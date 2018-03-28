package com.estimote.notification.estimote;

/**
 * Created by xizhouli on 2018/3/23.
 */

public class BeaconAttributes {
    private String message;
    private String name;
    private String reminderTime;
    private boolean on;

    public BeaconAttributes(String msg, String name, String remT, boolean on){
        this.message = msg;
        this.name = name;
        this.reminderTime = remT;
        this.on = on;
    }

    public String getMessage(){
        return this.message;
    }

    public String getName(){
        return this.name;
    }

    public String getRemt(){
        return this.getRemt();
    }

    public boolean on(){
        return this.on;
    }

    public void setMessage(String msg){
        this.message = msg;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setToggle(boolean on){
        this.on = on;
    }
    public void setreminder(String time){
        this.reminderTime = time;
    }
}
