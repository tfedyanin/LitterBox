import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Marianna on 07.04.2015.
 */
public class Client {
    private final Socket socket;
//    private final DataOutputStream out;

//    BufferedReader in;
    private final CommandTransmitter transmitter;


    public Client(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        transmitter = new CommandTransmitter(new DataOutputStream(socket.getOutputStream()));
        Receiver receiver = new Receiver(socket.getInputStream());
        receiver.addListener(transmitter);
        receiver.addListener(new ReceiverListener() {
            @Override
            public void recieve(char ch) {
                System.out.print(ch);
            }

            @Override
            public void receive(String string) {

//                System.out.println(string);
            }
        });
        Thread thread = new Thread(receiver);
        thread.start();
    }

    void send(Request request) throws IOException, EchoException {
        send(request.getCommand());
    }

    void send(String command) throws IOException, EchoException {
        transmitter.send(command);
    }
}
