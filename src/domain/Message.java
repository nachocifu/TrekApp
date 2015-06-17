package domain;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A single message object. Many will make a chat or a wall (in groups)
 */
@DatabaseTable(tableName = "Messages")
public class Message {
	
	/** id of the message */
	@DatabaseField( generatedId = true)
	private String msgId;
	
	/** text of the message */ 
	@DatabaseField
	private String text;
	
	/** empty constructor for persistence usage */ 
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
	public String getMsgId() {
		return this.msgId;
	}

}
