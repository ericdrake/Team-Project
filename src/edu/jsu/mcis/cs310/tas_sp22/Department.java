/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;

/**
 *
 * @author jasmi
 */
public class Department {
    private int id;
    private String description;
    private int terminalId;

    public Department(int id, String description, int terminalId) {
        this.id = id;
        this.description = description;
        this.terminalId = terminalId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getTerminalId() {
        return terminalId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(id).append(" ");
        sb.append("(").append(description).append("):");
        sb.append(" terminalid: ").append(terminalId);
        return sb.toString();
    }

    
    
    
}
