package repository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import domain.Profile;

public class UserRepository extends AbstractRepository<Profile> {

    /** the class of the objects this repository handles */
    private final Class<Profile> profileClass = null;

    public UserRepository(String pathToDataBase,Class reposClass) {
        super(pathToDataBase, reposClass);
        // TODO Auto-generated constructor stub
    }

    /**
     * Method to search the users
     * @param searchTxt string to search
     * @return response list of Profiles
     */
    public List<Profile> searchBy(String searchTxt, Profile currentUser){
        ConnectionSource connectionSource = null;
        List<Profile> response = new ArrayList<Profile>();

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<Profile, String> dao = DaoManager.createDao(connectionSource, Profile.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT prf.* ");
                qryBuilder.append("FROM Profile prfl ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "prf.usrName LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.email LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.city LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.name LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.surname LIKE '%"+ searchTxt + "%' ");

                String query = qryBuilder.toString();

                RawRowMapper<Profile> mapper = dao.getRawRowMapper();
                GenericRawResults<Profile> rawResponse = dao.queryRaw(query, mapper);
                response = rawResponse.getResults();
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
        return response;
    }

    /**
     * Query if username and password match to any users.
     * NOTE: count(*) is not used do to limited knowledge on OrmLite
     * @param userName
     * @param passWord
     * @return boolean If username and password exist and match.
     */
    public boolean validateCredentials(String userName, String passWord) {
        ConnectionSource connectionSource = null;
        Boolean flag = false;

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<Profile, String> dao = DaoManager.createDao(connectionSource, Profile.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT prf ");
                qryBuilder.append("FROM Profile prfl ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "prf.usrName = " + userName);
                qryBuilder.append(" AND prf.password = " + passWord);

                String query = qryBuilder.toString();

                RawRowMapper<Profile> mapper = dao.getRawRowMapper();
                GenericRawResults<Profile> rawResponse = dao. queryRaw(query, mapper);

                if(rawResponse.getResults().size() > 0)
                    flag = true;
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
        return flag;
    }

    /**
     * get user by username
     */
    public Profile getById(String username){
        return null;
    }
}
