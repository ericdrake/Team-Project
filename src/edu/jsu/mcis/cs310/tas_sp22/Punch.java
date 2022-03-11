/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Aneesh
 */
package edu.jsu.mcis.cs310.tas_sp22;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Punch {
    
    private int id, terminalid, eventtypeid;
    private PunchType punchtype;
    
    private String adjustmenttype;
    
    private Badge badge;
    
    private LocalDateTime timestamp, adjustedtimestamp;

    public Punch(HashMap<String, String> parameters, Badge badge) {
        
        this.id = Integer.parseInt(parameters.get("id"));
        this.terminalid = Integer.parseInt(parameters.get("terminalid"));
        this.punchtype = PunchType.values()[Integer.parseInt(parameters.get("punchtypeid"))];
        this.eventtypeid = Integer.parseInt(parameters.get("eventtypeid"));

        this.timestamp = LocalDateTime.parse(parameters.get("timestamp"));
        
        this.badge = badge;
        
    }
    
    public Punch(int terminalid, Badge badge, int eventtypeid) {
        
        this.terminalid = terminalid;
        this.badge = badge;
        this.eventtypeid = eventtypeid;
        this.punchtype = PunchType.values()[eventtypeid];
        
        this.id = 0;
        this.timestamp = LocalDateTime.now();
        this.adjustmenttype = "";
        this.adjustedtimestamp = null;
        
    }

    public int getEventtypeid() {
        return eventtypeid;
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

    public LocalDateTime getOriginalTimestamp() {
        return timestamp;
    }

    public LocalDateTime getAdjustedtimestamp() {
        return adjustedtimestamp;
    }
    
    

    public String printOriginal() {
        
        // "#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07"
        
        StringBuilder s = new StringBuilder();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        s.append('#').append(badge.getId()).append(' ').append(punchtype).append(": ");
        s.append(dtf.format(timestamp));
        
        return s.toString().toUpperCase();
        
    }

    @Override
    public String toString() {
        return this.printOriginal();
    }
}