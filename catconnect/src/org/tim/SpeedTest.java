package org.tim;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Marianna on 13.04.2015.
 */
public class SpeedTest {
    private static LowLevelClient lowLevelClient;

    public static void main(String[] args) {
        try {
            lowLevelClient = new LowLevelClient(InetAddress.getByAddress(
                    new byte[]{(byte) 192, (byte) 168, 1, (byte) 177}),
                    80);
            for (int i = 0; i < 50; i++) {
                lowLevelClient.send(Request.BOWL_STATUS);
                lowLevelClient.send(Request.CAT);

            }
        } catch (IOException | EchoException e) {
            e.printStackTrace();
        }
    }
}
