package org.tim;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.tim.lowlevel.LowLevelClient;
import org.tim.lowlevel.Command;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * User: Marianna
 * 05.04.2015
 * 21:57
 */
public class LitterClient extends Application {

    private static LowLevelClient lowLevelClient = null;


    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByAddress(
                    new byte[]{(byte) 192, (byte) 168, 1, (byte) 177});
            int port = 80;
            lowLevelClient = new LowLevelClient(address,
                    port);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Control!");
        Button bowl = new Button("Чаша");
        Button bowlIn = new Button("В совок");
        Button bowlOut = new Button("Из совка");
        Button bowlStop = new Button("Выключить");
        Button arm = new Button("Совок");
        Button armDown = new Button("Вниз");
        Button armUp = new Button("Вверх");
        Button armStop = new Button("Выключить");
        Button dosage = new Button("Шампунь");
        Button dosageOn = new Button("Включить");
        Button dosageOff = new Button("Выключить");
        Button tap = new Button("Вода");
        Button tapOn = new Button("Включить");
        Button tapOff = new Button("Выключить");
        Button drain = new Button("Дренаж");
        Button drainOn = new Button("Включить");
        Button drainOff = new Button("Выключить");
        Button dryer = new Button("Фен");
        Button dryerOn = new Button("Включить");
        Button dryerOff = new Button("Выключить");
        Button cat = new Button("Кот");
        Button water = new Button("Уровень воды");
        Button heat = new Button("Перегрев");
        Button program = new Button("Полная программа");
        Button shakeUp = new Button("Вверх и стрятси");
        Button fullDown = new Button("Полностью вниз");


        shakeUp.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.ARM_UP);
                TimeUnit.SECONDS.sleep(8);
                lowLevelClient.send(Command.ARM_DOWN);
                TimeUnit.SECONDS.sleep(5);
                lowLevelClient.send(Command.ARM_UP);
                TimeUnit.SECONDS.sleep(14);
                lowLevelClient.send(Command.ARM_STOP);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        fullDown.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.ARM_DOWN);
                TimeUnit.SECONDS.sleep(19);
                lowLevelClient.send(Command.ARM_STOP);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        bowl.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.BOWL_STATUS);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        bowlIn.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.BOWL_IN);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        bowlOut.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.BOWL_OUT);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        bowlStop.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.BOWL_STOP);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        arm.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.ARM_STATUS);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        armDown.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.ARM_DOWN);
            } catch (IOException |
                    InterruptedException e) {
                e.printStackTrace();
            }
        });
        armUp.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.ARM_UP);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        armStop.setOnAction(
                event -> {
                    try {
                        lowLevelClient.send(Command.ARM_STOP);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        dosage.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DOSAGE_STATUS);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        dosageOn.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DOSAGE_ON);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        dosageOff.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DOSAGE_OFF);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        tap.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.TAP_STATUS);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        tapOn.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.TAP_ON);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        tapOff.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.TAP_OFF);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        drain.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DRAIN_STATUS);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        drainOn.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DRAIN_ON);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        drainOff.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DRAIN_OFF);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        dryer.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DRYER_STATUS);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        dryerOn.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DRYER_ON);
                TimeUnit.MINUTES.sleep(20);
                lowLevelClient.send(Command.DRYER_OFF);
                lowLevelClient.send(Command.BOWL_STOP);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        dryerOff.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.DRYER_OFF);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        cat.setOnAction(
                event -> {
                    try {
                        lowLevelClient.send(Command.CAT);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        water.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.WATER);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        heat.setOnAction(event -> {
            try {
                lowLevelClient.send(Command.HEAT);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        program.setOnAction(
                event -> {
                    try {
                        lowLevelClient.send(Command.ARM_DOWN);
                        TimeUnit.SECONDS.sleep(19);
                        lowLevelClient.send(Command.ARM_STOP);
                        lowLevelClient.send(Command.BOWL_OUT);
                        lowLevelClient.send(Command.TAP_ON);
                        lowLevelClient.send(Command.DOSAGE_ON);
                        TimeUnit.SECONDS.sleep(12);
                        lowLevelClient.send(Command.DOSAGE_OFF);
                        TimeUnit.MINUTES.sleep(3);
                        lowLevelClient.send(Command.TAP_OFF);
                        lowLevelClient.send(Command.DRAIN_ON);
                        TimeUnit.MINUTES.sleep(2);
                        lowLevelClient.send(Command.DRAIN_OFF);
                        lowLevelClient.send(Command.TAP_ON);
                        TimeUnit.MINUTES.sleep(2);
                        lowLevelClient.send(Command.TAP_OFF);
                        lowLevelClient.send(Command.DRAIN_ON);
                        TimeUnit.MINUTES.sleep(2);
                        lowLevelClient.send(Command.DRAIN_OFF);
                        TimeUnit.SECONDS.sleep(15);
                        lowLevelClient.send(Command.DRAIN_ON);
                        TimeUnit.SECONDS.sleep(45);
                        lowLevelClient.send(Command.DRAIN_OFF);
                        lowLevelClient.send(Command.DRYER_ON);
                        TimeUnit.MINUTES.sleep(24);
                        lowLevelClient.send(Command.DRYER_OFF);
                        lowLevelClient.send(Command.ARM_UP);
                        TimeUnit.SECONDS.sleep(18);
                        lowLevelClient.send(Command.ARM_STOP);
                        lowLevelClient.send(Command.BOWL_STOP);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                });


        GridPane root = new GridPane();
        root.addRow(0, bowl, bowlIn, bowlOut, bowlStop);
        root.addRow(1, arm, armUp, armDown, armStop);
        root.addRow(2, dosage, dosageOn, dosageOff);
        root.addRow(3, tap, tapOn, tapOff);
        root.addRow(4, drain, drainOn, drainOff);
        root.addRow(5, dryer, dryerOn, dryerOff);
        root.addRow(6, cat, water, heat);
        root.addRow(7, program, shakeUp, fullDown);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}

