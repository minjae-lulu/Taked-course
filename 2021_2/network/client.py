import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('localhost', 5000))
print("present time is: ", s.recv(1024).decode())
s.close()