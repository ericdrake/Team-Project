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

public class Punch {
    private int punchId;
    private String adjustmenttype;
    private int terminalid;
    private String badgeId;
    private int punchtypeid;
    
    private String punchType;
    private LocalTime time;
    private LocalDate date;
    private LocalTime adjustedTime;
    private LocalDate adjustedDate;

    public Punch(HashMap<String, String> parameters) {
        this.terminalid = Integer.parseInt(parameters.get("terminalid"));
        this.badgeId = parameters.get("badgeid");
        this.punchtypeid = Integer.parseInt(parameters.get("punchtypeid"));
        this.punchId = 0;

        this.time = LocalTime.from(LocalDateTime.parse(parameters.get("timestamp")));
        this.date = LocalDate.from(LocalDateTime.parse(parameters.get("timestamp")));

        this.adjustedTime = null;
        this.adjustedDate = null;
        
        
        if (this.punchtypeid == 0) {
            this.punchType = PunchType.CLOCK_OUT.toString();
        } else if (this.punchtypeid == 1) {
            this.punchType = PunchType.CLOCK_IN.toString();
        } else if (this.punchtypeid == 2) {
            this.punchType = PunchType.TIME_OUT.toString();
        }
        
        
    }

    public int getPunchId() {
        return punchId;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public String getAdjustmenttype() {
        return adjustmenttype;
    }

    public String getPunchType() {
        return punchType;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getAdjustedTime() {
        return adjustedTime;
    }

    public void setAdjustedTime(LocalTime adjustedTime) {
        this.adjustedTime = adjustedTime;
    }

    public LocalDate getAdjustedDate() {
        return adjustedDate;
    }

    public void setAdjustedDate(LocalDate adjustedDate) {
        this.adjustedDate = adjustedDate;
    }

    public String printOriginal() {
        DateTimeFormatter dateFromatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFromatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "#" + this.getBadgeId() + " " + this.getPunchType() + ": "
                + this.getDate().getDayOfWeek().toString().substring(0, 3) + " " + this.getDate().format(dateFromatter)
                + " " + this.getTime().format(timeFromatter);
    }

    @Override
    public String toString() {
        return this.printOriginal();
    }
}