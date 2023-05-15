package com.usc.utils;

import java.util.UUID;

public class TimeUtils {
    static  public String handlerTime(String time){
        String day  = time.split("T")[0];
        String mil  = time.split("T")[1].split(".")[0];
        return day + " " + day;
    }
    static public String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
    static  public  int getIntUUID(){
     return Integer.parseInt(   UUID.randomUUID().toString().replace("-", "").substring(0,6));
    }
}
