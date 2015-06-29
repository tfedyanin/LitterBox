package org.tim.lowlevel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * User: Marianna
 * 07.04.2015
 * 22:07
 */
public class LowLevelClient {
    private final CommandTransmitter transmitter;

    private final Receiver receiver;

    public LowLevelClient(final InetAddress address, int port) throws IOException {
        Socket socket = new Socket(address, port);
        transmitter = new CommandTransmitter(new DataOutputStream(socket.getOutputStream()));
        receiver = new Receiver(socket.getInputStream());
        receiver.addListener(transmitter);
//        receiver.addListener(new ReceiverListener() {
//            @Override
//            public void receive(char ch) {
////                System.out.print(ch);
//            }
//
//            @Override
//            public void receive(String string) {
//
//            }
//        });
        Thread thread = new Thread(receiver);
        thread.start();
    }


    public void send(Command command) throws IOException, InterruptedException {
        send(command.getCommand());
    }

    void send(String command) throws IOException, InterruptedException {
        transmitter.send(command);
    }

    public Receiver getReceiver() {
        return receiver;
    }
}
