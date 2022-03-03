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
    int id;
    String description;
    LocalTime shiftStart;
    LocalTime shiftStop;
    int roundInterval;
    int gracePeriod;
    int dockPenalty;
    LocalTime lunchStart;
    LocalTime lunchStop;
    int lunchThreshold;
    long lunchDuration;
    long shiftDuration;

    public Shift(int id, String description, LocalTime shiftStart, LocalTime shiftStop, int roundInterval, int gracePeriod, int dockPenalty, LocalTime lunchStart, LocalTime lunchStop, int lunchThreshold) {
        this.id = id;
        this.description = description;
        this.shiftStart = shiftStart;
        this.shiftStop = shiftStop;
        this.roundInterval = roundInterval;
        this.gracePeriod = gracePeriod;
        this.dockPenalty = dockPenalty;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchThreshold = lunchThreshold;
        this.lunchDuration = Duration.between(lunchStart, lunchStop).toMinutes();
        this.shiftDuration = Duration.between(shiftStart, shiftStop).toMinutes();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public LocalTime getShiftStop() {
        return shiftStop;
    }

    public void setShiftStop(LocalTime shiftStop) {
        this.shiftStop = shiftStop;
    }

    public int getRoundInterval() {
        return roundInterval;
    }

    public void setRoundInterval(int roundInterval) {
        this.roundInterval = roundInterval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getDockPenalty() {
        return dockPenalty;
    }

    public void setDockPenalty(int dockPenalty) {
        this.dockPenalty = dockPenalty;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(LocalTime lunchStart) {
        this.lunchStart = lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(LocalTime lunchStop) {
        this.lunchStop = lunchStop;
    }

    public int getLunchThreshold() {
        return lunchThreshold;
    }

    public void setLunchThreshold(int lunchThreshold) {
        this.lunchThreshold = lunchThreshold;
    }

    public long getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(long lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public long getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(long shiftDuration) {
        this.shiftDuration = shiftDuration;
    }

    @Override
    public String toString() {
        return description + ": " + shiftStart + " - " + shiftStop + " (" + shiftDuration + " minutes); Lunch: " + lunchStart + " - " + lunchStop + " (" + lunchDuration + " minutes)";
    }
}
