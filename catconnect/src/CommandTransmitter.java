import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Тимофей
 * 12.04.2015 17:52
 */
public class CommandTransmitter implements ReceiverListener {
    private final DataOutputStream out;
    private volatile boolean waitChar = false;
    private volatile boolean waitString = false;
    private volatile char aChar;
    private final Lock lock = new ReentrantLock();


    public CommandTransmitter(DataOutputStream out) {
        this.out = out;
    }

    synchronized void send(String command) {
        try {
            init();
            for (char c : command.toCharArray()) {
                transmitEcho(c);
            }
            transmitEnd();
            waitString = true;
            while (waitString) {
                this.wait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (EchoException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException, InterruptedException, EchoException {
        transmitEnd();
        char echo = echo();
        if ('#' != echo) {
            throw new EchoException(echo, '#');
        }
        echo = echo();
        if (' ' != echo) {
            throw new EchoException(echo, ' ');
        }

    }


    private void transmit(char ch) throws IOException, InterruptedException {
        out.writeByte(ch);
        out.flush();
//        Thread.sleep(50);
    }

    private void transmitEnd() throws IOException, InterruptedException {
        transmitEcho('\r', '\n');
    }

    private void transmitEcho(char ch) {
        transmitEcho(ch, ch);
    }

    private void transmitEcho(char ch, char echoExpect) {
        try {
            transmit(ch);
            char echoChar = echo();
            if (echoChar != echoExpect) {
                throw new EchoException(echoExpect, echoChar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (EchoException e) {
            e.printStackTrace();
        }
    }

    private char echo() throws InterruptedException {
        waitChar = true;
        char res = aChar;
        while (waitChar) {
            this.wait();
            res = aChar;
        }
        return res;
    }


    @Override
    public void recieve(char ch) {
        synchronized (this) {
            waitChar = false;
            aChar = ch;
            this.notifyAll();
        }
    }

    @Override
    public void receive(String string) {
        synchronized (this) {
            waitString = false;
            this.notifyAll();
        }
    }
}
