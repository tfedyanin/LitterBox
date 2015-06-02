package org.tim.lowlevel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Тимофей
 * 12.04.2015 17:52
 */
public class CommandTransmitter implements ReceiverListener {
    private final DataOutputStream out;

    private List<Character> currentLine = new ArrayList<Character>(64) {
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(this.size());
            this.forEach(stringBuilder::append);
            return stringBuilder.toString();
        }
    };

    public CommandTransmitter(final DataOutputStream out) {
        this.out = out;
    }

    private void getReady() throws InterruptedException, IOException {
        if (currentLine.toString().equals("# ")) {
            return;
        }
        transmit('\r');
        while (currentLine.toString().equals("# ")) {
            this.wait();
        }
    }

    synchronized void send(String command) throws IOException, InterruptedException {
        getReady();
        for (char c : command.toCharArray()) {
            transmitEcho(c);
        }
        transmitEnd();
    }

    private void transmitEnd() throws IOException, InterruptedException {
        transmitEcho('\r', '\n');
    }

    private void transmitEcho(char ch) throws IOException, InterruptedException {
        transmitEcho(ch, ch);
    }

    private void transmitEcho(final char ch, final char echo) throws IOException, InterruptedException {
        transmit(ch);
        while (currentLine.isEmpty() || currentLine.get(currentLine.size() - 1) != echo) {
            this.wait();
        }
    }

    private void transmit(final char ch) throws IOException {
        out.writeByte(ch);
        out.flush();
    }

    @Override
    public synchronized void receive(final char ch) {
        currentLine.add(ch);
        notifyAll();
    }

    @Override
    public synchronized void receive(final String string) {
        currentLine.clear();
        notifyAll();
    }
}
