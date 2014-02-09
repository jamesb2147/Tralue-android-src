package com.embaucha.tralue.model;

public class PVRow {
	int id;
	String points_program;
	float point_value;
	
	public PVRow() {
		
	}
	
	public PVRow(String points_program, float point_value) {
		this.points_program = points_program;
		this.point_value = point_value;
	}
	
	public PVRow(String points_program, float point_value, int id) {
		this.points_program = points_program;
		this.point_value = point_value;
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPointsProgram(String points_program) {
		this.points_program = points_program;
	}
	
	public void setPointValue(float point_value) {
		this.point_value = point_value;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPointsProgram() {
		return points_program;
	}
	
	public float getPointValue() {
		return point_value;
	}
}