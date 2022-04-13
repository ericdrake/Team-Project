/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;

import static edu.jsu.mcis.cs310.tas_sp22.TAS.calculateAbsenteeism;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jasmi
 */
public class Absenteeism {
    private Badge badge;
    private String badgeid;
    private LocalDate startOfPay;
    private LocalDate endOfPay;
    private LocalDate payPeriod;
    private double absenteeismPercentage;
    

    public Absenteeism(Badge badge, LocalDate payPeriod, double absenteeismPercentage) {
        this.badgeid = badge.getId();
        this.badge = badge;
        this.endOfPay = payPeriod.with(payPeriod.getDayOfWeek().SATURDAY);
        this.startOfPay = payPeriod.with(endOfPay.minusDays(6));
        this.payPeriod = startOfPay;
        this.absenteeismPercentage = absenteeismPercentage;
    }

    public Badge getBadge() {
        return badge;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public LocalDate getPayPeriod() {
        return payPeriod;
    }

    public double getAbsenteeismPercentage() {
        return absenteeismPercentage;
    }

    @Override
    public String toString() {
        //#28DC3FB8 (Pay Period Starting 09-02-2018): 2.50%
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedString = String.format("%.02f", absenteeismPercentage);
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(badgeid);
        sb.append(" (Pay Period Starting ").append(dtf.format(payPeriod));
        sb.append("): ").append(formattedString).append("%");
        return sb.toString();
    }
    
}

