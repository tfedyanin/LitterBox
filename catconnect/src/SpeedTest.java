import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Marianna on 13.04.2015.
 */
public class SpeedTest {
    private static Client client;

    public static void main(String[] args) {
        try {
            client = new Client(InetAddress.getByAddress(
                    new byte[]{(byte) 192, (byte) 168, 1, (byte) 177}),
                    80);
            for (int i = 0; i < 5; i++) {
                client.send(Request.BOWL_STATUS);

            }
        } catch (IOException | EchoException e) {
            e.printStackTrace();
        }
    }
}
