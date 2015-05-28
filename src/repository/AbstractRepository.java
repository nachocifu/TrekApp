package repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import domain.QueryFilters;
import domain.User;

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
    public T getById(String id){

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
                response = dao.queryForId(id);

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
     * Adds the object. If failure, return number.
     * @param obj
     * @return
     */
    public boolean add(T obj){
        ConnectionSource connectionSource = null;
        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<T, String> dao = DaoManager.createDao(connectionSource, this.reposClass);

                /** check if object exists and update */
                dao.create(obj);

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
            return false;
        }
        return true;

    }

    /**
     * Removes the object referenced by id.
     * If it doesn't exist does nothing.
     * @param id
     */
    public void remove(String id){

        ConnectionSource connectionSource = null;
        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<T, String> dao = DaoManager.createDao(connectionSource, this.reposClass);

                /** check if object exists and remove */
                dao.deleteById(id);

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
    public abstract List<T> searchBy(String searchTxt, T currentUser);

}
