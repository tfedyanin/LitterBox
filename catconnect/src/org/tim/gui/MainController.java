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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.tim.lowlevel.Command.*;

/**
 * User: Tim
 * 07.06.2015
 * 9:01
 */
public class MainController implements Initializable {


    @FXML
    private TextArea console;

    private HighLevelClient client;

    public MainController() {
    }

    public MainController(InetAddress address, int port) {
        try {
            client = new HighLevelClient(new LowLevelClient(address, port));
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
        timeoutSec(3);
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
                timeoutSec(10);
                bowlIn();
                timeoutSec(45);
                armUpAndShake();
//                armUpFull();
                armDownFull();
                timeoutSec(45);
                armUpAndShake();
//                armUpFull();
                bowlOut();
                tapOn();
                armDownFull();
                timeoutMin(2);
                tapOff();
                drainOn();
                timeoutSec(100);
                drainOff();
                tapOn();
                dosageOn();
                timeoutSec(20);
                dosageOff();
                timeoutSec(40);
                tapOff();
                drainOn();
                dryerOn();
                timeoutMin(2);
                timeoutSec(30);
                drainOff();
                timeoutSec(30);
                drainOn();
                timeoutSec(30);
                drainOff();
                timeoutMin(16);
                armUpFull();
                dryerOff();
                bowlStop();
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
