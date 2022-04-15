
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Feature5b {
    private TASDatabase db;
    
    @Before
    public void setup() {
        
        db = new TASDatabase("tasuser", "War Room D", "localhost");
    }
    
    @Test
    public void testminutesAccruedDuringWeekdays() {
    
        Punch p = db.getPunch(252);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList <Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist){
            punch.adjust(s);
        }
        //To compute total pay period
        
        int minutes = TAS.calculateTotalMinutes(dailypunchlist, s);
        
        //To compare results
        
        assertEquals(540, minutes);
    }
    
    @Test
    public void testminutesAccruedDuringWeekend() {
    
        Punch p = db.getPunch(1164);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList <Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist){
            punch.adjust(s);
        }
        //To compute total pay period
        
        int minutes = TAS.calculateTotalMinutes(dailypunchlist, s);
        
        //To compare results
        
        assertEquals(373, minutes);
    }
    
    @Test
    public void testminutesAccruedDuringWeekDayWithTimeout() {
    
        Punch p = db.getPunch(4181);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList <Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist){
            punch.adjust(s);
        }
        //To compute total pay period
        
        int minutes = TAS.calculateTotalMinutes(dailypunchlist, s);
        
        //To compare results
        
        assertEquals(0, minutes);
    }
    
    @Test
    public void testminutesAccruedDuringWeekdays2() {
    
        Punch p = db.getPunch(882);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList <Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist){
            punch.adjust(s);
        }
        //To compute total pay period
        
        int minutes = TAS.calculateTotalMinutes(dailypunchlist, s);
        
        //To compare results
        
        assertEquals(540, minutes);
    }
    
}
