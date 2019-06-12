make sure java bin folder is in your path
run build script
open cmd window and run:
java DateServer

open cmd window and run:
java DateClient 127.0.0.1

Observe the hex value for ascii "a" print on the server side. This was sent from the client.

On the server side, specify the port that you want to bind to and then connect your other client to it and spam it with data and it will print one byte at a time.