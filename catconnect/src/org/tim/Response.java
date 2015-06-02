package org.tim;

/**
 * Created by Marianna on 07.04.2015.
 */
public enum Response {
    BOWL_OUT("Bowl: cw"),
    BOWL_IN("Bowl: ccw"),
    BOWL_STOP("Bowl: stop"),
    ARM_STATUS("arm"),
    ARM_DOWN("Arm: up"),
    ARM_UP("Arm: down"),
    ARM_STOP("Arm: stop"),
    //todo
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

    private final String info;


    Response(String info) {
        this.info = info;
    }

    String getInfo() {
        return info;
    }
}
