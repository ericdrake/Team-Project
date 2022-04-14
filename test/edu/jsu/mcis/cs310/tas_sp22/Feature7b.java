
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


public class Feature7b {
    private TASDatabase db;
    
    @Before 
    public void setup() {
    
        db = new TASDatabase ("tasuser", "War Room D", "localhost");
    }
    
    @Test 
    public void testAbseenteeismForWeekday() {
    
        //To get punches
        Punch p = db.getPunch(1975);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        LocalDateTime dt = p.getOriginalTimestamp();
        ArrayList<Punch> punches = db.getPayPeriodPunchList(b, dt.toLocalDate(), s);
        //Calculating Pay period total absenteeism
        double percentage = TAS.calculateAbsenteeism(punches, s);
        
        //To insert absenteeism into database 
        Absenteeism a = new Absenteeism(b, dt.toLocalDate(), percentage);
        db.insertAbsenteeism(a);
        
        //Retrieving absenteeism from the database
        
        Absenteeism a1 = db.getAbsenteeism(b, dt.toLocalDate());
        
        //Compare values
        
        assertEquals("#8C0644BA (Pay Period Starting 08-19-2018): -40.63%", a1.toString());
        
    }
    
    @Test
    public void testAbseenteeismForWeekday2() {
    
        //To get punches
        Punch p = db.getPunch(2971);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        LocalDateTime dt = p.getOriginalTimestamp();
        ArrayList<Punch> punches = db.getPayPeriodPunchList(b, dt.toLocalDate(), s);
        //Calculating Pay period total absenteeism
        double percentage = TAS.calculateAbsenteeism(punches, s);
        
        //To insert absenteeism into database 
        Absenteeism a = new Absenteeism(b, dt.toLocalDate(), percentage);
        db.insertAbsenteeism(a);
        
        //Retrieving absenteeism from the database
        
        Absenteeism a1 = db.getAbsenteeism(b, dt.toLocalDate());
        
        //Compare values
        
        assertEquals("#67637925 (Pay Period Starting 08-26-2018): -6.25%", a1.toString());
        
    }
    
    @Test
    public void testAbseenteeismForWeekend() {
    
        //To get punches
        Punch p = db.getPunch(2526);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        LocalDateTime dt = p.getOriginalTimestamp();
        ArrayList<Punch> punches = db.getPayPeriodPunchList(b, dt.toLocalDate(), s);
        //Calculating Pay period total absenteeism
        double percentage = TAS.calculateAbsenteeism(punches, s);
        
        //To insert absenteeism into database 
        Absenteeism a = new Absenteeism(b, dt.toLocalDate(), percentage);
        db.insertAbsenteeism(a);
        
        //Retrieving absenteeism from the database
        
        Absenteeism a1 = db.getAbsenteeism(b, dt.toLocalDate());
        
        //Compare values
        
        assertEquals("#4E04B5FE (Pay Period Starting 08-19-2018): -22.50%", a1.toString());
        
    }
    @Test
    public void testAbseenteeismForWeekend2() {
    
        //To get punches
        Punch p = db.getPunch(6886);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        LocalDateTime dt = p.getOriginalTimestamp();
        ArrayList<Punch> punches = db.getPayPeriodPunchList(b, dt.toLocalDate(), s);
        //Calculating Pay period total absenteeism
        double percentage = TAS.calculateAbsenteeism(punches, s);
        
        //To insert absenteeism into database 
        Absenteeism a = new Absenteeism(b, dt.toLocalDate(), percentage);
        db.insertAbsenteeism(a);
        
        //Retrieving absenteeism from the database
        
        Absenteeism a1 = db.getAbsenteeism(b, dt.toLocalDate());
        
        //Compare values
        
        assertEquals("#8709982E (Pay Period Starting 09-23-2018): 32.50%", a1.toString());
        
    }
    
}
