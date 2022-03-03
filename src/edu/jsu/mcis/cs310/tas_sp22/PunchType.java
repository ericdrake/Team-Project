/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_sp22;
/**
 *
 * @author aneesh
 */

public enum PunchType {
	CLOCK_OUT("CLOCK OUT"), CLOCK_IN("CLOCK IN"), TIME_OUT("TIME OUT");

	private final String description;

	private PunchType(String d) {
		description = d;
	}

	@Override
	public String toString() {
		return description;
	}
}