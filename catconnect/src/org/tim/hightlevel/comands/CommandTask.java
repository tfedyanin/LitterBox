package org.tim.hightlevel.comands;

import org.tim.exceptions.EchoException;
import org.tim.lowlevel.Command;
import org.tim.lowlevel.LowLevelClient;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * User: Tim
 * 01.06.2015 21:04
 */
public class CommandTask implements Callable<Objects> {

    private final Command command;
    private final LowLevelClient lowLevelClient;


    public CommandTask(Command command, LowLevelClient lowLevelClient) {
        this.command = command;
        this.lowLevelClient = lowLevelClient;
    }

    @Override
    public Objects call() throws Exception {
        lowLevelClient.send(command);
        return null;
    }

}
