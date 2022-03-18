
package edu.jsu.mcis.cs310.tas_sp22;
import java.sql.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author user
 */
public class TAS {
    public static void main(String[] args){
         TASDatabase db = new TASDatabase("tasuser","War Room D", "localhost");
        
        if (db.isConnected()){
            System.err.println("Your Have Successfully Connected To The Database");
        }   
    }
    
}
