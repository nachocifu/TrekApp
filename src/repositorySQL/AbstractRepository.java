package repositorySQL;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import domain.Profile;
import domain.QueryFilters;
import domain.UserNameAlreadyExistsException;

/**
 *
 * To be extended by all repositorys.
 * Operates between the data base and the services.
 * Provides file access to the services logic.
 * @author nacho
 *
 */
public abstract class AbstractRepository<T> {

    /** path to the folder containing all the binary files */
    protected final String databaseUrl;

    /** the class of the objects this repository handles */
    private final Class<T> reposClass;


    public AbstractRepository(final String pathToDataBase, final Class<T> reposClass){
        this.databaseUrl = ("jdbc:sqlite:" + pathToDataBase);
        this.reposClass = reposClass;
    }

    /**
     * Returns object with the id specified.
     * @param id
     * @return response object requested or null
     */
    public T getById(Integer id){

        T response = null;
        ConnectionSource connectionSource = null;
        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<T, String> dao = DaoManager.createDao(connectionSource, this.reposClass);

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

    /**
     * Updates the object. If failure, return number.
     * @param obj
     * @return
     */
    public Integer update(T obj){
        Integer response = null;
        ConnectionSource connectionSource = null;
        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<T, String> dao = DaoManager.createDao(connectionSource, this.reposClass);

                /** check if object exists and update */
                response = dao.update(obj);

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

    /**
     * Adds the object. Returns if it succeedes.
     * @param obj to save on system
     * @return status if success is true or false
     * @throws UserNameAlreadyExistsException
     * @throws ServerException
     */
    public void add(T obj) throws UserNameAlreadyExistsException, ServerException{
        ConnectionSource connectionSource = null;
        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<T, String> dao = DaoManager.createDao(connectionSource, this.reposClass);

                /** check if object exists and update */
                dao.createOrUpdate(obj);

            }
            catch(Exception e){
                System.err.println("[ERROR] || " + e.getMessage());
            }
            finally{
                /** close the connection source */
                connectionSource.close();
            }
        }
        catch(KeyAlreadyExistsException e){
            System.err.println("[ERROR] || " + e.getMessage());
            throw new UserNameAlreadyExistsException("ERROR || Unable to register user. Username already exists.");
        }
        catch(SQLException e){
            System.err.println("[ERROR] || " + e.getMessage());
            throw new ServerException("ERROR || Failed to register new user.");
        }
    }

    /**
     * Removes the object referenced by id.
     * If it doesn't exist does nothing.
     * @param id
     */
    public void delete(T obj){

        ConnectionSource connectionSource = null;
        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<T, String> dao = DaoManager.createDao(connectionSource, this.reposClass);

                /** check if object exists and remove */
                dao.delete(obj);

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
    }

    /**
     * Search  the T objects for the search text.
     * @param searchTxt
     * @return response The list of objects that match the text in someway.
     */
    public abstract List<T> searchBy(String searchTxt, Profile currentUser);



}
