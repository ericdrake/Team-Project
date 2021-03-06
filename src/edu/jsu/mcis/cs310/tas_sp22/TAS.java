package edu.jsu.mcis.cs310.tas_sp22;

import org.json.simple.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TAS {
    
    public static void main(String[] args){
         TASDatabase db = new TASDatabase("tasuser","War Room D", "localhost");
        
        if (db.isConnected()){
            System.err.println("You Have Successfully Connected To The Database");
        } 
    }
        
    /* Calculate the total number of hours that were accumulated by the employee */

    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        
        int totalMinutesWorked = 0;
        int totalWithLunch = 0;
        int startHours = 0;
        int startMinutes = 0;
        int stopHours = 0;
        int stopMinutes = 0;
        boolean pair = false;
        LocalDateTime punches;
        int lunchDuration = (int)shift.getLunchDuration();
        int calculations = 0;
        
        for (Punch p : dailypunchlist){
            
            if ( p.getPunchtype() == PunchType.CLOCK_IN || p.getPunchtype() == PunchType.CLOCK_OUT){
                
                if (p.getPunchtype() == PunchType.CLOCK_IN){
                    pair = false;
                }
                
                if (p.getPunchtype() == PunchType.CLOCK_OUT){
                    pair = true;
                }
            }
            
            if (pair == false){
                
                punches = p.getAdjustedtimestamp();
                startHours = punches.getHour();
                startMinutes = punches.getMinute();
                
            }
            
            else if (pair){ 
                
                punches = p.getAdjustedtimestamp();
                stopHours = punches.getHour();
                stopMinutes = punches.getMinute();
                totalWithLunch = ((stopHours - startHours) * 60) + (stopMinutes - startMinutes);
                
                if (totalWithLunch > shift.getlunchthreshold()){
                    calculations = totalWithLunch - lunchDuration;
                    totalMinutesWorked = totalMinutesWorked + calculations;
                }
                
                else if (totalWithLunch <= shift.getlunchthreshold()){
                    calculations = ((stopHours - startHours) * 60) + (stopMinutes - startMinutes);
                    totalMinutesWorked = totalMinutesWorked + calculations; 
                }
                
            }
        }
       
        return totalMinutesWorked;

    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailyPunchList){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        ArrayList<HashMap<String, String >> jsonData = new ArrayList<>();
        
        for(Punch p : dailyPunchList){
            
            HashMap<String, String > punchData = new HashMap<>(); 
            punchData.put("originaltimestamp", String.valueOf(dtf.format(p.getOriginalTimestamp()).toUpperCase()));
            punchData.put("badgeid", String.valueOf(p.getBadge().getId()));
            punchData.put("adjustedtimestamp", String.valueOf(dtf.format(p.getAdjustedtimestamp()).toUpperCase()));
            punchData.put("adjustmenttype", p.getAdjustmenttype());
            punchData.put("terminalid", String.valueOf(p.getTerminalid()));
            punchData.put("id", String.valueOf(p.getId()));
            punchData.put("punchtype", String.valueOf(p.getPunchtype()));
            
            jsonData.add(punchData);  
            
        }
        
        String json = JSONValue.toJSONString(jsonData);
        return json;     
                 
    }
    
    public static double calculateAbsenteeism(ArrayList<Punch> punchlist, Shift s) {

        int minutesScheduled = s.getTotalScheduledHours();
        int minutesWorked = calculateTotalMinutes(punchlist, s);

        System.err.println("Scheduled: " + minutesScheduled + ", Worked: " + minutesWorked);
        double absenteeism = (100.00 - ((double)(minutesWorked / (double)minutesScheduled)) * 100.00);

        return absenteeism;
        
    }
    
    public static String getPunchListPlusTotalsAsJSON(ArrayList<Punch> punchlist, Shift s){
        
        HashMap<String, String> output = new HashMap<>();
        String punchListAsJson = getPunchListAsJSON(punchlist);

        output.put("totalminutes", String.valueOf(calculateTotalMinutes(punchlist, s)));
        output.put("absenteeism", String.format("%.02f", calculateAbsenteeism(punchlist, s)) + "%");
        JSONObject obj = (JSONObject) JSONValue.parse(JSONValue.toJSONString(output));
        obj.put("punchlist", JSONValue.parse(punchListAsJson));

        return JSONValue.toJSONString(obj);

    }
    
}