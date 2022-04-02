
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TAS {
    public static void main(String[] args){
         TASDatabase db = new TASDatabase("tasuser","War Room D", "localhost");
        
        if (db.isConnected()){
            System.err.println("Your Have Successfully Connected To The Database");
        }   
    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        
        int numberofPunches = dailypunchlist.size();
        int totalMinutesWorked = 0;
        LocalTime start = null;
        LocalTime stop = null;
        LocalTime lunchStart = null;
        LocalTime lunchStop = null;
        
        if (numberofPunches == 1){
            start = dailypunchlist.get(0).getAdjustedtimestamp().toLocalTime();
            totalMinutesWorked = start.getHour() - start.getHour();
        }
        
        if (numberofPunches == 2){
                start = dailypunchlist.get(0).getAdjustedtimestamp().toLocalTime();
                stop = dailypunchlist.get(1).getAdjustedtimestamp().toLocalTime();
                totalMinutesWorked = (((stop.getHour() - start.getHour()) * 60) + (stop.getMinute() - start.getMinute()));
                if(totalMinutesWorked > shift.getlunchthreshold()){
                    totalMinutesWorked = (int)(totalMinutesWorked - shift.getLunchDuration());
                }     
        }
        
        if (numberofPunches == 3){
            start = dailypunchlist.get(0).getAdjustedtimestamp().toLocalTime();
            lunchStart = dailypunchlist.get(3).getAdjustedtimestamp().toLocalTime();
            lunchStop = dailypunchlist.get(3).getAdjustedtimestamp().toLocalTime();  
        }
        
        if (numberofPunches == 4){
            start = dailypunchlist.get(0).getAdjustedtimestamp().toLocalTime();
            stop = dailypunchlist.get(3).getAdjustedtimestamp().toLocalTime();
            lunchStart = dailypunchlist.get(1).getAdjustedtimestamp().toLocalTime();
            lunchStop = dailypunchlist.get(2).getAdjustedtimestamp().toLocalTime();
            int totalLunch = ((lunchStop.getHour() - lunchStart.getHour()) + (lunchStop.getMinute() - lunchStart.getMinute()));
            totalMinutesWorked = (((stop.getHour() - start.getHour()) * 60) + ((stop.getMinute() - start.getMinute())) - totalLunch);
            System.err.println(dailypunchlist);
        }
     
        return totalMinutesWorked;
    }
}
