import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A simple TCP server. When a client connects, it sends the client the current
 * datetime, then closes the connection. This is arguably the simplest server
 * you can write. Beware though that a client has to be completely served its
 * date before the server will be able to handle another client.
 */
public class DateServer {
    public static void main(String[] args) throws IOException {
		
		byte[] databuffer = new byte[100000];
		int i = 0;
		int serverPort = 59090;
		
        try (ServerSocket listener = new ServerSocket(serverPort)) {
            System.out.println("The date server is running...");
            while (true) {
                try (Socket socket = listener.accept()) {
					boolean sendDataWhenPeopleConnect = true;
					if (sendDataWhenPeopleConnect) {
						OutputStream outputListener = socket.getOutputStream();					
						PrintWriter out = new PrintWriter(outputListener, true);
						out.println(new Date().toString());
					}
					InputStream inputListener = socket.getInputStream();
					boolean waitForSomeData = true;
					while (waitForSomeData) {
						try {
							int tmpData = inputListener.read(); // one byte read
							// note, this variable is an int, but
							// only the first byte is valid data

							String printMe = String.format("0x%08X", tmpData);
							System.out.println(printMe);
							databuffer[i] = (byte)tmpData;
							i++;
							if (i == 100000) {
								i = 0;
							}
						} catch (Exception e) {
							System.out.println("hey an exception, wait for new connection");
							waitForSomeData = false;
						}
						
					}
					
                }
            }
        }
    }
}
