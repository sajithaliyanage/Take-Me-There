package com.example.sajitha.tmt;

public class LoginSession {
    public static String logStatus =null;
    public static String userName =null;
    public static int userID = 0;

    LoginSession(String name,int id){
        logStatus = "Logged";
        userName = name;
        userID = id;
    }

    public boolean isLogged(){
        if(logStatus.equals("Logged") && userID!=0){
            return true;
        }else{
            return false;
        }
    }

    public void logOut(){
        logStatus = null;
        userName = null;
        userID =0;
    }

    public String getUserName(){
        if(isLogged()){
            return userName;
        }else{
            return null;
        }
    }

    public int getUserID(){
        if(isLogged()){
            return userID;
        }else{
            return 0;
        }
    }
}
