/**
 * Created by Marianna on 07.04.2015.
 */
public class EchoException extends Exception {
    public EchoException(String command, String result) {
        super("Can't send command.\n" +
                "Transmit: " + command.substring(0, result.length()) + "<" + command.substring(result.length()) + ">\n" +
                "Receive: " + result);
    }
}
