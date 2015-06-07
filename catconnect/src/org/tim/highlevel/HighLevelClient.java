package org.tim.highlevel;

import org.tim.highlevel.comands.CommandTask;
import org.tim.lowlevel.Command;
import org.tim.lowlevel.LowLevelClient;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * User: Tim
 * 01.06.2015 20:59
 */
public class HighLevelClient {
    static final private int DEFAULT_TIMEOUT = 1000;


    final private ExecutorService executorService;
    final private LowLevelClient lowLevelClient;
    private int timeout = DEFAULT_TIMEOUT;


    public HighLevelClient(ExecutorService executorService, LowLevelClient lowLevelClient) {
        this(executorService, lowLevelClient, DEFAULT_TIMEOUT);
    }

    public HighLevelClient(ExecutorService executorService, LowLevelClient lowLevelClient, int timeout) {
        this.executorService = executorService;
        this.lowLevelClient = lowLevelClient;
        this.timeout = timeout;
    }

    public boolean execute(Command command) {
        CommandTask task = new CommandTask(command, lowLevelClient);
        Future<Objects> future = executorService.submit(task);
        try {
            future.get(timeout, TimeUnit.MILLISECONDS);
            return true;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return false;
        } finally {
            future.cancel(true);
        }
    }
}
