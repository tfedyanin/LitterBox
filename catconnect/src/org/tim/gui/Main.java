package org.tim.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.InetAddress;

/**
 * User: Tim
 * 07.06.2015
 * 20:46
 */
public class Main extends Application{


    final private int port = 80;
    final private byte[] addr = new byte[]{(byte) 192, (byte) 168, 1, (byte) 177};

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final InetAddress address = InetAddress.getByAddress(addr);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                if (param == MainController.class) {
                    return new MainController(address, port);
                } else {
                    try {
                        return param.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Parent root = loader.load();
        primaryStage.setTitle("Кошачий туалет");
        primaryStage.setScene(new Scene(root, 300, 600));
        primaryStage.show();
    }
}
