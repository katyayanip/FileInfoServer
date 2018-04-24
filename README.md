# FileInfoServer

Gives the remote server file system metadata.

It is implemented in a way that multiple clients can accesss the server file system info. Server will have a configured number of threads and clients sockets are put into QUeue. Server's threads read from this queue and serve all clients.

FIServer.jar Jar Run parameters   
portnumber RemoteserverFileFolder numberofThreads 
6655 "C:\\Users\\katyayani\\Desktop" 10

FIClient.jar
client Jar Run Parameters
portNumber 6655
