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
    private String lastLine;
    private List<Character> currentLine = new ArrayList<Character>(64){
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(this.size());
            this.forEach(stringBuilder::append);
            return stringBuilder.toString();
        }
    };

    public CommandTransmitter(DataOutputStream out) {
        this.out = out;
    }

    private void getReady() throws InterruptedException, IOException {
        if (currentLine.toString().equals("# ")) {
            return;
        }
        transmit('\r');
        while (currentLine.toString().equals("# ")) {
            //todo возможен вечное ожидание, нужно побороть переобределив ожидание с таймаутом
            this.wait();
        }
    }

    synchronized void send(String command) throws IOException, InterruptedException {
        //todo При черезмерном ожидании или ошибке выполнения команды требуется повторить попытку. Количество повторов
        // должно быть определно, после критическая ошибка
        getReady();
        for (char c : command.toCharArray()) {
            transmitEcho(c);
        }
        transmitEnd();
    }

    private void transmit(char ch) throws IOException, InterruptedException {
        out.writeByte(ch);
        out.flush();
    }

    private void transmitEnd() throws IOException, InterruptedException {
        transmitEcho('\r', '\n');
    }

    private void transmitEcho(char ch) throws IOException, InterruptedException {
        transmitEcho(ch, ch);
    }

    private void transmitEcho(char ch, char echoExpect) throws IOException, InterruptedException {
        transmit(ch);
        while (currentLine.isEmpty() || currentLine.get(currentLine.size() - 1) != echoExpect) {
            //todo возможен вечное ожидание, нужно побороть переобределив ожидание с таймаутом
            this.wait();
        }
    }

    @Override
    public synchronized void recieve(char ch) {
        currentLine.add(ch);
        notifyAll();
    }

    @Override
    public synchronized void receive(String string) {
        currentLine.clear();
        lastLine = string;
        notifyAll();
    }
}
