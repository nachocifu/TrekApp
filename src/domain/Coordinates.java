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
	
	public Coordinates(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public Coordinates(){
	}
	
	public Double getX(){
		return this.x;
	}
	
	public void setX(Double x){
		this.x=x;
	}
	
	public Double getY(){
		return this.y;
	}
	public void setY(Double y){
		this.y=y;
	}
}
