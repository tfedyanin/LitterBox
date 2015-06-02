package org.tim;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Тимофей
 * 12.04.2015 17:21
 */
public class Receiver implements Runnable {
    private final List<ReceiverListener> listeners = new LinkedList<ReceiverListener>();
    private final InputStream stream;
    private ArrayList<Character> buffer;

    public void addListener(ReceiverListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public boolean removeListener(ReceiverListener listener) {
        synchronized (listeners) {
            return listeners.remove(listener);
        }
    }

    public void removeAllListeners() {
        synchronized (listeners) {
            listeners.clear();
        }
    }

    public Receiver(InputStream inputStream) {
        this.stream = inputStream;
        buffer = new ArrayList<Character>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                char read = (char) stream.read();
                notifyListeners(read);
                if (read == '\n') {
                    StringBuilder builder = new StringBuilder();
                    for (Character character : buffer) {
                        builder.append(character);
                    }
                    notifyListeners(builder.toString());
                    buffer.clear();
                    continue;
                }
                buffer.add(read);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void notifyListeners(char ch) {
        synchronized (listeners) {
            for (ReceiverListener listener : listeners) {
                listener.recieve(ch);
            }
        }
    }

    private void notifyListeners(String string) {
        synchronized (listeners) {
            for (ReceiverListener listener : listeners) {
                listener.receive(string);
            }
        }
    }

}
