package org.tim.lowlevel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

/**
 * User: Marianna
 * 07.04.2015
 * 22:07
 */
public class LowLevelClient {
    private final CommandTransmitter transmitter;


    public LowLevelClient(final InetAddress address, int port) throws IOException {
        Socket socket = new Socket(address, port);
        transmitter = new CommandTransmitter(new DataOutputStream(socket.getOutputStream()));
        Receiver receiver = new Receiver(socket.getInputStream());
        receiver.addListener(transmitter);
        receiver.addListener(new ReceiverListener() {
            @Override
            public void receive(char ch) {
                System.out.print(ch);
            }

            @Override
            public void receive(String string) {

            }
        });
        Thread thread = new Thread(receiver);
        thread.start();
    }

    public void send(Request request) throws IOException, InterruptedException {
        send(request.getCommand());
    }

    void send(String command) throws IOException, InterruptedException {
        transmitter.send(command);
    }
}