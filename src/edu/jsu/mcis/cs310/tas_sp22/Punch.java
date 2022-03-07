/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Aneesh
 */
package edu.jsu.mcis.cs310.tas_sp22;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Punch {
    
    private int id, terminalid;
    private PunchType punchtype;
    
    private String adjustmenttype, badgeid;
    
    private LocalDateTime timestamp, adjustedtimestamp;

    public Punch(HashMap<String, String> parameters) {
        
        this.id = Integer.parseInt(parameters.get("id"));
        this.terminalid = Integer.parseInt(parameters.get("terminalid"));
        this.badgeid = parameters.get("badgeid");
        this.punchtype = PunchType.values()[Integer.parseInt(parameters.get("punchtypeid"))];

        this.timestamp = LocalDateTime.parse(parameters.get("timestamp"));
        
    }

    public String printOriginal() {
        
        // "#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07"
        
        StringBuilder s = new StringBuilder();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        s.append('#').append(badgeid).append(' ').append(punchtype).append(": ");
        s.append(dtf.format(timestamp));
        
        return s.toString().toUpperCase();
        
    }

    @Override
    public String toString() {
        return this.printOriginal();
    }
}