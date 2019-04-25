# Overview
Real time chat application that allow messaging between a server and the client connected to the server.The application is heavily threaded using the BufferedInputStream to costantly listen out for incoming stream and BufferedOutputStream to send data.  

# How to use 
1. Download / Clone the repository.
2. Navigate to folder `out/artifacts/ChatApp_jar`.
3. Using the cmd/terminal run `ChatApp.jar`, command : `java -jar ChatApp.jar`.
4. Type in `Server` as requested by the console application.
5. Enter a port # for which the server will be listening on.
6. Run another instance of cmd/terminal.
7. This time select type in `Client` as requested by the application.
8. Enter the port number that you have selected for your server.
9. Enter the ip address of the server you'd like to connect with, in the case of running it on the same machine the ip would be `127.0.0.1`.
10. If the connection is successful text messaging is now possible.
