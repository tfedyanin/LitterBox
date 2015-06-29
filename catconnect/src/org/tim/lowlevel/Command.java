package org.tim.lowlevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User: Marianna
 * 07.04.2015
 * 22:09
 */
public enum Command {
    BOWL_STATUS("bowl",
            Response.BOWL_IN,
            Response.BOWL_OUT,
            Response.BOWL_STOP),
    BOWL_OUT("bowl cw",
            Response.BOWL_OUT),
    BOWL_IN("bowl ccw",
            Response.BOWL_IN),
    BOWL_STOP("bowl stop",
            Response.BOWL_STOP),
    ARM_STATUS("arm",
            Response.ARM_UP,
            Response.ARM_DOWN,
            Response.ARM_STOP),
    ARM_DOWN("arm down",
            Response.ARM_DOWN),
    ARM_UP("arm up",
            Response.ARM_UP),
    ARM_STOP("arm stop",
            Response.ARM_STOP),
    DOSAGE_STATUS("dosage",
            Response.DOSAGE_ON,
            Response.DOSAGE_OFF),
    DOSAGE_ON("dosage on",
            Response.DOSAGE_ON),
    DOSAGE_OFF("dosage off",
            Response.DOSAGE_OFF),
    TAP_STATUS("tap",
            Response.TAP_ON,
            Response.TAP_OFF),
    TAP_ON("tap on",
            Response.TAP_ON),
    TAP_OFF("tap off",
            Response.TAP_OFF),
    DRAIN_STATUS("drain",
            Response.DRAIN_ON,
            Response.DRAIN_OFF),
    DRAIN_ON("drain on",
            Response.DRAIN_ON),
    DRAIN_OFF("drain off",
            Response.DRAIN_OFF),
    DRYER_STATUS("dryer",
            Response.DRYER_ON,
            Response.DRYER_OFF),
    DRYER_ON("dryer on",
            Response.DRYER_ON),
    DRYER_OFF("dryer off",
            Response.DRYER_OFF),
    CAT("cat",
            Response.CAT_IN,
            Response.CAT_OUT),
    WATER("water",
            Response.WATER_LOW,
            Response.WATER_HIGH),
    HEAT("heat",
            Response.HEAT_YES,
            Response.HEAT_NO),;


    private final String command;
    private final List<Response> responses;


    Command(String command, Response... responses) {
        this.responses = new ArrayList<>(responses.length);
        this.responses.addAll(Arrays.asList(responses));
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public CommandResponseType getType() {
        if (command.contains("bowl")) {
            return CommandResponseType.BOWL;
        }
        if (command.contains("arm")) {
            return CommandResponseType.ARM;
        }
        if (command.contains("dosage")) {
            return CommandResponseType.DOSAGE;
        }
        if (command.contains("drain")) {
            return CommandResponseType.DRAIN;
        }
        if (command.contains("dryer")) {
            return CommandResponseType.DRYER;
        }
        if (command.contains("tap")) {
            return CommandResponseType.TAP;
        }
        if (command.contains("cat")) {
            return CommandResponseType.CAT;
        }
        if (command.contains("heat")) {
            return CommandResponseType.HEAT;
        }
        if (command.contains("water")) {
            return CommandResponseType.WATER;
        }
        return null;
    }
}
