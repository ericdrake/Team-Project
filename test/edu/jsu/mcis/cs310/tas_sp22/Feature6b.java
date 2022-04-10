
package edu.jsu.mcis.cs310.tas_sp22;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.json.simple.*;

public class Feature6b {
    private TASDatabase db;
    
    @Before
    
    public void setup() {
    
        db = new TASDatabase ("tasuser", "War Room D", "localhost");
    }
    
    @Test
    public void testWeekdayJson() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"originaltimestamp\":\"WED 08\\/01\\/2018 06:59:00\",\"badgeid\":\"07901755\",\"adjustedtimestamp\":\"WED 08\\/01\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"101\",\"id\":\"186\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 08\\/01\\/2018 15:33:25\",\"badgeid\":\"07901755\",\"adjustedtimestamp\":\"WED 08\\/01\\/2018 15:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"101\",\"id\":\"227\",\"punchtype\":\"CLOCK OUT\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(186);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
		
        /* Get Daily Punch List */
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        /* Adjust Punches */
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
        
        /* JSON Conversion */
        
        String actualJSON = TAS.getPunchListAsJSON(dailypunchlist);
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
     @Test
    public void testWeekendShiftJson() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"originaltimestamp\":\"SAT 09\\/29\\/2018 05:58:58\",\"badgeid\":\"8C0644BA\",\"adjustedtimestamp\":\"SAT 09\\/29\\/2018 06:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"6371\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 09\\/29\\/2018 11:18:57\",\"badgeid\":\"8C0644BA\",\"adjustedtimestamp\":\"SAT 09\\/29\\/2018 11:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"6457\",\"punchtype\":\"CLOCK OUT\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(6371);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
		
        /* Get Daily Punch List */
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        /* Adjust Punches */
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
        
        /* JSON Conversion */
        
        String actualJSON = TAS.getPunchListAsJSON(dailypunchlist);
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testWeekdayShiftJson() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"originaltimestamp\":\"WED 09\\/26\\/2018 06:46:38\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/26\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"5896\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 09\\/26\\/2018 12:01:42\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/26\\/2018 12:00:00\",\"adjustmenttype\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"5964\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 09\\/26\\/2018 12:25:47\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/26\\/2018 12:30:00\",\"adjustmenttype\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"5965\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 09\\/26\\/2018 17:32:14\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/26\\/2018 17:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"6022\",\"punchtype\":\"CLOCK OUT\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(5896);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
		
        /* Get Daily Punch List */
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        /* Adjust Punches */
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
        
        /* JSON Conversion */
        
        String actualJSON = TAS.getPunchListAsJSON(dailypunchlist);
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
}
