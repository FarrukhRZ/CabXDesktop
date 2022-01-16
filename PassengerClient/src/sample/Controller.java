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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class Controller implements Initializable{
    private final int caller;
    Socket socket;
    @FXML private TextField First;
    @FXML private TextField Last;
    @FXML private TextField Age;
    @FXML private TextField Email;
    @FXML private TextField Contact;
    @FXML public Button Submit,help,userhist,regcomp,book;
    @FXML public Button Confirm;
    @FXML public Button LoadRegister;
    @FXML public Button Login;
    @FXML public PasswordField pass;
    @FXML public TextField uname;
    @FXML public PasswordField confirmPass;
    @FXML public TextField Username;
    @FXML public PasswordField Password;
    @FXML public TextField log_uname,pickup,dropoff;
    @FXML public PasswordField log_password,confirmP;
    @FXML public Label loginsuccess;
    @FXML public Hyperlink forgotpass;
    @FXML public TableView<TableData> Profile;
    @FXML public TableView<RideData> Rides;
    @FXML public TableColumn<TableData,String> ProfColumn;
    @FXML public TextField CP_name;
    @FXML public TableColumn<RideData,String> RideID, RidePickup, RideDropoff, RideDName, RideDate, RideFare;


    public Controller(int x){
        caller=x;
    }
    public Controller(){
        this(0);
    }
    public void loadRegister(ActionEvent actionEvent)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("register_start.fxml"));
        Stage newWindow=(Stage) LoadRegister.getScene().getWindow();
        newWindow.setTitle("Become a CabX member");
        newWindow.setScene(new Scene(root, 765, 500));
    }
    public int submit(ActionEvent actionEvent) throws  Exception {
        if(First.getText().isEmpty()){
            infoBox("First Name cannot be empty","Syntax Error",null);
            return 0;
        }
        else if(Last.getText().isEmpty()){
            infoBox("Last Name cannot be empty","Syntax Error",null);
            return 0;
        }
        else if(Age.getText().isEmpty()){
            infoBox("Age cannot be empty","Syntax Error",null);
            return 0;
        }
        else if(Email.getText().isEmpty()){
            infoBox("Email cannot be empty","Syntax Error",null);
            return 0;
        }
        else if(Contact.getText().isEmpty()){
            infoBox("Contact cannot be empty","Syntax Error",null);
            return 0;
        }
        else{
            try{
                Integer.valueOf(Age.getText());
            }
            catch(Exception e){
                infoBox("Invalid Age","Syntax Error",null);
                e.fillInStackTrace();
                return 0;
            }
        }
        PrintWriter writer=MyClass.getSender();
        writer.println("3");
        writer.println(First.getText()+" "+Email.getText()+" "+Contact.getText());
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage newWindow=(Stage) Submit.getScene().getWindow();
        newWindow.setTitle("Become a CabX member");
        newWindow.setScene(new Scene(root, 765, 500));
        return 1;
    }
    public void login(ActionEvent actionEvent)throws Exception{
        socket=MyClass.getSocket();
        System.out.println(socket.getPort());
        PrintWriter sender=MyClass.getSender();
        BufferedReader reciever=MyClass.getReciever();
        if(socket.isConnected()){
            //loginsuccess.setText("Login Successful");
            sender.println("1");
            sender.println(log_uname.getText()+" "+log_password.getText());
            if(reciever.readLine().equals("successful")){
                loginsuccess.setText("Login Successful");
                Parent root = FXMLLoader.load(getClass().getResource("passengermain.fxml"));
                Stage newWindow=(Stage) loginsuccess.getScene().getWindow();
                newWindow.setTitle("Welcome to CabX!");
                newWindow.setScene(new Scene(root, 600, 400));
            }
            else{
                loginsuccess.setText("Wrong Username/Password");
            }
        }

    }
    public void confirm(ActionEvent actionEvent)throws Exception{
        if(pass.getText().isEmpty() || uname.getText().isEmpty() ||confirmPass.getText().isEmpty()){
            infoBox(" fill all fields","Empty Fields",null);
        }
        else if(!pass.getText().equals(confirmPass.getText())){
            System.out.println(pass.getText());
            System.out.println(confirmPass.getText());
            infoBox("Passwords do not match","Password Error",null);
        }
        else{
            boolean numCheck=false;
            for(char c : pass.getText().toCharArray()){
                if(c>='0'&&c<='9')
                    numCheck=true;
            }
            if(!numCheck){
                infoBox("Password must contain at least 1 number",
                        "Password Error",null);
            }
            PrintWriter writer=MyClass.getSender();
            BufferedReader reader=MyClass.getReciever();
            writer.println(uname.getText()+" "+pass.getText());
            if(reader.readLine().equals("no")){
                infoBox("Username already taken!","Common Username",null);
                return;
            }
            Parent root = FXMLLoader.load(getClass().getResource("passengermain.fxml"));
            Stage newWindow=(Stage) Confirm.getScene().getWindow();
            newWindow.setTitle("Welcome to CabX!");
            newWindow.setScene(new Scene(root, 600 , 400));
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
    public void PassengerMain(ActionEvent event)throws Exception{
        socket=MyClass.getSocket();
        PrintWriter sender=MyClass.getSender();
        BufferedReader reciever=MyClass.getReciever();
        Button clicked=(Button) event.getSource();
        if(clicked.getId().equals("book")){
            Parent root = FXMLLoader.load(getClass().getResource("cartype.fxml"));
            Stage newWindow=(Stage) help.getScene().getWindow();
            newWindow.setTitle("Ride Details");
            newWindow.setScene(new Scene(root, 600, 400));

        }
        else if(clicked.getId().equals("help")){
            Parent root = FXMLLoader.load(getClass().getResource("help.fxml"));
            Stage newWindow=(Stage) help.getScene().getWindow();
            newWindow.setTitle("Help");
            newWindow.setScene(new Scene(root, 600, 400));
        }
        else if(clicked.getId().equals("userhist")){
            sender.println("2");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDetails.fxml"));
            Controller controller=new Controller(1);
            loader.setController(controller);
            Parent root=loader.load();
            Stage newWindow=(Stage) help.getScene().getWindow();
            newWindow.setTitle("Details");
            newWindow.setScene(new Scene(root, 900, 600));
        }
        else{
            System.out.println("Register Complain");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(caller==1){
            BufferedReader reciever=MyClass.getReciever();
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
                //System.out.println("check");
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
            try{
                l1.setText("Finding Your Ride, Please Wait");
                //MyClass.CreateThread(this);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return;
    }
    public void callMain(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("passengermain.fxml"));
        Stage newWindow=(Stage) Profile.getScene().getWindow();
        newWindow.setTitle("Welcome to CabX!");
        newWindow.setScene(new Scene(root, 600  , 400));
    }
    @FXML public Label l1,l2,l3,l4;
    @FXML Button loadDriver;
    public void callride(ActionEvent event)throws Exception{
        if(pickup.getText().isEmpty() || dropoff.getText().isEmpty()){
            infoBox("Fill all fields","Empty Fields", null);
            return;
        }
        BufferedReader reciever=MyClass.getReciever();
        PrintWriter sender=MyClass.getSender();
        sender.println("4");
        sender.println(pickup.getText()+" "+dropoff.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("blank.fxml"));
        Controller controller=new Controller(2);
        loader.setController(controller);
        Parent root=loader.load();
        Stage newWindow=(Stage) pickup.getScene().getWindow();
        newWindow.setTitle("Ride Details");
        newWindow.setScene(new Scene(root, 600, 400));
    }
    public void LoadDriver(ActionEvent event)throws Exception{
        BufferedReader reader=MyClass.getReciever();
        String details=reader.readLine();
        System.out.println(details);
        StringTokenizer st=new StringTokenizer(details);
        l1.setText("Driver Found!");
        l2.setText("Name: "+st.nextToken());
        l3.setText("Rating: "+st.nextToken());
        l4.setText("Arriving in 5 minutes!");
        loadDriver.setText("Cancel Ride");
        loadDriver.setOnAction(event1 -> {
            PrintWriter sender=MyClass.getSender();
            sender.println("5");
            try{
                String reply=reader.readLine();
                if(reply.equals("no")){
                    infoBox("Ride is already Started","Cancellation Failed!",null);
                }
                else if(reply.equals("comp")){
                    infoBox("Thankyou for riding with us!","Ride Completed",null);
                    Parent root = FXMLLoader.load(getClass().getResource("passengermain.fxml"));
                    Stage newWindow=(Stage) Profile.getScene().getWindow();
                    newWindow.setTitle("Welcome to CabX!");
                    newWindow.setScene(new Scene(root, 600  , 400));
                }
                else {
                    infoBox("Ride Cancelled","Success",null);
                    Parent root = FXMLLoader.load(getClass().getResource("passengermain.fxml"));
                    Stage newWindow=(Stage) loadDriver.getScene().getWindow();
                    newWindow.setTitle("Welcome to CabX!");
                    newWindow.setScene(new Scene(root, 600, 400));

                }
            }catch(Exception e){

            }
        });
    }
    public void loadCP(ActionEvent event)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("changepass.fxml"));
        Stage newWindow=(Stage) forgotpass.getScene().getWindow();
        newWindow.setTitle("Password Change");
        newWindow.setScene(new Scene(root, 600, 400));
    }
    public void changePass(ActionEvent event)throws Exception{
        BufferedReader reader=MyClass.getReciever();
        PrintWriter writer=MyClass.getSender();
        writer.println("6");
        String user=CP_name.getText();
        String cPass =log_password.getText();
        String pass=confirmP.getText();
        writer.println(pass+" "+user);
        boolean nCheck=false;
        for (char c: cPass.toCharArray()){
            if(c<='9' && c>='0'){
                nCheck=true;
            }
        }
        if(cPass.isEmpty() || pass.isEmpty()){
            infoBox("Fill all fields","Failed",null);
            return;
        }
        else if (!cPass.equals(pass)){
            infoBox("Passwords donot match","Failed",null);
            return;
        }
        else if(!nCheck){
            infoBox("Password Must contain a number","Failed",null);
            return;
        }
        if(reader.readLine().equals("1")){
            infoBox("Password Changed Successfully!","Success",null);
            Parent root = FXMLLoader.load(getClass().getResource("passenger1.fxml"));
            Stage newWindow=(Stage) confirmP.getScene().getWindow();
            newWindow.setTitle("Welcome to CabX!");
            newWindow.setScene(new Scene(root, 600  , 400));
        }
        else{
            infoBox("User Doesn't Exist!","Failed",null);
        }
    }
}
