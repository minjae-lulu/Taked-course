import numpy as np
from matplotlib import pyplot as plt

import warnings
warnings.filterwarnings('ignore') #sometimes, my code occur Mean of empty slice warning so i ignore warning.
np.seterr(invalid='ignore')

def analyze_kmeans():
    """
    Top-level wrapper to iterate over a bunch of values of k and plot the
    distortions and misclassification rates.
    """
    X = np.genfromtxt("digit.txt")
    y = np.genfromtxt("labels.txt", dtype=int)
    distortions = [] # same mean with inertia
    errs = []
    ks = range(1, 11) # test 1->10 cluster.
    for k in ks:
        distortion, err = analyze_one_k(X, y, k)
        distortions.append(distortion)
        errs.append(err)
    fig, ax = plt.subplots(2, figsize=(8, 6)) # make 2 pic that size 8*6
    ax[0].plot(ks, distortions, marker=".")
    ax[0].set_ylabel("Distortion")
    ax[1].plot(ks, errs, marker=".")
    ax[1].set_xlabel("k")
    ax[1].set_ylabel("Mistake rate")
    ax[0].set_title("k-means performance")
    fig.savefig("kmeans.png")                 # make and save kmeans.png file


def analyze_one_k(X, y, k):
    """
    Run the k-means analysis for a single value of k. Return the distortion and
    the mistake rate.
    """
    print ("Running k-means with k={0}".format(k))
    clust = cluster(X, y, k)
    print ("Computing classification error.")
    err = compute_mistake_rate(y, clust)
    return clust["distortion"], err


def cluster(X, y, k, n_starts=5):
    """
    Run k-means a total of n_starts times. Returns the results from the run that
    had the lowest within-group sum of squares (i.e. the lowest distortion).

    Inputs
    ------
    X is an NxD matrix of inputs.
    y is a Dx1 vector of labels.
    n_starts says how many times to randomly re-initialize k-means. You don't
        need to change this.

    Outputs
    -------
    The output is a dictionary with the following fields:
    Mu is a kxD matrix of cluster centroids
    z is an Nx1 vector assigning points to clusters. So, for instance, if z[4] =
        2, then the algorithm has assigned the 4th data point to the second
        cluster.
    distortion is the within-group sum of squares, a number.
    """
    def loop(X, i):
        """
        A single run of clustering.
        """
        Mu = initialize(X, k)       # first initialize random
        N = X.shape[0]              # mean 1000
        z = np.repeat(-1, N)        # z = -1 initial first and later assign value.
        while True:
            old_z = z
            z = assign(X, Mu)       # The vector of assignments z.
            Mu = update(X, z, k)    # Update the centroids
            if np.all(z == old_z):  # until the change does't occur
                distortion = compute_distortion(X, Mu, z)
                return dict(Mu=Mu, z=z, distortion=distortion)

    # Main function body
    print ("Performing clustering.")
    results = [loop(X, i) for i in range(n_starts)]
    best = min(results, key=lambda entry: entry["distortion"])
    best["digits"] = label_clusters(y, k, best["z"])
    return best


def assign(X, Mu):
    """
    Assign each entry to the closest centroid. Return an Nx1 vector of
    assignments z.
    X is the NxD matrix of inputs.
    Mu is the kxD matrix of cluster centroids.
    """
    find_z = []
    for i in range(Mu.shape[0]):  # k
        find_z.append(np.linalg.norm((X-Mu[i]), axis=1))
        
    z = np.argmin(find_z,axis=0)  #find min value and assign 
    return z

def update(X, z, k):
    """
    Update the cluster centroids given the new assignments. Return a kxD matrix
    of cluster centroids Mu.
    X is the NxD inputs as always.
    z is the Nx1 vector of cluster assignments.
    k is the number of clusters.
    """
    Mu = np.zeros((k, X.shape[1]))  # initialize 0 first
    for i in range(0, k):
        centroid = np.mean(X[z==i], axis=0, dtype=np.int)  # update centroid new z, dtype = int!!!!
        Mu[i] = centroid
    return Mu


def compute_distortion(X, Mu, z):
    """
    Compute the distortion (i.e. within-group sum of squares) implied by NxD
    data X, kxD centroids Mu, and Nx1 assignments z.
    """
    square_of_e = []
    distort = []
    for i in range(Mu.shape[0]):
        square_of_e.append(np.linalg.norm((X[z==i]-Mu[i]), axis = 1))
        
    for j in range(len(square_of_e)):
        distort.append(np.sum((square_of_e[j])**2)) # square of error
    distortion = sum(distort)               # sum of square of error
    return distortion


def initialize(X, k):
    """
    Randomly initialize the kxD matrix of cluster centroids Mu. Do this by
    choosing k data points randomly from the data set X.
    """
    rand_v = np.random.randint(0,X.shape[0],k)
    Mu = X[rand_v]          # make Mu randomly
    return Mu


def label_clusters(y, k, z):
    """
    Label each cluster with the digit that occurs most frequently for points
    assigned to that cluster.
    Return a kx1 vector labels with the label for each cluster.
    For instance: if 20 points assigned to cluster 0 have label "3", and 40 have
    label "5", then labels[0] should be 5.

    y is the Nx1 vector of digit labels for the data X
    k is the number of clusters
    z is the Nx1 vector of cluster assignments.
    """
    labels = []
    for i in range(k):
        cnt = [0,0,0,0]
        for j in range(len(z)):
            if z[j] == i:
                if y[j] == 1:
                    cnt[0] = cnt[0] + 1
                elif y[j] == 3:
                    cnt[1] = cnt[1] + 1
                elif y[j] == 5:
                    cnt[2] = cnt[2] + 1
                elif y[j] == 7:
                    cnt[3] = cnt[3] + 1
        lab_num = 2*np.argmax(cnt)+1     # choose the most count assigned label (2*max + 1 : make 0 1 2 3 to 1 3 5 7)
        labels.append(lab_num)
        
    labels = np.array(labels)
    #print("label is : ------------")
    #print(labels)
    return labels


def compute_mistake_rate(y, clust):
    """
    Compute the mistake rate as discussed in IE406_Assignment_1.pdf.
    y is the Nx1 vector of true labels.
    clust is the output of a run of clustering. Two fields are relevant:
    "digits" is a kx1 vector giving the majority label for each cluster
    "z" is an Nx1 vector of final cluster assignments.
    """
    def zero_one_loss(xs, ys):
        return sum(xs != ys) / float(len(xs))

    y_hat = clust["digits"][clust["z"]]
    return zero_one_loss(y, y_hat)


def main():
    analyze_kmeans()


if __name__ == '__main__':
    main()
