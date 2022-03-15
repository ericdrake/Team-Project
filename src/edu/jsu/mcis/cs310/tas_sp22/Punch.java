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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;

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
        s.append(dtf.format(timestamp).toUpperCase());

        return s.toString();
        
    }
   
    @Override
    public String toString() {
        return this.printOriginal();
    }

    public void adjust(Shift s) {
    
        LocalTime shiftStart = s.getShiftStart();
        LocalTime shiftStop = s.getShiftStop();
        LocalTime lunchStart = s.getLunchStart();
        LocalTime lunchStop = s.getLunchStop();
        LocalTime startInterval = s.getShiftStart().minusMinutes(s.getroundinterval());
        LocalTime startGracePeriod = s.getShiftStart().plusMinutes(s.getgraceperiod());
        LocalTime startDockPenalty = s.getShiftStart().plusMinutes(s.getdockpenalty());
        LocalTime stopInterval = s.getShiftStop().plusMinutes(s.getroundinterval());
        LocalTime stopGracePeriod = s.getShiftStop().minusMinutes(s.getgraceperiod());
        LocalTime stopDockPenalty = s.getShiftStop().minusMinutes(s.getdockpenalty());
        LocalDate date = timestamp.toLocalDate();
        
        if(timestamp.toLocalTime().isBefore(shiftStart) && timestamp.toLocalTime().isAfter(startInterval)){
           adjustedtimestamp = LocalDateTime.of(date, shiftStart);
           String description =  "(Shift Start)";
           adjustmenttype = description;     
        }
        
      
        
}
    
        public String printAdjusted() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        StringBuilder s = new StringBuilder();

        s.append('#').append(badgeid).append(' ').append(punchtype).append(": ");
        s.append(dtf.format(adjustedtimestamp).toUpperCase());
        s.append(adjustmenttype);

        return s.toString();
        
    }
     
  
}
