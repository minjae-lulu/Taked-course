import socket
from threading import Thread
import re
import requests
import ssl


serverSocket = socket.socket() 
localHostIp = "localhost" 
print("application bind with ", localHostIp)
port = 8080
serverSocket.bind((localHostIp, port))     
serverSocket.listen(10)
#serverSocket.setblocking(False)
allThreads = set() 
buffer = 512

def handleClientConnection(clientSocket, clientAddr):
    'This will handle all the client connections'
    clientHeader = ""
    listHeader = []
    print("Recieving request from client ", clientAddr)
    while True:
        rawData = clientSocket.recv(buffer)
        try:
            clientHeader += rawData.decode("utf-8")
        except UnicodeDecodeError:
            break
        if len(rawData) < buffer:
            break
    listHeader = list(map(str, clientHeader.strip().split("\r\n")))
    

    if list(map(str, listHeader[0].split(" ")))[0].strip() == "GET":
        print("Recieved request: ", listHeader[0])
        handleHttpRequest(clientSocket, listHeader)
    else :
        print("Recieved request: ", listHeader[0])
        print("client can only use GET method\n")
        response = "501 Not implemented \r\n MESSAGE : Client can only use GET method at our assignment\r\n"
        clientSocket.send(response.encode("utf-8"))




def handleHttpRequest(clientSocket, listHeader):
    'This will handle http requests from the clients'
    try: 
        webRequest = requests.get(list(map(str, listHeader[0].split(" ")))[1])
        reqstate = webRequest.status_code
        
        if reqstate == 200:
            response = "200 OK\r\n Success \r\n\r\n"
            clientSocket.send(response.encode("utf-8"))
            clientSocket.send(webRequest.text.encode("utf-8"))

        elif 300 <= reqstate <=  500:
            response = str(reqstate) + "\r\n Found \r\n\r\n"
            clientSocket.send(response.encode("utf-8"))
            clientSocket.send(webRequest.text.encode("utf-8"))
            
    except :
        response = "400 Bad Request \r\nError occur your request is wrong address.\r\n"
        clientSocket.send(response.encode("utf-8"))
 

while True:
    print("\tWaiting for client connection…")
    clientSocket, clientAddr = serverSocket.accept()  
    print("Connection accepted from ", clientAddr)
    thread = Thread(target = handleClientConnection, args=(clientSocket, clientAddr))
    allThreads.add(thread)
    thread.start()