package carrot.mc.mancchallenge.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Timestamp {

    public static String getNowTimestamp(){
        return String.valueOf(System.currentTimeMillis()/1000L);
    }

    public static String TimestampToHour(String timestamp){
        Date currentDate = new Date(Long.parseLong(timestamp)*1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("HHH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Atlantic/Madeira")); // UTC
        return formatter.format(currentDate);
    }

    public static String getDifferenceTime(String startTime, String finalTime){
        long difference = (Long.parseLong(finalTime)*1000L - Long.parseLong(startTime)*1000L)/1000L; // milliseconds
        return TimestampToHour(String.valueOf(difference));
    }

    public static int differenceTime(String startTime, String finalTime){
        return (int) ((Long.parseLong(finalTime)*1000L - Long.parseLong(startTime)*1000L)/1000L); // milliseconds
    }

}
