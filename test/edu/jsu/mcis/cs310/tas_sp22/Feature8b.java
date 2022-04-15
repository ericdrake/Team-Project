package edu.jsu.mcis.cs310.tas_sp22;

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
        String expectedJSON = "{\"absenteeism\":\"23.75%\",\"totalminutes\":\"1830\",\"punchlist\":[{\"originaltimestamp\":\"TUE 08\\/21\\/2018 07:01:44\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"TUE 08\\/21\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"2065\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 08\\/21\\/2018 16:31:40\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"TUE 08\\/21\\/2018 16:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"2088\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 08\\/22\\/2018 07:00:44\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"WED 08\\/22\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"2175\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 08\\/22\\/2018 15:31:49\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"WED 08\\/22\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"2199\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 08\\/24\\/2018 06:58:59\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"FRI 08\\/24\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"2426\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 08\\/24\\/2018 15:31:54\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"FRI 08\\/24\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"2460\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"SAT 08\\/25\\/2018 05:56:16\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"SAT 08\\/25\\/2018 05:45:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"2547\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 08\\/25\\/2018 11:01:47\",\"badgeid\":\"2A972897\",\"adjustedtimestamp\":\"SAT 08\\/25\\/2018 11:15:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"2579\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
    
        Punch p = db.getPunch(2460);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
        
        ArrayList <Punch> punches = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        //JSON Conversion
        
     String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punches, s);
     
     JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
        
     assertEquals(expected, actual);   
    }
    
    @Test
    public void testWeekendShiftJson() {
        
        //Expected Json
        String expectedJSON = "{\"absenteeism\":\"-12.50%\",\"totalminutes\":\"2700\",\"punchlist\":[{\"originaltimestamp\":\"MON 08\\/06\\/2018 06:56:48\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"MON 08\\/06\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"515\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"MON 08\\/06\\/2018 15:32:32\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"MON 08\\/06\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"569\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"TUE 08\\/07\\/2018 06:58:31\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"TUE 08\\/07\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"631\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 08\\/07\\/2018 15:32:17\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"TUE 08\\/07\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"671\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 08\\/08\\/2018 06:56:28\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"WED 08\\/08\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"744\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 08\\/08\\/2018 15:32:24\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"WED 08\\/08\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"793\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 08\\/09\\/2018 06:55:51\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"THU 08\\/09\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"859\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 08\\/09\\/2018 15:32:22\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"THU 08\\/09\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"921\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 08\\/10\\/2018 06:55:17\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"FRI 08\\/10\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"979\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 08\\/10\\/2018 15:33:49\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"FRI 08\\/10\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"1062\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"SAT 08\\/11\\/2018 05:59:35\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 06:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"1105\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 08\\/11\\/2018 11:00:00\",\"badgeid\":\"CF697DE6\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 11:00:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"0\",\"id\":\"1158\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
    
        Punch p = db.getPunch(1105);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
        
        ArrayList <Punch> punches = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        //JSON Conversion
        
     String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punches, s);
     
     JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
        
     assertEquals(expected, actual);   
    }
    
    @Test
    public void testWeekday2ShiftJson() {
        
        //Expected Json
        String expectedJSON = "{\"absenteeism\":\"15.00%\",\"totalminutes\":\"2040\",\"punchlist\":[{\"originaltimestamp\":\"MON 08\\/06\\/2018 06:50:08\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"MON 08\\/06\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"500\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"MON 08\\/06\\/2018 23:12:34\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"MON 08\\/06\\/2018 23:12:34\",\"adjustmenttype\":null,\"terminalid\":\"0\",\"id\":\"599\",\"punchtype\":\"TIME OUT\"},{\"originaltimestamp\":\"TUE 08\\/07\\/2018 06:50:42\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"TUE 08\\/07\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"611\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 08\\/07\\/2018 15:33:25\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"TUE 08\\/07\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"103\",\"id\":\"680\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 08\\/08\\/2018 06:52:15\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"WED 08\\/08\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"734\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 08\\/08\\/2018 16:33:18\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"WED 08\\/08\\/2018 16:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"103\",\"id\":\"820\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 08\\/09\\/2018 06:50:36\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"THU 08\\/09\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"845\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 08\\/09\\/2018 16:33:12\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"THU 08\\/09\\/2018 16:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"103\",\"id\":\"947\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 08\\/10\\/2018 06:49:24\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"FRI 08\\/10\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"964\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 08\\/10\\/2018 15:33:14\",\"badgeid\":\"6CA0FF4A\",\"adjustedtimestamp\":\"FRI 08\\/10\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"103\",\"id\":\"1056\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
    
        Punch p = db.getPunch(500);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
        
        ArrayList <Punch> punches = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        //JSON Conversion
        
     String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punches, s);
     
     JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
        
     assertEquals(expected, actual);   
    }
    
    @Test
    public void testWeekend2ShiftJson() {
        
        //Expected Json
        String expectedJSON = "{\"absenteeism\":\"-9.38%\",\"totalminutes\":\"2625\",\"punchlist\":[{\"originaltimestamp\":\"TUE 09\\/04\\/2018 06:52:29\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"TUE 09\\/04\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"3291\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 09\\/04\\/2018 17:33:45\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"TUE 09\\/04\\/2018 17:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"103\",\"id\":\"3381\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 09\\/05\\/2018 06:54:34\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"WED 09\\/05\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"3407\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 09\\/05\\/2018 17:34:15\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"WED 09\\/05\\/2018 17:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"103\",\"id\":\"3504\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 09\\/06\\/2018 06:49:45\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"THU 09\\/06\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"103\",\"id\":\"3529\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 09\\/06\\/2018 17:30:00\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"THU 09\\/06\\/2018 17:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"0\",\"id\":\"3728\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 07:00:00\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 07:00:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"0\",\"id\":\"3729\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 15:39:01\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"0\",\"id\":\"3730\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"SAT 09\\/08\\/2018 05:56:23\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"SAT 09\\/08\\/2018 05:45:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"103\",\"id\":\"3759\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 09\\/08\\/2018 12:04:08\",\"badgeid\":\"99F0C0FA\",\"adjustedtimestamp\":\"SAT 09\\/08\\/2018 12:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"103\",\"id\":\"3853\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
    
        Punch p = db.getPunch(3853);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
        
        ArrayList <Punch> punches = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        //JSON Conversion
        
     String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punches, s);
     
     JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
        
     assertEquals(expected, actual);   
    }
}
