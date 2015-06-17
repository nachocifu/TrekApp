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
	
	/**
	 * @param coor1
	 * @param coor2
	 * @return Returns the linear distance between this coordinate and another
	 */
	public double getDistanceBetweenOtherCoordinate(Coordinates coor2){
		return Math.sqrt((coor2.getX() - this.x) + (coor2.getY() - this.y));
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
