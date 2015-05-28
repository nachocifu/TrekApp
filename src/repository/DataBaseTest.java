package repository;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import domain.Profile;

/**
 * Esta clase se encarga de arrancar, testear y probar el servidor
 * Se usara para la parte de desarrollo y se borra para la version final.
 * 
 * NO EJECUTAR YA QUE PISA LA BASE DE DATOS
 * 
 * @author nacho
 */
public class DataBaseTest {

	public static void main(String[] args) {
		System.err.println("### Local Database TEST ###");
		System.out.println("starting database admin");
		DataBaseTest repoAdmin = new DataBaseTest();
		System.out.println("starting tables");
		repoAdmin.start(Profile.class);
	}
	
	
	private String databaseUrl = "jdbc:sqlite:DataBase";

	public DataBaseTest(){
	}
	
	
	/**
	 * NO USAR.
	 * Activa las tablas. Los cambios que hace son en memoria de la base de datos!!!!
	 * @param type
	 */
	private <T> void start(Class<T> type){
		
		System.out.println("### Starting table " + type.getSimpleName() + " ###");
		try{
		
			Class.forName("org.sqlite.JDBC");
			
			/** create a connection source to our database */
			ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
			
			/** create the table */ 
			TableUtils.dropTable(connectionSource, type, false);
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

