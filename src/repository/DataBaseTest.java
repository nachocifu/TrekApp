package repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import controllers.Application;
import domain.Group;
import domain.Profile;
import domain.Trip;

/**
 * Esta clase se encarga de arrancar, testear y probar el servidor
 * Se usara para la parte de desarrollo y se borra para la version final.
 *
 * NO EJECUTAR YA QUE PISA LA BASE DE DATOS
 *
 * @author nacho
 */
public class DataBaseTest {
    private String databaseUrl = "jdbc:sqlite:DataBase";


    public static void main(String[] args) {
        System.err.println("### Local Database TEST ###");
        System.out.println("starting database admin");
        DataBaseTest repoAdmin = new DataBaseTest();
        System.out.println("Starting tables");
        repoAdmin.start(Profile.class);
        repoAdmin.populateUsers();
        repoAdmin.start(Group.class);
        //repoAdmin.populateGroups();
        repoAdmin.start(Trip.class);

        System.out.println("--------END-------");

        Application app = Application.getInstance();
        //ProfileController user1 = app.get
    }


    private void populateGroups() {
        System.out.println("### Populating Group table ###");

        Class<Group> type = Group.class;
        List<Group> pool = new ArrayList<Group>();
        boolean status = true;

        try{

            Class.forName("org.sqlite.JDBC");

            /** create a connection source to our database */
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

            /**create DAO*/
            Dao<Group, String> dao = DaoManager.createDao(connectionSource, type);

            /**Populate*/
            //pool.add(Group e)

            for(Group each : pool)
                dao.createOrUpdate(each);

            /** close the connection source */
            connectionSource.close();
        }catch(Exception e){
            System.err.println("[ERROR] || " + e.getMessage() + "\n" + e.getStackTrace().toString());
            status = false;
        }
        if(status) System.out.println("OK");
    }


    private void populateUsers() {
        System.out.println("### Populating users table ###");

        Class<Profile> type = Profile.class;
        List<Profile> pool = new ArrayList<Profile>();
        boolean status = true;

        try{

            Class.forName("org.sqlite.JDBC");

            /** create a connection source to our database */
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

            /**create DAO*/
            Dao<Profile, String> dao = DaoManager.createDao(connectionSource, type);

            /**Populate*/
            Profile p = new Profile("nacho", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com");
            pool.add(p);
            Profile p2 = new Profile("nacho", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com");
            pool.add(p2);
            pool.add(new Profile("kochi", "Daniel", "Kochian", null, false, "agua", "neuquen", "kochis.mail@gmail.com") );

            /**Test operations*/
            Profile user = pool.get(0);
            Profile user2 = pool.get(1);
            user2.sendFriendRequest(user);
            user.acceptFriend(user2);

            System.out.println("addeo bien al amgio");

            for(Profile each : pool)
                dao.createOrUpdate(each);

            /** close the connection source */
            connectionSource.close();
        }catch(Exception e){
            System.err.println("[ERROR] || " + e.getMessage());
            status = false;
        }
        if(status) System.out.println("OK");
    }




    public DataBaseTest(){
    }


    /**
     * NO USAR.
     * Activa las tablas. Los cambios que hace son en memoria de la base de datos!!!!
     * @param type
     */
    private <T> void start(Class<T> type){

        System.out.println("### Starting table " + type.getSimpleName() + " ###");
        boolean status = true;
        try{

            Class.forName("org.sqlite.JDBC");

            /** create a connection source to our database */
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

            /**drop and create the table */
            TableUtils.dropTable(connectionSource, type, false);
            TableUtils.createTableIfNotExists(connectionSource, type);

            /** close the connection source */
            connectionSource.close();
        }catch(Exception e){
            System.err.println("[ERROR] || " + e.getMessage() + e.getStackTrace().toString());
            status = false;
        }
        if(status) System.out.println("OK");
    }
}

