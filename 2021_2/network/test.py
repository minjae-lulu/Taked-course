import sys
import os
from urllib.parse import urlparse
from socket import *

def sendRequest(url):
    path = url.path
    hostname = url.netloc
    clientTcpSocket = socket(AF_INET, SOCK_STREAM)
    clientTcpSocket.connect((hostname,80))
    requestString = f"GET {path} HTTP/1.0\r\nHost:{hostname}\r\n\r\n"
    clientTcpSocket.send(requestString.encode())
    resp = clientTcpSocket.recv(1 << 20)
    return resp


#def test():
#    cwd = os.getcwd()
#    hostname = "mathalgs.com"
#    path = "/index.html"[1:]
#    cachedFilePathStr = os.path.join(cwd,hostname,path)#f"{cwd}/{hostname}{path}"
#    pathDir = os.path.dirname(path)
#    if not os.path.exists(cachedFilePathStr):
#        os.makedirs(cachedFilePathStr)

def main():
    tcpServerSocket = socket(AF_INET,SOCK_STREAM)
    tcpServerSocket.bind(("",12345))
    tcpServerSocket.listen(1)
    
    while True:
        connectionSocket,addr = tcpServerSocket.accept()
        message = connectionSocket.recv(1 << 20).decode()
        cwd = os.getcwd()
        print(message)
        urlString = message.split()[1]
        parsedUrl = urlparse(urlString) 
        hostname = parsedUrl.netloc
        path = parsedUrl.path
        if path == "/" or path == "" :
            path =  "/index.html"
        cachedFilePath = os.path.join(cwd,hostname,path[1:])
        try:
            print("preopen")
            cachedFile = open(cachedFilePath,'rb')
            print("post open")
            print("pre cache read")
            #Send back data from file, header data saved in the file
            connectionSocket.send(cachedFile.read())
            cachedFile.close()
            
            print("post cache read")
            print('Sent back the cached data')
        except:
            #Perform the request, and send back the data
            response = sendRequest(parsedUrl)
            print(f"response: {response}")
            #Cache the result
            if not os.path.exists(os.path.dirname(cachedFilePath)):
                os.makedirs(os.path.dirname(cachedFilePath))
            with open(cachedFilePath,'wb') as cachedFile:
                cachedFile.write(response)
            #Send back the response
            connectionSocket.send(response)
            connectionSocket.close()
        
    tcpServerSocket.close()
    print("Done")
        
if __name__ == "__main__" : main()