package org.tim;

/**
 * Created by Marianna on 07.04.2015.
 */
public enum Request {
    BOWL_STATUS("bowl"),
    BOWL_OUT("bowl cw"),
    BOWL_IN("bowl ccw"),
    BOWL_STOP("bowl stop"),
    ARM_STATUS("arm"),
    ARM_DOWN("arm down"),
    ARM_UP("arm up"),
    ARM_STOP("arm stop"),
    DOSAGE_STATUS("dosage"),
    DOSAGE_ON("dosage on"),
    DOSAGE_OFF("dosage off"),
    TAP_STATUS("tap"),
    TAP_ON("tap on"),
    TAP_OFF("tap off"),
    DRAIN_STATUS("drain"),
    DRAIN_ON("drain on"),
    DRAIN_OFF("drain off"),
    DRYER_STATUS("dryer"),
    DRYER_ON("dryer on"),
    DRYER_OFF("dryer off"),
    CAT("cat"),
    WATER("water"),
    HEAT("heat"),;

    private final String command;


    Request(String command) {
        this.command = command;
    }

    String getCommand() {
        return command;
    }
}
