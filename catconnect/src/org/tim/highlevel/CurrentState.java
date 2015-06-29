package org.tim.highlevel;

import org.tim.lowlevel.Command;
import org.tim.lowlevel.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Tim
 * 28.06.2015
 * 10:29
 */
public class CurrentState {

    private long lastUpdate = Long.MAX_VALUE;
    private boolean consistentState = true;
    private long lastCatVisit = Long.MIN_VALUE;

    private final Response bowlDefault = Response.BOWL_STOP;
    private final Response armDefault = Response.ARM_STOP;
    private final Response tapDefault = Response.TAP_OFF;
    private final Response dosageDefault = Response.DOSAGE_OFF;
    private final Response drainDefault = Response.DRAIN_OFF;
    private final Response dryerDefault = Response.DRYER_OFF;
    private final Response catDefault = Response.CAT_OUT;
    private final Response waterDefault = Response.WATER_LOW;
    private final Response heatDefault = Response.HEAT_NO;

    private Response bowl = bowlDefault;
    private Response arm = armDefault;
    private Response dosage = dosageDefault;
    private Response tap = tapDefault;
    private Response drain = drainDefault;
    private Response dryer = dryerDefault;
    private Response cat = catDefault;
    private Response water = waterDefault;
    private Response heat = heatDefault;

    public CurrentState() {
    }

    public boolean isConsistentState() {
        return consistentState;
    }

    public void setConsistentState(boolean consistentState) {
        lastUpdate = System.currentTimeMillis();
        this.consistentState = consistentState;
    }

    public Response getArm() {
        return arm;
    }

    public void setArm(Response arm) {
        lastUpdate = System.currentTimeMillis();
        this.arm = arm;
    }

    public Response getDosage() {
        return dosage;
    }

    public void setDosage(Response dosage) {
        lastUpdate = System.currentTimeMillis();
        this.dosage = dosage;
    }

    public Response getTap() {
        return tap;
    }

    public void setTap(Response tap) {
        lastUpdate = System.currentTimeMillis();
        this.tap = tap;
    }

    public Response getDrain() {
        return drain;
    }

    public void setDrain(Response drain) {
        lastUpdate = System.currentTimeMillis();
        this.drain = drain;
    }

    public Response getDryer() {
        return dryer;
    }

    public void setDryer(Response dryer) {
        lastUpdate = System.currentTimeMillis();
        this.dryer = dryer;
    }

    public Response getCat() {
        return cat;
    }

    public void setCat(Response cat) {
        lastUpdate = System.currentTimeMillis();
        this.cat = cat;
    }

    public Response getWater() {
        return water;
    }

    public void setWater(Response water) {
        lastUpdate = System.currentTimeMillis();
        this.water = water;
    }

    public Response getHeat() {
        return heat;
    }

    public void setHeat(Response heat) {
        lastUpdate = System.currentTimeMillis();
        this.heat = heat;
    }

    public Response getBowl() {
        return bowl;
    }

    public void setBowl(Response bowl) {
        lastUpdate = System.currentTimeMillis();
        this.bowl = bowl;
    }

    public void set(Response response) {
        switch (response.getType()) {
            case NAME:
                break;
            case RESET:
                setConsistentState(false);
                break;
            case BOWL:
                setBowl(response);
                break;
            case ARM:
                setArm(response);
                break;
            case TAP:
                setTap(response);
                break;
            case DOSAGE:
                setDosage(response);
                break;
            case DRYER:
                setDryer(response);
                break;
            case DRAIN:
                setDrain(response);
                break;
            case CAT:
                setCat(cat);
                break;
            case HEAT:
                setHeat(response);
                break;
            case WATER:
                setWater(response);
                break;
        }
    }

    public boolean check(Command command) {
        List<Response> responses = command.getResponses();
        //Status commands have more then 1 responses and doesn't influence on permanent state
        if (responses.size() > 1) {
            return true;
        } else {
            Response response = responses.get(0);
            switch (response.getType()) {
                case BOWL:
                    return response.equals(getBowl());
                case ARM:
                    return response.equals(getArm());
                case TAP:
                    return response.equals(getTap());
                case DOSAGE:
                    return response.equals(getDosage());
                case DRYER:
                    return response.equals(getDryer());
                case DRAIN:
                    return response.equals(getDrain());
                default:
                    return false;
            }
        }
    }


    public List<Response> notDefaultStates() {
        ArrayList<Response> res = new ArrayList<>();
        if (!getBowl().equals(bowlDefault)) {
            res.add(getBowl());
        }
        if (!getArm().equals(armDefault)) {
            res.add(getArm());
        }
        if (!getTap().equals(tapDefault)) {
            res.add(getTap());
        }
        if (!getDrain().equals(drainDefault)) {
            res.add(getDrain());
        }
        if (!getDosage().equals(dosageDefault)) {
            res.add(getDosage());
        }
        if (!getDryer().equals(dryerDefault)) {
            res.add(getDryer());
        }
        return res;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
}
