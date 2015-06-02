package org.tim;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marianna on 05.04.2015.
 */
public class LitterClient extends Application {

    private static LowLevelClient lowLevelClient = null;


    public static void main(String[] args) {
        try {
            lowLevelClient = new LowLevelClient(InetAddress.getByAddress(
                    new byte[]{(byte) 192, (byte) 168, 1, (byte) 177}),
                    80);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Control!");
        Button bowl = new Button("Чаша");
        Button bowlcw_in = new Button("В совок");
        Button bowlccw_out = new Button("Из совка");
        Button bowlstop = new Button("Выключить");
        Button arm = new Button("Совок");
        Button armdown = new Button("Вниз");
        Button armup = new Button("Вверх");
        Button armstop = new Button("Выключить");
        Button dosage = new Button("Шампунь");
        Button dosageon = new Button("Включить");
        Button dosageoff = new Button("Выключить");
        Button tap = new Button("Вода");
        Button tapon = new Button("Включить");
        Button tapoff = new Button("Выключить");
        Button drain = new Button("Дренаж");
        Button drainon = new Button("Включить");
        Button drainoff = new Button("Выключить");
        Button dryer = new Button("Фен");
        Button dryeron = new Button("Включить");
        Button dryeroff = new Button("Выключить");
        Button cat = new Button("Кот");
        Button water = new Button("Уровень воды");
        Button heat = new Button("Перегрев");
        Button program = new Button("Полная программа");
        Button shakeUp = new Button("Вверх и стрятси");
        Button fullDown = new Button("Полностью вниз");


        shakeUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_UP);
                    TimeUnit.SECONDS.sleep(8);
                    lowLevelClient.send(Request.ARM_DOWN);
                    TimeUnit.SECONDS.sleep(5);
                    lowLevelClient.send(Request.ARM_UP);
                    TimeUnit.SECONDS.sleep(14);
                    lowLevelClient.send(Request.ARM_STOP);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fullDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_DOWN);
                    TimeUnit.SECONDS.sleep(19);
                    lowLevelClient.send(Request.ARM_STOP);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bowl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.BOWL_STATUS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        bowlcw_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.BOWL_IN);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        bowlccw_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.BOWL_OUT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        bowlstop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.BOWL_STOP);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        arm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_STATUS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        armdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_DOWN);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        armup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_UP);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        armstop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_STOP);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        dosage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DOSAGE_STATUS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        dosageon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DOSAGE_ON);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        dosageoff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DOSAGE_OFF);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        tap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.TAP_STATUS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        tapon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.TAP_ON);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        tapoff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.TAP_OFF);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        drain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DRAIN_STATUS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        drainon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DRAIN_ON);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        drainoff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DRAIN_OFF);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        dryer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DRYER_STATUS);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        dryeron.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DRYER_ON);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        dryeroff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.DRYER_OFF);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        cat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.CAT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        water.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.WATER);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });
        heat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.HEAT);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EchoException e) {
                    e.printStackTrace();
                }
            }
        });

        program.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    lowLevelClient.send(Request.ARM_DOWN);
                    TimeUnit.SECONDS.sleep(19);
                    lowLevelClient.send(Request.ARM_STOP);
                    lowLevelClient.send(Request.BOWL_OUT);
                    lowLevelClient.send(Request.TAP_ON);
                    lowLevelClient.send(Request.DOSAGE_ON);
                    TimeUnit.SECONDS.sleep(12);
                    lowLevelClient.send(Request.DOSAGE_OFF);
                    TimeUnit.MINUTES.sleep(3);
                    lowLevelClient.send(Request.TAP_OFF);
                    lowLevelClient.send(Request.DRAIN_ON);
                    TimeUnit.MINUTES.sleep(5);
                    lowLevelClient.send(Request.DRAIN_OFF);
                    lowLevelClient.send(Request.TAP_ON);
                    TimeUnit.MINUTES.sleep(2);
                    lowLevelClient.send(Request.TAP_OFF);
                    lowLevelClient.send(Request.DRAIN_ON);
                    TimeUnit.MINUTES.sleep(5);
                    lowLevelClient.send(Request.DRAIN_OFF);
                    TimeUnit.SECONDS.sleep(15);
                    lowLevelClient.send(Request.DRAIN_ON);
                    TimeUnit.SECONDS.sleep(45);
                    lowLevelClient.send(Request.DRAIN_OFF);
                    TimeUnit.SECONDS.sleep(15);
                    lowLevelClient.send(Request.DRAIN_ON);
                    TimeUnit.SECONDS.sleep(15);
                    lowLevelClient.send(Request.DRAIN_OFF);
                    TimeUnit.SECONDS.sleep(15);
                    lowLevelClient.send(Request.DRAIN_ON);
                    TimeUnit.SECONDS.sleep(60);
                    lowLevelClient.send(Request.DRAIN_OFF);
                    lowLevelClient.send(Request.DRYER_ON);
                    TimeUnit.MINUTES.sleep(24);
                    lowLevelClient.send(Request.DRYER_OFF);
                    lowLevelClient.send(Request.ARM_UP);
                    TimeUnit.SECONDS.sleep(18);
                    lowLevelClient.send(Request.ARM_STOP);
                    lowLevelClient.send(Request.BOWL_STOP);
                } catch (EchoException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        GridPane root = new GridPane();
        root.addRow(0, bowl, bowlcw_in, bowlccw_out, bowlstop);
        root.addRow(1, arm, armup, armdown, armstop);
        root.addRow(2, dosage, dosageon, dosageoff);
        root.addRow(3, tap, tapon, tapoff);
        root.addRow(4, drain, drainon, drainoff);
        root.addRow(5, dryer, dryeron, dryeroff);
        root.addRow(6, cat, water, heat);
        root.addRow(7, program, shakeUp, fullDown);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}

