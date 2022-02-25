/*
*@author Favour Ejemole
*/
package edu.jsu.mcis.cs310.tas_sp22;
import java.util.*;
import java.time.*;
import java.sql.*;


public class Employee {
    private int id,employeetypeid,departmentid,shiftid ;
    private String badgeid, firstname, lastname;
    private char middlename;
    private LocalDateTime active;
   

    public Employee(int id, int employeetypeid, int departmentid, int shiftid, String badgeid, String firstname, String lastname, char middlename, LocalDateTime active) {
        this.id = id;
        this.employeetypeid = employeetypeid;
        this.departmentid = departmentid;
        this.shiftid = shiftid;
        this.badgeid = badgeid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        try{
            Connection conn = null;
            String query = "INSERT INTO employee.id VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            id = ps.executeUpdate();
        }
        catch(SQLException e){e.printStackTrace();}
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

    public char getMiddlename() {
        return middlename;
    }

    public void setMiddlename(char middlename) {
        this.middlename = middlename;
    }

    public LocalDateTime getActive() {
        return active;
    }

    public void setActive(LocalDateTime active) {
        this.active = active;
    }
    
}
