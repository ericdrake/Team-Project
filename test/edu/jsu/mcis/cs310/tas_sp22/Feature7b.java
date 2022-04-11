
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
        Punch p = db.getPunch(115);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
    }
    
}
