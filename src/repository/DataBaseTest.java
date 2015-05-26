package repository;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import domain.Chat;
import domain.Message;
import domain.UniversalString;
import domain.User;

/**
 * Esta clase se encarga de arrancar, testear y probar el servidor
 * Se usara para la parte de desarrollo y se borra para la version final.
 * @author nacho
 */
public class DataBaseTest {

	public static void main(String[] args) {
		System.err.println("### Local Database TEST ###");
		
		DataBaseTest repo = new DataBaseTest();
		
		/** Start all tables. */
		
		
	}
	
	
	private String databaseUrl = "jdbc:sqlite:DataBase";

	public DataBaseTest(){
	}
	
	/**no usar*/
	private void startTables(){
		System.err.println("###\n###\n###\tSTARTING TABLES\n###\n###");
		
		start(User.class);
		start(UniversalString.class);
		start(Chat.class);
		start(Message.class);
	}
	
	/**idem*/
	private <T> void start(Class<T> type){
		
		System.out.println("### Starting table " + type.getSimpleName() + " ###");
		try{
		
			Class.forName("org.sqlite.JDBC");
			
			/** create a connection source to our database */
			ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
			
			/** create the table */ 
			TableUtils.createTableIfNotExists(connectionSource, type);
	
			/** close the connection source */
			connectionSource.close();
		}catch(Exception e){
			System.err.println("[ERROR] || " + e.getMessage());
			System.err.println("ERROR");
		}
		System.err.println("OK");;
	}
}

