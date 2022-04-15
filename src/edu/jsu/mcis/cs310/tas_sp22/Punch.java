/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Aneesh
 */
package edu.jsu.mcis.cs310.tas_sp22;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class Punch {
    
    private int id, terminalid;
    private PunchType punchtype;
    
    private Badge badge;

    private String adjustmenttype, badgeDescription;

    private LocalDateTime timestamp, adjustedtimestamp;

    public Punch(HashMap<String, String> parameters, Badge badge) {
        
        this.terminalid = Integer.parseInt(parameters.get("terminalid"));
        this.punchtype = PunchType.values()[ Integer.parseInt(parameters.get("punchtypeid")) ];
        this.id = Integer.parseInt(parameters.get("id"));

        this.timestamp = LocalDateTime.from(LocalDateTime.parse(parameters.get("timestamp")));

        this.adjustedtimestamp = LocalDateTime.from(LocalDateTime.parse(parameters.get("timestamp")));
        
        this.badge = badge;
        
    }
    
    public Punch(int terminalid, Badge badge, int eventtypeid) {

        this.terminalid = terminalid;
        this.badgeDescription = badge.getDescription();
        this.punchtype = PunchType.values()[eventtypeid];

        this.id = 0;
        this.timestamp = LocalDateTime.now().withNano(0);
        this.adjustmenttype = null;
        this.adjustedtimestamp = null;
        
        this.badge = badge;

    }

    public int getId() {
        return id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public PunchType getPunchtype() {
        return punchtype;
    }

    public String getAdjustmenttype() {
        return adjustmenttype;
    }

    public Badge getBadge() {
        return badge;
    }
    
    public String getBadgeDescription(){
        return badgeDescription;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public LocalDateTime getOriginalTimestamp() {
        return timestamp;
    }

    public LocalDateTime getAdjustedtimestamp() {
        return adjustedtimestamp;
    }
    
    

    public String printOriginal() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        StringBuilder s = new StringBuilder();

        s.append('#').append(badge.getId()).append(' ').append(punchtype).append(": ");
        s.append(dtf.format(timestamp));

        return s.toString().toUpperCase();
        
    }

    @Override
    public String toString() {
        return this.printOriginal();
    }
    
    public void adjust(Shift s) {
    
        
        LocalDateTime shiftStart = timestamp.withHour(s.getShiftStart().getHour())
                .withMinute(s.getShiftStart().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime shiftStop = timestamp.withHour(s.getShiftStop().getHour())
                .withMinute(s.getShiftStop().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime lunchStart = timestamp.withHour(s.getLunchStart().getHour())
                .withMinute(s.getLunchStart().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime lunchStop = timestamp.withHour(s.getLunchStop().getHour())
                .withMinute(s.getLunchStop().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime startInterval = timestamp.withHour(s.getShiftStart().minusMinutes(s.getroundinterval()).getHour())
                .withMinute(s.getShiftStart().minusMinutes(s.getroundinterval()).getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime startGracePeriod = timestamp.withHour(s.getShiftStart().getHour())
                .withMinute((s.getShiftStart().plusMinutes(s.getgraceperiod())).getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime startDockPenalty = timestamp.withHour(s.getShiftStart().getHour())
                .withMinute((s.getShiftStart().plusMinutes(s.getdockpenalty())).getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime stopInterval = timestamp.withHour(s.getShiftStop().getHour())
                .withMinute((s.getShiftStop().plusMinutes(s.getroundinterval())).getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime stopGracePeriod = timestamp.withHour(s.getShiftStop().getHour())
                .withMinute((s.getShiftStop().minusMinutes(s.getgraceperiod())).getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime stopDockPenalty = timestamp.withHour(s.getShiftStop().getHour())
                .withMinute((s.getShiftStop().minusMinutes(s.getdockpenalty())).getMinute()).withSecond(0).withNano(0);
        
        DayOfWeek day = timestamp.getDayOfWeek();
        
        
        if (punchtype == PunchType.CLOCK_IN){
            if (timestamp.isBefore(shiftStart) && timestamp.isAfter(startInterval)){
                adjustedtimestamp = shiftStart;
                adjustmenttype = "Shift Start";
            }
            
            else if (timestamp.isAfter(shiftStart) && timestamp.isBefore(startGracePeriod)){
                adjustedtimestamp = shiftStart;
                adjustmenttype = "Shift Start";
                 
            }
            
            else if (timestamp.isAfter(startGracePeriod) && timestamp.isBefore(startDockPenalty)){
                adjustedtimestamp = startDockPenalty;
                adjustmenttype = "Shift Dock";
            }
            
            else if (timestamp.isBefore(lunchStop) && timestamp.isAfter(lunchStart)){
                adjustedtimestamp = lunchStop;
                adjustmenttype = "Lunch Stop";
            }
            else if (timestamp.getMinute() == 0){
                adjustedtimestamp = timestamp.withSecond(0).withNano(0);
                adjustmenttype = "None";
            }
            
            else if (timestamp.getMinute() == 30){
                adjustedtimestamp = timestamp.withSecond(0).withNano(0);
                adjustmenttype = "None";
            }
            
            else if (timestamp.getMinute() == 45){
                adjustedtimestamp = timestamp.withSecond(0).withNano(0);
                adjustmenttype = "None";
            }
            
            else if (timestamp.isBefore(startInterval)){
                int time_second = timestamp.getSecond();
                int time_minute = timestamp.getMinute();
                int interval_round = Math.round((time_minute/s.getroundinterval()) * s.getroundinterval());
                if(time_second > 30){
                    adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round).plusMinutes(s.getroundinterval())
                       .withSecond(0).withNano(0);
                    adjustmenttype = "Interval Round";
               }
                
               else {
               adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round)
                       .withSecond(0).withNano(0);
               adjustmenttype = "Interval Round";
                }
                
            }
                
        }
        
        else if (punchtype == PunchType.CLOCK_OUT){
            if (timestamp.isAfter(lunchStart) && timestamp.isBefore(lunchStop)){
                if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
                    adjustedtimestamp = lunchStart;
                    adjustmenttype = "Interval Round";
                }
                
                else if (day != DayOfWeek.SATURDAY || day != DayOfWeek.SUNDAY){
                    adjustedtimestamp = lunchStart;
                    adjustmenttype = "Lunch Start";
                }
            }
            
            else if (timestamp.isAfter(stopGracePeriod) && timestamp.isBefore(shiftStop)){
                adjustedtimestamp = shiftStop;
                adjustmenttype = "Shift Stop";
            }
            
            else if (timestamp.isAfter(shiftStop) && timestamp.isBefore(stopInterval)){
                adjustedtimestamp = shiftStop;
                adjustmenttype = "Shift Stop";
            }
            
            else if (timestamp.isAfter(stopDockPenalty.minusMinutes(1))&& timestamp.isBefore(stopGracePeriod)){
                if(timestamp.getMinute() == 15){
                    adjustedtimestamp = stopDockPenalty;
                    adjustmenttype = "Shift Dock";
                }
                
                else {
                adjustedtimestamp = stopDockPenalty;
                adjustmenttype = "Shift Dock";
                }
            }
            
            else if (timestamp.getMinute() == 0){
                adjustedtimestamp = timestamp.withSecond(0).withNano(0);
                adjustmenttype = "None";
            }
            
            else if (timestamp.getMinute() == 30){
                adjustedtimestamp = timestamp.withSecond(0).withNano(0);
                adjustmenttype = "None";
            }
            
            else if (timestamp.getMinute() == 45){
                adjustedtimestamp = timestamp.withSecond(0).withNano(0);
                adjustmenttype = "None";
            }
            
            else if (timestamp.isBefore(stopInterval)){
               int time_second = timestamp.getSecond();
               int time_minute = timestamp.getMinute();
               int interval_round = Math.round((time_minute/s.getroundinterval()) * s.getroundinterval());
               if (time_second > 30){
                   adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round).plusMinutes(s.getroundinterval())
                       .withSecond(0).withNano(0);
                   adjustmenttype = "Interval Round";
               }
               
               else {
                    adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round)
                            .withSecond(0).withNano(0);
                   adjustmenttype = "Interval Round";
            }
            }
            else if (timestamp.isAfter(stopInterval)){
               int time_second = timestamp.getSecond();
               int time_minute = timestamp.getMinute();
               int interval_round = Math.round((time_minute/s.getroundinterval()) * s.getroundinterval());
               if (time_second > 30){
                   if(time_minute > 30){
                       adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round)
                       .withSecond(0).withNano(0); 
                   }
                   
                   else{
                       adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round)
                       .withSecond(0).withNano(0);
                   }
                   
                   adjustmenttype = "Interval Round";
               }
               
               else if(time_second < 30){
                   if(time_minute > 30){
                       adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round)
                       .withSecond(0).withNano(0); 
                   }
                   else{
                    adjustedtimestamp = timestamp.withHour(timestamp.getHour()).withMinute(interval_round)
                            .withSecond(0).withNano(0);   
                   }
                    
                   adjustmenttype = "Interval Round";
            }
            }
            
        }

    }
      
 
    
        public String printAdjusted() {
            
            // "#28DC3FB8 CLOCK IN: FRI 09/07/2018 07:00:00 (Shift Start)"
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        StringBuilder s = new StringBuilder();

        s.append('#').append(badge.getId()).append(' ').append(punchtype).append(": ");
        s.append(dtf.format(adjustedtimestamp).toUpperCase()).append(' ');
        s.append("(").append(adjustmenttype).append(")");

        return s.toString();
        
        }

  
}
