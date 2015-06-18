package domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The text object.
 * @author nacho
 *
 */
@DatabaseTable(tableName = "UniversalString")
public class UniversalString {
	
	/** the id of the text */
	@DatabaseField( id = true )
	private Integer id;
	
	/** the text */
	@DatabaseField
	private String text;
	
	/** the language of the text */
	@DatabaseField
	private String lang; 
	
	
	/** Empty Constructor for ORM persistence */
	public UniversalString(){
	}
	
	public UniversalString(Integer id, String lang, String text){
		this.id = id;
		this.text = text;
		this.lang = lang;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	
}
