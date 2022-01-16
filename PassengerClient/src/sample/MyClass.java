package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClass extends Application {
    private static Socket socket;
    private static BufferedReader reciever;
    private static PrintWriter sender;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("passenger1.fxml"));
        primaryStage.setTitle("Become a CabX Member");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static BufferedReader getReciever() {
        return reciever;
    }

    public static void setReciever(BufferedReader reciever) {
        MyClass.reciever = reciever;
    }

    public static PrintWriter getSender() {
        return sender;
    }

    public static void setSender(PrintWriter sender) {
        MyClass.sender = sender;
    }

    public static void main(String[] args) throws Exception
    {
        socket=new Socket("localhost",5000);
        reciever = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        sender = new PrintWriter(socket.getOutputStream(), true);
        sender.println("Passenger");
        if(reciever.readLine().equals("connected")){
            System.out.println("Connection Established");
        }
        launch(args);
    }
    public static void CreateThread(Controller controller)throws Exception{
        new Updated(controller);
    }
}
