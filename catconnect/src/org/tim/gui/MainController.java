package org.tim.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.tim.highlevel.HighLevelClient;
import org.tim.lowlevel.LowLevelClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.tim.lowlevel.Command.*;

/**
 * User: Tim
 * 07.06.2015
 * 9:01
 */
public class MainController implements Initializable{

    @FXML
    private TextArea console;

    private HighLevelClient client;

    public MainController() {
    }

    public MainController(InetAddress address, int port) {
        try {
            client = new HighLevelClient(Executors.newCachedThreadPool(), new LowLevelClient(address, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void armUp() {
        client.execute(ARM_UP);
    }

    @FXML
    public void armDown() {
        client.execute(ARM_DOWN);
    }

    @FXML
    public void armStop() {
        client.execute(ARM_STOP);
    }

    @FXML
    public void armUpFull() {
        client.execute(ARM_UP);
        timeoutSec(19);
        client.execute(ARM_STOP);
    }

    @FXML
    public void armUpAndShake() {
        client.execute(ARM_UP);
        timeoutSec(8);
        client.execute(ARM_DOWN);
        timeoutSec(5);
        client.execute(ARM_UP);
        timeoutSec(14);
        client.execute(ARM_STOP);
    }

    @FXML
    public void armUpLittleBit() {
        client.execute(ARM_UP);
        timeoutMillis(500);
        client.execute(ARM_STOP);
    }

    @FXML
    public void armDownFull() {
        client.execute(ARM_DOWN);
        timeoutSec(21);
        client.execute(ARM_STOP);
    }

    @FXML
    public void armDownLittleBit() {
        client.execute(ARM_DOWN);
        timeoutMillis(500);
        client.execute(ARM_STOP);
    }

    @FXML
    public void armStatus() {
        client.execute(ARM_STATUS);
    }

    @FXML
    public void bowlIn() {
        client.execute(BOWL_IN);
    }

    @FXML
    public void bowlOut() {
        client.execute(BOWL_OUT);
    }

    @FXML
    public void bowlStop() {
        client.execute(BOWL_STOP);
    }

    @FXML
    public void bowlStatus() {
        client.execute(BOWL_STATUS);
    }

    @FXML
    public void dosageOn() {
        client.execute(DOSAGE_ON);
    }

    @FXML
    public void dosageOff() {
        client.execute(DOSAGE_OFF);
    }

    @FXML
    public void dosageStatus() {
        client.execute(DOSAGE_STATUS);
    }

    @FXML
    public void dryerOn() {
        client.execute(DRYER_ON);
    }

    @FXML
    public void dryerOff() {
        client.execute(DRYER_OFF);
    }

    @FXML
    public void dryerStatus() {
        client.execute(DRYER_STATUS);
    }

    @FXML
    public void dryer35m() {
        client.execute(DRYER_ON);
        timeoutMin(35);
        shutdownAll();
        timeoutSec(3);
        armUpFull();
        timeoutSec(3);
        shutdownAll();
    }

    @FXML
    public void tapOn() {
        client.execute(TAP_ON);
    }

    @FXML
    public void tapOff() {
        client.execute(TAP_OFF);
    }

    @FXML
    public void tapStatus() {
        client.execute(TAP_STATUS);
    }

    @FXML
    public void drainOn() {
        client.execute(DRAIN_ON);
    }

    @FXML
    public void drainOff() {
        client.execute(DRAIN_OFF);
    }

    @FXML
    public void drainStatus() {
        client.execute(DRAIN_STATUS);
    }

    @FXML
    public void waterLevelStatus() {
        client.execute(WATER);
    }

    @FXML
    public void overheatStatus() {
        client.execute(HEAT);
    }

    @FXML
    public void catStatus() {
        client.execute(CAT);
    }

    @FXML
    public void program() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                bowlOut();
                armDownFull();
                timeoutSec(1);
                bowlIn();
                timeoutSec(30);
//                armUpAndShake();
                armUpFull();
                armDownFull();
                timeoutSec(30);
                armUpAndShake();
                armDownFull();
                timeoutSec(1);
                bowlOut();
                timeoutSec(1);
                dosageOn();
                timeoutSec(30);
                tapOn();
                timeoutSec(30);
                dosageOff();
                timeoutSec(30);
                tapOff();
                drainOn();
                timeoutMin(4);
                drainOff();
                dryerOn();
                timeoutSec(15);
                drainOn();
                timeoutSec(45);
                drainOff();
                timeoutMin(45);
                armUpFull();
                shutdownAll();
            }
        });
        thread.start();


    }

    @FXML
    public void shutdownAll() {
        client.execute(ARM_STOP);
        client.execute(BOWL_STOP);
        client.execute(TAP_OFF);
        client.execute(DRAIN_OFF);
        client.execute(DRYER_OFF);
        client.execute(DOSAGE_OFF);
    }


    private void timeoutSec(int value) {
        try {
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void timeoutMin(int value) {
        try {
            TimeUnit.MINUTES.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void timeoutMillis(int value) {
        try {
            TimeUnit.MILLISECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
