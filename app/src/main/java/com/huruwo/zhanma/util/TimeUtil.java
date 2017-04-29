package com.huruwo.zhanma.util;

/**
 * Created by Administrator on 2017/4/15.
 */

public class TimeUtil {
    public static String SecondsToTime(int seconds){
        String strSecond;
        String strMinute;
        String strHour;
        int day=seconds/(60*60*24);//换成天
        int hour=(seconds-(60*60*24*day))/3600;//总秒数-换算成天的秒数=剩余的秒数    剩余的秒数换算为小时
        int minute=(seconds-60*60*24*day-3600*hour)/60;//总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数    剩余的秒数换算为分
        int second=seconds-60*60*24*day-3600*hour-60*minute;//总秒数-换算成天的秒数-换算成小时的秒数-换算为分的

        if (second<10)
        {
             strSecond= "0"+Integer.toString(second);
        }
        else {
            strSecond=Integer.toString(second);
        }

        if (minute<10)
        {
            strMinute= "0"+Integer.toString(minute);
        }
        else {
            strMinute=Integer.toString(minute);
        }

        if (hour<10)
        {
            strHour= "0"+Integer.toString(hour);
        }
        else {
            strHour=Integer.toString(hour);
        }




            return strHour+":"+strMinute+":"+strSecond;
    }
}
