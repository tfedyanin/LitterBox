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
    private final DataOutputStream out;

    BufferedReader in;


    public Client(InetAddress address, int port) throws IOException {
        socket = new Socket(address, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
    }

    void send(Request request) throws IOException, EchoException {
        send(request.getCommand());
    }

    void send(String command) throws IOException, EchoException {
        while (in.ready()) {
            in.read();
        }
        StringBuilder builder = new StringBuilder();
        for (char c : command.toCharArray()) {
            out.writeByte(c);
            out.flush();
            char rc = (char) in.read();
            if (rc == '#') {
                rc = (char) in.read();
            }
            builder.append(rc);
            if (rc != c) {
                throw new EchoException(command, builder.toString());
            }
        }
        out.writeByte('\r');
        out.flush();
        char rc = (char) in.read();
        if (rc == '#') {
            rc = (char) in.read();
            if (rc != ' ') {
                throw new EchoException(command, builder.toString());
            }
            rc = (char) in.read();
        }
        if ('\r' != rc && '\n' != rc) {
            throw new EchoException(command, builder.toString());
        }

        //todo
        System.out.println(in.readLine());
    }
}
