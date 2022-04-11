
package edu.jsu.mcis.cs310.tas_sp22;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
import java.lang.*;
import java.time.LocalDateTime;
        
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
            Punch p = db.getPunch(3634);
                Badge b = db.getBadge(p.getBadge().getId());
                Shift s = db.getShift(b);
                ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
                for (Punch punch : dailypunchlist) {
                            punch.adjust(s);}
                getPunchListAsJSON(dailypunchlist);
                
                            
              
              
    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        
        int numberofPunches = dailypunchlist.size();
        int totalMinutesWorked = 0;
        LocalTime start;
        LocalTime stop;
        LocalTime lunchStart;
        LocalTime lunchStop;
        
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
        
        if (numberofPunches == 4){
            start = dailypunchlist.get(0).getAdjustedtimestamp().toLocalTime();
            stop = dailypunchlist.get(3).getAdjustedtimestamp().toLocalTime();
            lunchStart = dailypunchlist.get(1).getAdjustedtimestamp().toLocalTime();
            lunchStop = dailypunchlist.get(2).getAdjustedtimestamp().toLocalTime();
            int totalLunch = ((lunchStop.getHour() - lunchStart.getHour()) + (lunchStop.getMinute() - lunchStart.getMinute()));
            totalMinutesWorked = (((stop.getHour() - start.getHour()) * 60) + ((stop.getMinute() - start.getMinute())) - totalLunch);
        }
        
        return totalMinutesWorked;
    }

 public static String getPunchListAsJSON(ArrayList<Punch> dailyPunchList){  
     ArrayList<HashMap<String, String >> jsonData = new ArrayList<>();
             for(Punch p : dailyPunchList){
                  HashMap<String, String > punchData = new HashMap<>();
                  punchData.put("id", String.valueOf(p.getId()));
                  punchData.put("badgeid", p.getBadge().getId()); 
                  punchData.put("terminalid", String.valueOf(p.getTerminalid()));
                  punchData.put("punchtypeid", String.valueOf(p.getPunchtype()));
                  punchData.put("adjustmenttype", p.getAdjustmenttype()); 
                  punchData.put("originaltimestamp", String.valueOf(p.getOriginalTimestamp()));
                  punchData.put("adjustedtimestamp", String.valueOf(p.getAdjustedtimestamp()));
                 
                  
                  jsonData.add(punchData);  
             }
                          String json = JSONValue.toJSONString(jsonData);
                          return json;
                          
                              
                              
                              
                  
                 
             }
 }



 