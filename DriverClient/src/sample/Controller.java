package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class Controller implements Initializable {
    @FXML public TextField log_uname;
    @FXML public PasswordField log_password;
    @FXML public Label loginsuccess;
    @FXML public Button Help,userDetails,loadPassenger;
    @FXML public TableView<TableData> Profile;
    @FXML public TableView<RideData> Rides;
    @FXML public TableColumn<TableData,String> ProfColumn;
    @FXML public TableColumn<RideData,String> RideID,RidePickup,RideDropoff,RideDName,RideDate,RideFare;
    @FXML public Label l1,l2,l3,l4;
    int caller;
    public Controller(int x){
        caller=x;
    }
    public Controller(){
        this(0);
    }
    public void login(ActionEvent event)throws Exception{
        BufferedReader reader=Main.getReciever();
        PrintWriter writer=Main.getSender();
        writer.println("1");
        writer.println(log_uname.getText()+" "+log_password.getText());
        if(reader.readLine().equals("successful")){
            loginsuccess.setText("Login Successful");
            Parent root = FXMLLoader.load(getClass().getResource("drivermain.fxml"));
            Stage newWindow=(Stage) loginsuccess.getScene().getWindow();
            newWindow.setTitle("Welcome to CabX!");
            newWindow.setScene(new Scene(root, 600, 400));
        }
        else{
            loginsuccess.setText("Wrong Username/Password");
        }

    }
    public void displayError(ActionEvent event){
        infoBox("Go to nearest CabX Customer Care Centre to Register","Join US!",null);
    }
    public void drivermain(ActionEvent event)throws Exception{
        PrintWriter writer=Main.getSender();
        BufferedReader reader=Main.getReciever();
        Button clicked=(Button) event.getSource();
        if(clicked.getId().equals("findRide")){
            writer.println("3");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("blank.fxml"));
            Controller controller=new Controller(2);
            loader.setController(controller);
            Parent root=loader.load();
            Stage newWindow=(Stage) Help.getScene().getWindow();
            newWindow.setTitle("Ride Details");
            newWindow.setScene(new Scene(root,600, 400));

        }
        else if(clicked.getId().equals("Help")){
            Parent root = FXMLLoader.load(getClass().getResource("driverhelp.fxml"));
            Stage newWindow=(Stage) Help.getScene().getWindow();
            newWindow.setTitle("Help Window");
            newWindow.setScene(new Scene(root, 600, 400));
        }
        else{
            writer.println("2");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDetails.fxml"));
            Controller controller=new Controller(1);
            loader.setController(controller);
            Parent root=loader.load();
            Stage newWindow=(Stage) Help.getScene().getWindow();
            newWindow.setTitle("Details");
            newWindow.setScene(new Scene(root, 900, 600));
        }
    }
    public void LoadPassenger(ActionEvent event)throws Exception{
        BufferedReader reader=Main.getReciever();
        String details=reader.readLine();
        System.out.println(details);
        StringTokenizer st=new StringTokenizer(details);
        l1.setText("Ride Found!");
        l2.setText("Name: ".concat(st.nextToken()));
        l3.setText("Pickup: "+st.nextToken());
        l4.setText("Dropoff: "+st.nextToken());
        loadPassenger.setText("Start Ride");
        loadPassenger.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                PrintWriter sender=Main.getSender();
                sender.println("4");
                try{
                    String reply=reader.readLine();
                    if(reply.equals("no")){
                        infoBox("Ride is cancelled by the Passenger!","Failed",null);
                        Parent root = FXMLLoader.load(getClass().getResource("drivermain.fxml"));
                        Stage newWindow=(Stage) loadPassenger.getScene().getWindow();
                        newWindow.setTitle("Welcome to CabX!");
                        newWindow.setScene(new Scene(root, 600  , 400));

                    }
                    else{
                        l1.setText("You're on your way");
                        infoBox("Ride Started","Success",null);
                        loadPassenger.setText("Finish Ride");
                        loadPassenger.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                sender.println("5");
                                try{
                                    Parent root = FXMLLoader.load(getClass().getResource("drivermain.fxml"));
                                    Stage newWindow=(Stage) loadPassenger.getScene().getWindow();
                                    newWindow.setTitle("Welcome to CabX!");
                                    newWindow.setScene(new Scene(root, 600  , 400));
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                }

                            }

                        });

                    }
                }catch(Exception ignored){

                }
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Check");
        if(caller==1){
            BufferedReader reciever=Main.getReciever();
            try{
                String details=reciever.readLine();
                System.out.println(details);
                StringTokenizer st=new StringTokenizer(details);
                ProfColumn.setCellValueFactory(new PropertyValueFactory("s1"));
                ObservableList<TableData> credentials=FXCollections.observableArrayList();
                while(st.hasMoreTokens()){
                    String cellval=st.nextToken();
                    System.out.println(cellval);
                    credentials.add(new TableData(cellval));
                }
                System.out.println("check");
                Profile.setItems(credentials);
                //System.out.println("check");
                String msg;
                RideID.setCellValueFactory(new PropertyValueFactory("rideID"));
                RideDate.setCellValueFactory(new PropertyValueFactory("rideDate"));
                RideDropoff.setCellValueFactory(new PropertyValueFactory("rideDropoff"));
                RidePickup.setCellValueFactory(new PropertyValueFactory("ridePickup"));
                RideDName.setCellValueFactory(new PropertyValueFactory("rideDName"));
                RideFare.setCellValueFactory(new PropertyValueFactory("rideFare"));
                ObservableList<RideData> RideDetails=FXCollections.observableArrayList();
                //System.out.println("check");
                while(true){
                    msg=reciever.readLine();
                    if(msg.equals("exit"))
                        break;
                    st=new StringTokenizer(msg);
                    System.out.println(st.countTokens());
                    System.out.println(msg);
                    RideDetails.add(new RideData(st.nextToken(),st.nextToken(),st.nextToken(),
                            st.nextToken(),st.nextToken(),st.nextToken()));
                }
                System.out.println("check");
                Rides.setItems(RideDetails);
            }
            catch (Exception e){
                System.out.println("Initialization error "+e.getMessage());
            }

        }
        else if(caller==2){
            l1.setText("Searching a nearby passenger .  . .");
            try{
                Main.CreateThread(this);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    public void callMain(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("drivermain.fxml"));
        Stage newWindow=(Stage) Profile.getScene().getWindow();
        newWindow.setTitle("Welcome to CabX!");
        newWindow.setScene(new Scene(root, 600  , 400));
    }
}
