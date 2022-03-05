/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp22;

/**
 *
 * @author user
 */
public class Shift {
    private int id, roundinterval, graceperiod, dockpenalty, lunchthreshold;
    private String description;
    private LocalTime ShiftStart, ShiftStop, LunchStart, LunchStop;
    private long ShiftDuration, LunchDuration;

    public Shift(HashMap<String, String> params) {
        System.err.println("Shift ID: " + params.get("id"));
        this.id = Integer.parseInt(params.get("id"));

        this.description = params.get("description");
        this.ShiftStart = LocalTime.parse(params.get("ShiftStart"));

        this.ShiftStop = LocalTime.parse(params.get("ShiftStop"));
        this.roundinterval = Integer.parseInt(params.get("roundinterval"));
        this.graceperiod = Integer.parseInt(params.get("graceperiod"));
        this.dockpenalty = Integer.parseInt(params.get("dockpenalty"));
        this.LunchStart = LocalTime.parse(params.get("LunchStart"));
        this.LunchStop = LocalTime.parse(params.get("LunchStop"));
        this.lunchthreshold = Integer.parseInt(params.get("lunchthreshold"));

        // The minutes between the shiftstart and stop
        this.ShiftDuration = java.time.Duration.between(ShiftStart, ShiftStop).toMinutes();

        // The minutes between the lunch start and stop
        this.LunchDuration = java.time.Duration.between(LunchStart, LunchStop).toMinutes();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getShiftStart() {
        return ShiftStart;
    }

    public void setShiftStart(LocalTime shiftStart) {
        this.ShiftStart = ShiftStart;
    }

    public LocalTime getShiftStop() {
        return ShiftStop;
    }

    public void setShiftStop(LocalTime shiftStop) {
        this.ShiftStop = shiftStop;
    }

    public int getroundinterval() {
        return roundinterval;
    }

    public void setroundinterval(int roundinterval) {
        this.roundinterval = roundinterval;
    }

    public int getgraceperiod() {
        return graceperiod;
    }

    public void setgraceperiod(int graceperiod) {
        this.graceperiod = graceperiod;
    }

    public int getdockpenalty() {
        return dockpenalty;
    }

    public void setdockpenalty(int dockpenalty) {
        this.dockpenalty = dockpenalty;
    }

    public LocalTime getLunchStart() {
        return LunchStart;
    }

    public void setLunchStart(LocalTime LunchStart) {
        this.LunchStart = LunchStart;
    }

    public LocalTime getLunchStop() {
        return LunchStop;
    }

    public void setLunchStop(LocalTime LunchStop) {
        this.LunchStop = LunchStop;
    }

    public int getlunchthreshold() {
        return lunchthreshold;
    }

    public void setlunchthreshold(int lunchthreshold) {
        this.lunchthreshold = lunchthreshold;
    }

    public long getLunchDuration() {
        return LunchDuration;
    }

    public void setLunchDuration(long LunchDuration) {
        this.LunchDuration = LunchDuration;
    }

    public long getShiftDuration() {
        return ShiftDuration;
    }

    public void setShiftDuration(long ShiftDuration) {
        this.ShiftDuration = ShiftDuration;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(description).append(": ").append(ShiftStart);
        s.append(" - ").append(ShiftStop).append(" (");
        s.append(ShiftDuration).append(" minutes); Lunch: ");
        s.append(LunchStart).append(" - ").append(LunchStop);
        s.append(" (").append(LunchDuration).append(" minutes)");
        return s.toString();
    }
}
