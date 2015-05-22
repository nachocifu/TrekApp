package repository;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import domain.UniversalString;

public class MessageSource {

	/** path to the folder containing all the binary files */ 
	protected final String databaseUrl;
	
	public MessageSource(String pathToDataBase) {
		this.databaseUrl = ("jdbc:sqlite:" + pathToDataBase);
	}
	
	/**
	 * Returns string with the id specified.
	 * @param id
	 * @return response string requested or null
	 */
	public UniversalString getById(Integer id){
		
		UniversalString response = null;
		ConnectionSource connectionSource = null;
		try{
			try{
				Class.forName("org.sqlite.JDBC");
				/** create a connection source to our database */
				connectionSource = new JdbcConnectionSource(this.databaseUrl);
	    
				/** instantiate the dao */
				Dao<UniversalString , String> dao = DaoManager.createDao(connectionSource, UniversalString.class);
	    
				/** retrieve the object from the database by its id */
				response = dao.queryForId(id.toString());
	    
			}
			catch(Exception e){
				System.err.println("[ERROR] || " + e.getMessage());
			}
			finally{
				/** close the connection source */
				connectionSource.close();
			}
		}
		catch(SQLException e){
			System.err.println("[ERROR] || " + e.getMessage());
		}
		finally{
			return response;
		}
	}

	
}
