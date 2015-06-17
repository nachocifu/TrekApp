package domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Coordinates")
public class Coordinates {
	
	@DatabaseField
	private double x;
	
	@DatabaseField
	private double y;
	
	@DatabaseField (generatedId=true)
	private Integer id;
	
	public Coordinates(){
	}
	
	public Coordinates(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public double getDistanceBetweenCoordinates(Coordinates coor1, Coordinates coor2){
		return Math.sqrt((coor2.getX() - coor1.getX()) + (coor2.getY() - coor1.getY()));
	}
	
	public void updateCoordinates(Double x, Double y){
		this.x = x;
		this.y = y;
	}
	
	public Double getX(){
		return this.x;
	}
	
	public Double getY(){
		return this.y;
	}
}
