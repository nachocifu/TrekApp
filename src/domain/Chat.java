package domain;

import java.util.Set;

import com.j256.ormlite.table.DatabaseTable;

/**
 * A Chat, collection of messages 
 */
@DatabaseTable(tableName = "Chats")
public class Chat {
	
	private Chat(Set<Message> messages, String lang, String username ){
		
	}

}
