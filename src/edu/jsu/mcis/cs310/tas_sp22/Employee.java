/*
*@author Favour Ejemole
*/
package edu.jsu.mcis.cs310.tas_sp22;
import java.util.*;
import java.time.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;


public class Employee {
    
    private int id, employeetypeid, departmentid, shiftid;
    private String badgeid, firstname, lastname, middlename;
    private LocalDateTime active, inactive;
    
    public Employee(HashMap<String, String> params) {
        
        this.id = Integer.parseInt(params.get("id"));
        this.employeetypeid = Integer.parseInt(params.get("employeetypeid"));
        this.departmentid = Integer.parseInt(params.get("departmentid"));
        this.shiftid = Integer.parseInt(params.get("shiftid"));
        this.badgeid = params.get("badgeid");
        this.firstname = params.get("firstname");
        this.lastname = params.get("lastname");
        this.middlename = params.get("middlename");
        this.active = LocalDateTime.parse(params.get("active"));
        
        String inactive = params.get("inactive");
        if (inactive != null)
            this.inactive = LocalDateTime.parse(inactive);
    
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeetypeid() {
        return employeetypeid;
    }

    public void setEmployeetypeid(int employeetypeid) {
        this.employeetypeid = employeetypeid;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public int getShiftid() {
        return shiftid;
    }

    public void setShiftid(int shiftid) {
        this.shiftid = shiftid;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public LocalDateTime getActive() {
        return active;
    }

    public void setActive(LocalDateTime active) {
        this.active = active;
    }
    public LocalDateTime getInactive(){
        return inactive;
    }
    public void setInactive(LocalDateTime inactive){
        this.inactive = inactive;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        sb.append("#").append(badgeid).append(" (").append(lastname).append(", ");
        sb.append(firstname).append(" ").append(middlename).append("): ").append("employeetypeid: ");
        sb.append(employeetypeid).append(", departmentid: ").append(departmentid).append(", shiftid: ");
        sb.append(shiftid).append(", active: ").append(dt.format(active));
        if(inactive == null){
        sb.append(", inactive: ").append("none");
        }
        else{
            sb.append(", inactive: ").append(dt.format(inactive));
        }
        return sb.toString();
    }
    
}
