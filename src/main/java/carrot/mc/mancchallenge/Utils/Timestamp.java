package carrot.mc.mancchallenge.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Timestamp {

    public static String getNowTimestamp(){
        return String.valueOf(System.currentTimeMillis()/1000L);
    }

    public static String getDay(String timestamp){
        long day = Long.parseLong(timestamp)/86400L;
        day = (long) Math.floor(day);
        return String.format("%03d:", day);
    }

    public static String TimestampWithDay(String timestamp){
        Date currentDate = new Date(Long.parseLong(timestamp)*1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Atlantic/Madeira")); // UTC
        return getDay(timestamp) + formatter.format(currentDate);
    }

    public static String TimestampToHour(String timestamp){
        Date currentDate = new Date(Long.parseLong(timestamp)*1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Atlantic/Madeira")); // UTC
        return formatter.format(currentDate);
    }

    public static String getDifferenceTime(String startTime, String finalTime){
        long difference = (Long.parseLong(finalTime)*1000L - Long.parseLong(startTime)*1000L)/1000L; // milliseconds
        return TimestampWithDay(String.valueOf(difference));
    }

    public static int differenceTime(String startTime, String finalTime){
        return (int) ((Long.parseLong(finalTime)*1000L - Long.parseLong(startTime)*1000L)/1000L); // milliseconds
    }

}
