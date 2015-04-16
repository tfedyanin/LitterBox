import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
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
    private volatile AtomicInteger aChar = new AtomicInteger();
    private final Lock lock = new ReentrantLock();
    private AtomicBoolean preReadyToTransmit = null;
    private AtomicBoolean readyToTransmit = null;

    public CommandTransmitter(DataOutputStream out) {
        this.out = out;
    }

    private void ready() throws IOException, InterruptedException {
        if (readyToTransmit == null) {
            preReadyToTransmit = new AtomicBoolean(false);
            readyToTransmit = new AtomicBoolean(false);
//            transmit('\r');
        }
        if (!readyToTransmit.get()) {
            transmit('\r');
        }
        while (!readyToTransmit.get()) {
            this.wait();
        }
    }

    synchronized void send(String command) {
        try {
            ready();
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
        preReadyToTransmit.set(false);
        readyToTransmit.set(false);
        out.writeByte(ch);
        out.flush();
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
//            if (echoChar != echoExpect) {
//                throw new EchoException(echoExpect, echoChar);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //todo механизм с отдачей, иногда допустить попытку подождать следующий символ
            //todo разобраться с таймаутами, чтобы исключить бесконечное ожидание
//        } catch (EchoException e) {
//            e.printStackTrace();
        }
    }

    private char echo() throws InterruptedException {
        waitChar = true;
        char res = (char) aChar.get();
        while (waitChar) {
            this.wait();
            res = (char) aChar.get();
        }
        return res;
    }


    @Override
    public void recieve(char ch) {
        synchronized (this) {
            waitChar = false;
            aChar.set(ch);

            if (!preReadyToTransmit.get() && !readyToTransmit.get() && ch == '#') {
                preReadyToTransmit.set(true);
                this.notifyAll();
                return;
            }
            if (preReadyToTransmit.get() && !readyToTransmit.get() && ch == ' ') {
                preReadyToTransmit.set(false);
                readyToTransmit.set(true);
                this.notifyAll();
                return;
            }
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
