package sample;

import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Update extends Thread{
    Controller controller;
    public Update(Controller controller)throws Exception{
        this.controller=controller;
    }
    @Override
    public void run(){
        System.out.println("here");
        try{
            BufferedReader reader=Main.getReciever();
            String details=reader.readLine();
            StringTokenizer st=new StringTokenizer(details);
            controller.l1.setText("Driver Found!");
            controller.l2.setText("Name: "+st.nextToken());
            controller.l3.setText("Pickup: "+st.nextToken());
            controller.l4.setText("Dropoff: "+st.nextToken());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("done");
    }
}
