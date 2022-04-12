package edu.jsu.mcis.cs310.tas_sp22;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class Absenteeism {
    private String badgeid;
    private LocalDate payPeriod;
    private double absenteeismPercentage;


    public Absenteeism(Badge badge, LocalDate payPeriod, double absenteeismPercentage) {
        this.badgeid = badge.getId();
        
        
        //Get weekNumber
        TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = payPeriod.get(weekOfYear);

        

        //Fetch each date: Did not have a fancy way of doing this so i hard coded it
        LocalDate sundayDate = payPeriod
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        
        //Initialize payPeriod to sunday
        this.payPeriod = sundayDate;
        this.absenteeismPercentage = absenteeismPercentage;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public LocalDate getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(LocalDate payPeriod) {
        this.payPeriod = payPeriod;
    }

    public double getAbsenteeismPercentage() {
        return absenteeismPercentage;
    }

    public void setAbsenteeismPercentage(double absenteeismPercentage) {
        this.absenteeismPercentage = absenteeismPercentage;
    }
    
    //"#28DC3FB8 (Pay Period Starting 09-02-2018): 2.50%"
    public String toString(){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        StringBuilder s = new StringBuilder();

        s.append('#').append(getBadgeid()).append(" (Pay Period Starting ").append(dtf.format(getPayPeriod())).append("): ");
        s.append(String.format("%.2f", getAbsenteeismPercentage())).append('%');

        return s.toString();
    }
}

