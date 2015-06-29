package org.tim.lowlevel;

/**
 * User: Tim
 * 28.06.2015
 * 10:13
 */
public enum Response {
    WELCOME("*** GenieDiag ***"),
    PIN_RESET("Pin reset"),
    BOWL_OUT("Bowl: cw"),
    BOWL_IN("Bowl: ccw"),
    BOWL_STOP("Bowl: stop"),
    ARM_DOWN("Arm: down"),
    ARM_UP("Arm: up"),
    ARM_STOP("Arm: stop"),
    DOSAGE_ON("Dosage: on"),
    DOSAGE_OFF("Dosage: off"),
    TAP_ON("Tap: on"),
    TAP_OFF("Tap: off"),
    DRAIN_ON("Drain: on"),
    DRAIN_OFF("Drain: off"),
    DRYER_ON("Dryer: on"),
    DRYER_OFF("Dryer: off"),
    CAT_IN("Cat: in"),
    CAT_OUT("Cat: out"),
    WATER_LOW("Water: low"),
    WATER_HIGH("Water: high"),
    HEAT_YES("Overheat: yes"),
    HEAT_NO("Overheat: no"),;



    private final String response;

    Response(String response) {
        this.response = response;

    }

    public String getResponse() {
        return response;
    }

    public Command getCommand() {
        switch (this) {
            case BOWL_OUT:
                return Command.BOWL_OUT;
            case BOWL_IN:
                return Command.BOWL_IN;
            case BOWL_STOP:
                return Command.BOWL_STOP;
            case ARM_DOWN:
                return Command.ARM_DOWN;
            case ARM_UP:
                return Command.ARM_UP;
            case ARM_STOP:
                return Command.ARM_STOP;
            case DOSAGE_ON:
                return Command.DOSAGE_ON;
            case DOSAGE_OFF:
                return Command.DOSAGE_OFF;
            case TAP_ON:
                return Command.TAP_ON;
            case TAP_OFF:
                return Command.TAP_OFF;
            case DRAIN_ON:
                return Command.DRAIN_ON;
            case DRAIN_OFF:
                return Command.DRAIN_OFF;
            case DRYER_ON:
                return Command.DRYER_ON;
            case DRYER_OFF:
                return Command.DRYER_OFF;
            case CAT_IN:
                return Command.CAT;
            case CAT_OUT:
                return Command.CAT;
            case WATER_LOW:
                return Command.WATER;
            case WATER_HIGH:
                return Command.WATER;
            case HEAT_YES:
                return Command.HEAT;
            case HEAT_NO:
                return Command.HEAT;
            default:
                return null;
        }
    }

    public CommandResponseType getType() {
        if (response.contains("Bowl")) {
            return CommandResponseType.BOWL;
        }
        if (response.contains("Arm")) {
            return CommandResponseType.ARM;
        }
        if (response.contains("Tap")) {
            return CommandResponseType.TAP;
        }
        if (response.contains("Dosage")) {
            return CommandResponseType.DOSAGE;
        }
        if (response.contains("Dryer")) {
            return CommandResponseType.DRYER;
        }
        if (response.contains("Drain")) {
            return CommandResponseType.DRAIN;
        }
        if (response.contains("Cat")) {
            return CommandResponseType.CAT;
        }
        if (response.contains("Heat")) {
            return CommandResponseType.HEAT;
        }
        if (response.contains("Water")) {
            return CommandResponseType.WATER;
        }
        if (response.contains("***")) {
            return CommandResponseType.NAME;
        }
        if (response.contains("Pin")) {
            return CommandResponseType.RESET;
        }
        return null;
    }

    public static Response getByName(String name) {
        for (Response response : Response.values()) {
            if (response.getResponse().equals(name)) {
                return response;
            }
        }
        return null;
    }

    }
