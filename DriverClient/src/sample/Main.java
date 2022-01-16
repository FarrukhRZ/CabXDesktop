package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {
    private static Socket socket;
    private static BufferedReader reciever;
    private static PrintWriter sender;

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
        Main.reciever = reciever;
    }

    public static PrintWriter getSender() {
        return sender;
    }

    public static void setSender(PrintWriter sender) {
        Main.sender = sender;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Welcome to CabX");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        try{
            socket=new Socket("localhost",5000);
            reciever = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            sender = new PrintWriter(socket.getOutputStream(), true);
            sender.println("Driver");
            if(reciever.readLine().equals("connected"))
                System.out.println("Connection Established");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        launch(args);
    }
    public static void CreateThread(Controller controller)throws Exception{
        new Update(controller);
    }
}
