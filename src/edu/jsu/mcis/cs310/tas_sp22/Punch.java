/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Aneesh
 */
package edu.jsu.mcis.cs310.tas_sp22;
import edu.jsu.mcis.cs310.tas_sp22.PunchType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Punch {
    
    private int id, terminalid;
    private PunchType punchtype;

    private String adjustmenttype, badgeid;

    private LocalDateTime timestamp, adjustedtimestamp;

    public Punch(HashMap<String, String> parameters) {
        
        this.terminalid = Integer.parseInt(parameters.get("terminalid"));
        this.badgeid = parameters.get("badgeid");
        this.punchtype = PunchType.values()[ Integer.parseInt(parameters.get("punchtypeid")) ];
        this.id = Integer.parseInt(parameters.get("id"));

        this.timestamp = LocalDateTime.from(LocalDateTime.parse(parameters.get("timestamp")));

        this.adjustedtimestamp = LocalDateTime.from(LocalDateTime.parse(parameters.get("timestamp")));
        
    }
    
    public Punch(int terminalid, Badge badge, int eventtypeid) {

        this.terminalid = terminalid;
        this.badgeid = badge.getId();
        this.punchtype = PunchType.values()[eventtypeid];

        this.id = 0;
        this.timestamp = LocalDateTime.now().withNano(0);
        this.adjustmenttype = null;
        this.adjustedtimestamp = null;

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

    public String getBadge() {
        return badgeid;
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

        s.append('#').append(badgeid).append(' ').append(punchtype).append(": ");
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
        
        LocalDateTime startInterval = timestamp.withMinute((s.getShiftStart().minusMinutes(s.getroundinterval())).getMinute())
                .withSecond(0).withNano(0);
        
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
            else if(timestamp.isAfter(shiftStart) && timestamp.isBefore(startGracePeriod)){
                adjustedtimestamp = shiftStart;
                adjustmenttype = "Shift Start";
                
            }
            else if(timestamp.isAfter(startGracePeriod) && timestamp.isBefore(startDockPenalty)){
                adjustedtimestamp = startDockPenalty;
                adjustmenttype = "Shift Dock";
            }
            else if(timestamp.isBefore(lunchStop) && timestamp.isAfter(lunchStart)){
                adjustedtimestamp = lunchStop;
                adjustmenttype = "Lunch Stop";
            }
        }
        
        else if (punchtype == PunchType.CLOCK_OUT){
            if(timestamp.isAfter(lunchStart) && timestamp.isBefore(lunchStop)){
                adjustedtimestamp = lunchStart;
                adjustmenttype = "Lunch Start";
            }
            else if(timestamp.isAfter(stopGracePeriod) && timestamp.isBefore(shiftStop)){
                adjustedtimestamp = shiftStop;
                adjustmenttype = "Shift Stop";
            }
            else if(timestamp.isAfter(shiftStop) && timestamp.isBefore(stopInterval)){
                adjustedtimestamp = shiftStop;
                adjustmenttype = "Shift Stop";
            }
            
        }
        
    }
        

    
        public String printAdjusted() {
            
            // "#28DC3FB8 CLOCK IN: FRI 09/07/2018 07:00:00 (Shift Start)"
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        StringBuilder s = new StringBuilder();

        s.append('#').append(badgeid).append(' ').append(punchtype).append(": ");
        s.append(dtf.format(adjustedtimestamp).toUpperCase()).append(' ');
        s.append("(").append(adjustmenttype).append(")");

        return s.toString();
        
    }
     
  
}
