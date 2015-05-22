package domain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A single message object. Many will make a chat.
 * @author nacho
 *
 */
@DatabaseTable(tableName = "Messages")
public class Message {
	
	/** id of the message */
	@DatabaseField( id = true)
	private Integer id;
	
	/** text of the message */ 
	@DatabaseField
	private String text;
	
	/** empty constructor for persistance usage */ 
	public Message(){
	}
	
	public Message(String text){
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
