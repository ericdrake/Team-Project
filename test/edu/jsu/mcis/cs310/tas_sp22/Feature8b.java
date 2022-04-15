
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.util.*;
import org.json.simple.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Feature8b {
    private TASDatabase db;
    
    @Before 
    public void setup() {
    
        db = new TASDatabase ("tasuser", "War Room D", "localhost");
    }
    
    @Test
    public void testWeekdayShiftJson() {
        
        //Expected Json
        String expectedJSON = "";
    
        Punch p = db.getPunch(0);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList <Punch> punches = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        //JSON Conversion
        
        
        
    }
}
