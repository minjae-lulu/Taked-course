import sys
import os
from urllib.parse import urlparse
from socket import *
import select

def sendRequest(url):
    path = url.path
    hostname = url.netloc
    clientTcpSocket = socket(AF_INET, SOCK_STREAM)
    clientTcpSocket.connect((hostname,80))
    requestString = f"GET {path} HTTP/1.0\r\nHost:{hostname}\r\n\r\n"
    clientTcpSocket.send(requestString.encode())
    resp = clientTcpSocket.recv(1 << 20)
    return resp


def main():
    tcpServerSocket = socket(AF_INET,SOCK_STREAM)
    tcpServerSocket.bind(("",8321))
    tcpServerSocket.listen(10)

    tcpServerSocket2 = socket(AF_INET,SOCK_STREAM)
    tcpServerSocket2.bind(("",8322))
    tcpServerSocket2.listen(10)

    tcpServerSocket3 = socket(AF_INET,SOCK_STREAM)
    tcpServerSocket3.bind(("",8323))
    tcpServerSocket3.listen(10)
    
    read_socket_list = [tcpServerSocket, tcpServerSocket2, tcpServerSocket3]
    print("Sockets created")

    while True:

        conn_read_socket_list, conn_write_socket_list, conn_except_socket_list = select.select(read_socket_list, [], [])
        for conn_read_socket in conn_read_socket_list:
            if conn_read_socket == tcpServerSocket:
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
                    connectionSocket.send(cachedFile.read())
                    cachedFile.close()
                    
                    print("post cache read")
                    print('Sent back the cached data')
                    
                except:
                    response = sendRequest(parsedUrl)
                    print(f"response: {response}")
                    if not os.path.exists(os.path.dirname(cachedFilePath)):
                        os.makedirs(os.path.dirname(cachedFilePath))
                    with open(cachedFilePath,'wb') as cachedFile:
                        cachedFile.write(response)
                    connectionSocket.send(response)
                    connectionSocket.close()

            elif conn_read_socket == tcpServerSocket2:
                connectionSocket,addr = tcpServerSocket2.accept()
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
                    connectionSocket.send(cachedFile.read())
                    cachedFile.close()
                    
                    print("post cache read")
                    print('Sent back the cached data')
                    
                except:
                    response = sendRequest(parsedUrl)
                    print(f"response: {response}")
                    if not os.path.exists(os.path.dirname(cachedFilePath)):
                        os.makedirs(os.path.dirname(cachedFilePath))
                    with open(cachedFilePath,'wb') as cachedFile:
                        cachedFile.write(response)
                    connectionSocket.send(response)
                    connectionSocket.close()

            elif conn_read_socket == tcpServerSocket3:
                connectionSocket,addr = tcpServerSocket3.accept() 
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
                    connectionSocket.send(cachedFile.read())
                    cachedFile.close()
                    
                    print("post cache read")
                    print('Sent back the cached data')
                    connectionSocket.close()
                except:
                    response = sendRequest(parsedUrl)
                    print(f"response: {response}")
                    if not os.path.exists(os.path.dirname(cachedFilePath)):
                        os.makedirs(os.path.dirname(cachedFilePath))
                    with open(cachedFilePath,'wb') as cachedFile:
                        cachedFile.write(response)
                    connectionSocket.send(response)
                    connectionSocket.close()
        
        
    tcpServerSocket.close()
    print("Done")
        
if __name__ == "__main__" : main()