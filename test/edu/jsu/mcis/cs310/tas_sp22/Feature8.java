package edu.jsu.mcis.cs310.tas_sp22;

import java.util.*;
import org.json.simple.*;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature8 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        
        db = new TASDatabase();
        
    }
    
    @Test
    public void testJSONShift1Weekday() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "{\"absenteeism\":\"2.50%\",\"totalminutes\":\"2340\",\"punchlist\":[{\"originaltimestamp\":\"TUE 09\\/04\\/2018 06:48:31\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"TUE 09\\/04\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3279\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 09\\/04\\/2018 12:02:42\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"TUE 09\\/04\\/2018 12:00:00\",\"adjustmenttype\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3333\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 09\\/05\\/2018 06:46:48\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/05\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3395\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 09\\/05\\/2018 12:02:26\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/05\\/2018 12:00:00\",\"adjustmenttype\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3461\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 09\\/05\\/2018 12:26:35\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/05\\/2018 12:30:00\",\"adjustmenttype\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3462\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 09\\/05\\/2018 17:33:44\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"WED 09\\/05\\/2018 17:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3498\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 09\\/06\\/2018 06:46:06\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"THU 09\\/06\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3523\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 09\\/06\\/2018 12:03:34\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"THU 09\\/06\\/2018 12:00:00\",\"adjustmenttype\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3569\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 09\\/06\\/2018 12:27:34\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"THU 09\\/06\\/2018 12:30:00\",\"adjustmenttype\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3570\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 09\\/06\\/2018 17:33:21\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"THU 09\\/06\\/2018 17:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3597\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 06:50:35\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3634\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 12:03:54\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 12:00:00\",\"adjustmenttype\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3687\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 12:23:41\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 12:30:00\",\"adjustmenttype\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3688\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 15:34:13\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"3716\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"SAT 09\\/08\\/2018 05:55:36\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"SAT 09\\/08\\/2018 06:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3756\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 09\\/08\\/2018 12:03:37\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"SAT 09\\/08\\/2018 12:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"3839\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
		
        /* Get Punch */
        
        Punch p = db.getPunch(3634);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
		
        /* Get Daily Punch List */
        
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        /* JSON Conversion */
        
        String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punchlist, s);
        
        JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testJSONShift1Weekend() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "{\"absenteeism\":\"-20.00%\",\"totalminutes\":\"2880\",\"punchlist\":[{\"originaltimestamp\":\"MON 08\\/06\\/2018 06:54:17\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"MON 08\\/06\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"508\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"MON 08\\/06\\/2018 15:32:18\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"MON 08\\/06\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"105\",\"id\":\"565\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"TUE 08\\/07\\/2018 06:54:42\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"TUE 08\\/07\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"619\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 08\\/07\\/2018 16:32:47\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"TUE 08\\/07\\/2018 16:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"702\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 08\\/08\\/2018 06:54:30\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"WED 08\\/08\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"739\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 08\\/08\\/2018 16:32:41\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"WED 08\\/08\\/2018 16:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"814\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 08\\/09\\/2018 06:53:58\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"THU 08\\/09\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"851\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 08\\/09\\/2018 15:33:16\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"THU 08\\/09\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"105\",\"id\":\"927\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 08\\/10\\/2018 06:54:25\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"FRI 08\\/10\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"105\",\"id\":\"975\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 08\\/10\\/2018 15:35:35\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"FRI 08\\/10\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"105\",\"id\":\"1074\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"SAT 08\\/11\\/2018 05:54:58\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 06:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1087\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 08\\/11\\/2018 12:04:02\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 12:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1162\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
		
        /* Get Punch */
        
        Punch p = db.getPunch(1087);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
        
        /* Get Daily Punch List */
        
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        /* JSON Conversion */
        
        String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punchlist, s);
        
        JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testJSONShift2Weekend() {
        
        /* Expected JSON Data */
        
        String expectedJSON = "{\"absenteeism\":\"-27.50%\",\"totalminutes\":\"3060\",\"punchlist\":[{\"originaltimestamp\":\"MON 09\\/17\\/2018 11:30:37\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"MON 09\\/17\\/2018 11:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"104\",\"id\":\"4809\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"MON 09\\/17\\/2018 20:32:06\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"MON 09\\/17\\/2018 20:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"4880\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"TUE 09\\/18\\/2018 11:59:33\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"TUE 09\\/18\\/2018 12:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"4943\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 09\\/18\\/2018 21:30:27\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"TUE 09\\/18\\/2018 21:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"104\",\"id\":\"5004\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"WED 09\\/19\\/2018 12:07:51\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"WED 09\\/19\\/2018 12:15:00\",\"adjustmenttype\":\"Shift Dock\",\"terminalid\":\"104\",\"id\":\"5091\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"WED 09\\/19\\/2018 22:31:05\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"WED 09\\/19\\/2018 22:30:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"5162\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"THU 09\\/20\\/2018 11:57:30\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"THU 09\\/20\\/2018 12:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"5228\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"THU 09\\/20\\/2018 22:30:31\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"THU 09\\/20\\/2018 22:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"104\",\"id\":\"5307\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 09\\/21\\/2018 11:52:08\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"FRI 09\\/21\\/2018 12:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"5383\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 09\\/21\\/2018 20:30:51\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"FRI 09\\/21\\/2018 20:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"5455\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"SAT 09\\/22\\/2018 05:49:00\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"SAT 09\\/22\\/2018 05:45:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"5463\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 09\\/22\\/2018 12:04:15\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"SAT 09\\/22\\/2018 12:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"104\",\"id\":\"5541\",\"punchtype\":\"CLOCK OUT\"}]}";
        
        JSONObject expected = (JSONObject)(JSONValue.parse(expectedJSON));
		
        /* Get Punch */
        
        Punch p = db.getPunch(4943);
        Badge b = db.getBadge(p.getBadge().getId());
        Shift s = db.getShift(b);
        
        /* Get Daily Punch List */
        
        ArrayList<Punch> punchlist = db.getPayPeriodPunchList(b, p.getOriginalTimestamp().toLocalDate(), s);
        
        /* JSON Conversion */
        
        String actualJSON = TAS.getPunchListPlusTotalsAsJSON(punchlist, s);
        
        JSONObject actual = (JSONObject)(JSONValue.parse(actualJSON));
		
        /* Compare to Expected JSON */
        
        assertEquals(expected, actual);
        
    }
    
}