from model import VAE
import torch
import numpy as np
import torchvision
import tqdm
from torch.utils.data import Dataset
from torch.utils.data import DataLoader
import cv2
import torch.nn.functional as F
from glob import glob
import pandas
from google.colab.patches import cv2_imshow
import utils

device = 'cuda:0'

class MVDataset(Dataset):
    def __init__(self, method=None):
        self.root = './datasets/toothbrush/' #Change this path
        self.x_data = []
        self.y_data = []

        if method == 'train':
            self.root = './datasets/toothbrush/'  #add
            self.root = self.root + 'train/good/'
            self.img_path = sorted(glob(self.root + '*.png'))
 
        elif method == 'test':
            self.root = './datasets/toothbrush/' #Change this path
            self.root = self.root + 'test/defective/'
            self.img_path = sorted(glob(self.root + '*.png'))
        elif method =='test_capsule':
            self.root = './datasets/capsule/' #Change this path
            self.root = self.root + 'test/'
            self.img_path = sorted(glob(self.root + '**/*.png'))
        elif method =='test_bottle':
            self.root = './datasets/bottle/' #Change this path
            self.root = self.root + 'test/'
            self.img_path = sorted(glob(self.root + '**/*.png'))
        elif method =='valid_bottle':
            self.root = './datasets/bottle/' #Change this path
            self.root = self.root + 'test/good/'
            self.img_path = sorted(glob(self.root + '*.png'))
        elif method =='valid_capsule':
            self.root = './datasets/capsule/' #Change this path
            self.root = self.root + 'test/good/'
            self.img_path = sorted(glob(self.root + '*.png'))

        for i in tqdm.tqdm(range(len(self.img_path))):
            img = cv2.imread(self.img_path[i], cv2.IMREAD_COLOR)
            print(self.img_path[i])
            b, g, r = cv2.split(img)
            img = cv2.merge([r, g, b])
            img = cv2.resize(img, dsize=(256, 256))
            #cv2.imwrite('test_%d.png' % i, img)

            self.x_data.append(img)
            self.y_data.append(img)

    def __len__(self):
        return len(self.img_path)

    def __getitem__(self, idx):
        transform1 = torchvision.transforms.ToTensor()
        new_x_data = transform1(self.x_data[idx])
        return new_x_data, self.y_data[idx]


class Trainer(object):
    def __init__(self, epochs, batch_size, lr, kl=0.00001): # kl add
        self.epochs = epochs
        self.batch_size = batch_size
        self.learning_rate = lr
        self._build_model()
        self.binary_cross_entropy = torch.nn.BCELoss()
        self.kl = kl # add

        dataset = MVDataset(method='train')
        self.root = dataset.root
        self.dataloader = DataLoader(dataset, batch_size=self.batch_size, shuffle=True)
        self.optimizer = torch.optim.Adam(self.net.parameters(), lr = self.learning_rate, betas=(0.9, 0.999))

        # Load of pretrained_weight file
        print("Training...")

    def _build_model(self):
        net = VAE()
        self.net = net.to(device)
        self.net.train()

        print('Finish build model.')

    def vae_loss(self, recon_x, x, mu, logvar):
        recon_loss = self.binary_cross_entropy(recon_x.view(-1, 256*256*3), x.view(-1, 256*256*3))
        kldivergence = -0.5 * torch.sum(1+ logvar - mu.pow(2) - logvar.exp()) 
        return recon_loss + 0.000001 * kldivergence

    def train(self):
        date = '20211017'
        loss_avg = [] # add
        for epoch in tqdm.tqdm(range(self.epochs + 1)):
            loss_avg.append(0)
            if epoch == 11:
                torch.save(self.net.state_dict(), "_".join(['./savemodel/toothbrush/epoch', str(epoch-1),str(self.kl), '.pth']))
            elif epoch ==501:
                torch.save(self.net.state_dict(), "_".join(['./savemodel/toothbrush/epoch', str(epoch-1),str(self.kl), '.pth'])) #Change this path
                break
            now_batch=0
            for batch_idx, samples in enumerate(self.dataloader):
                x_train, y_train = samples
                x_train, y_train = x_train.to(device), y_train.to(device)
                # print(x_train)
                # print(y_train)
                g, latent_mu, latent_logvar = self.net(x_train)
                loss = Trainer.vae_loss(self, g, x_train, latent_mu, latent_logvar)
                self.optimizer.zero_grad()
                loss.backward()
                self.optimizer.step()
                loss_avg[-1] += loss.item()
                now_batch += 1
                # pdb.set_trace()
            loss_avg[-1] /= now_batch
            print('Epoch [%d / %d] average reconstruction error: %f' % (epoch+1, now_batch, loss_avg[-1]))
             

        print('Finish training.')


class Tester(object):
    def __init__(self, batch_size):
        self.batch_size = batch_size
        self._build_model()

        dataset = MVDataset(method='test')
        self.root = dataset.root
        self.dataloader = DataLoader(dataset, batch_size=self.batch_size, shuffle=False)
        self.datalen = dataset.__len__()
        self.mse_all_img = []

        # Load of pretrained_weight file
        weight_PATH = '/content/gdrive/MyDrive/CSE48001/ass1/datasets/toothbrush/model.pth' #Change this path
        self.net.load_state_dict(torch.load(weight_PATH))

        print("Testing...")

    def _build_model(self):
        net = VAE()
        self.net = net.to(device)
        self.net.eval()

        print('Finish build model.')

    def test(self):
        for batch_idx, samples in enumerate(self.dataloader):
            x_test, y_test = samples
            out = self.net(x_test.cuda())

            x_test2 = 256. * x_test
            out2 = 256. * out[0]

            abnomal = utils.compare_images_colab(x_test2[0].clone().permute(1, 2, 0).cpu().detach().numpy(), out2[0].clone().permute(1, 2, 0).cpu().detach().numpy(), None, 0.2)   

            cv2.imwrite('test_%d_ori.png' % batch_idx, cv2.cvtColor(x_test2[0].clone().permute(1, 2, 0).cpu().detach().numpy(), cv2.COLOR_RGB2BGR))
            cv2.imwrite('test_%d_gen.png' % batch_idx, cv2.cvtColor(out2[0].clone().permute(1, 2, 0).cpu().detach().numpy(), cv2.COLOR_RGB2BGR))
            cv2.imwrite('test_%d_diff.png' % batch_idx, abnomal)

def main():

    epochs = 1500
    batchSize = 1
    learningRate = 1e-4

    #trainer = Trainer(epochs, batchSize, learningRate)
    #trainer.train()

    tester = Tester(batchSize)
    tester.test()

if __name__ == '__main__':
    main()
