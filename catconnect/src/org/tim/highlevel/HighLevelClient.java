package org.tim.highlevel;

import org.tim.exceptions.EchoException;
import org.tim.highlevel.comands.CommandTask;
import org.tim.lowlevel.Command;
import org.tim.lowlevel.LowLevelClient;
import org.tim.lowlevel.ReceiverListener;
import org.tim.lowlevel.Response;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * User: Tim
 * 01.06.2015 20:59
 */
public class HighLevelClient {
    static final private int DEFAULT_TIMEOUT = 1000;
    static final private int DEFAULT_ATTEMP_COUNT = 5;
    private final CurrentState state = new CurrentState();


    final private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    final private LowLevelClient lowLevelClient;
    private int timeout = DEFAULT_TIMEOUT;
    private final ReceiverListener listener = new ReceiverListener() {
        private short lineNumber = 0;

        @Override
        public void receive(char ch) {

        }

        @Override
        public void receive(String string) {
            if (lineNumber == Short.MAX_VALUE) {
                lineNumber = 0;
            } else {
                lineNumber++;
            }
            Response response = Response.getByName(string);
            if (string.contains("#")) {
                System.out.format("%3d %s\n", lineNumber, string);
                return;
            }
            if (response == null) {
                System.err.format("%3d %s\n", lineNumber, "Неизвестный ответ на команду: " + string);
                return;
            }
            if (response.equals(Response.WELCOME)) {
                System.err.format("%3d %s\n", lineNumber, "Туалет перезагрузился");
                state.setConsistentState(false);
                return;
            }
            if (response.equals(Response.PIN_RESET)) {
                System.err.format("%3d %s\n", lineNumber, "Произошел сброс состояния туалета");
                return;
            }
            System.out.format("%3d %s\n", lineNumber, string);
            state.set(response);
        }
    };
    ;


    public HighLevelClient(LowLevelClient lowLevelClient) {
        this(lowLevelClient, DEFAULT_TIMEOUT);
    }

    public HighLevelClient(LowLevelClient lowLevelClient, int timeout) {
        lowLevelClient.getReceiver().addListener(listener);
        this.lowLevelClient = lowLevelClient;
        this.timeout = timeout;
    }

    public boolean execute(Command command) {
        return execute(command, false);
    }

    private boolean execute(Command command, boolean restoreMode) {
        boolean success = false;
        int attemptCount = 0;

        while (!success && attemptCount++ < DEFAULT_ATTEMP_COUNT) {
            CommandTask task = new CommandTask(command, lowLevelClient);
            long submitTime = System.currentTimeMillis();
            Future<Objects> future = executorService.submit(task);
            try {
                future.get(timeout, TimeUnit.MILLISECONDS);
                while (submitTime > state.getLastUpdate()) {
                    Thread.sleep(10);
                }
                if (!state.isConsistentState()) {
                    restore();
                }
                success = state.check(command);

            } catch (TimeoutException e) {
                success = state.check(command);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return false;
            } finally {
                //todo check this place
                future.cancel(true);
            }
        }
        return success;
    }

    private boolean restore() {
        List<Response> responses = state.notDefaultStates();
        boolean result = true;
        state.setConsistentState(true);
        for (Response response : responses) {
            boolean execute = execute(response.getCommand(), true);
            result &= execute;
        }
        return result;
    }
}
