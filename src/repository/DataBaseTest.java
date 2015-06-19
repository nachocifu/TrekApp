package repository;

import java.rmi.ServerException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import controllers.Application;
import domain.Coordinates;
import domain.Group;
import domain.Profile;
import domain.Trip;
import domain.UserNameAlreadyExistsException;

/**
 * Esta clase se encarga de arrancar, testear y probar el servidor
 * Se usara para la parte de desarrollo y se borra para la version final.
 *
 * NO EJECUTAR YA QUE PISA LA BASE DE DATOS
 *
 */
public class DataBaseTest {
    private String databaseUrl = "jdbc:sqlite:DataBase";


    public static void main(String[] args) throws ServerException, UserNameAlreadyExistsException {
        System.err.println("### Local Database TEST ###");
        System.out.println("starting database admin");
        DataBaseTest repoAdmin = new DataBaseTest();
        System.out.println("Starting tables");
        repoAdmin.start(Profile.class);
        repoAdmin.start(Group.class);
        repoAdmin.start(Trip.class);
        repoAdmin.start(ProfileRelationship.class);
        repoAdmin.start(Coordinates.class);

        repoAdmin.populateUsers();
        repoAdmin.operateWithUsers();
        System.out.println("--------END-------");

        Application app = Application.getInstance();
        //ProfileController user1 = app.get
    }


    private void operateWithUsers() {
        // TODO Auto-generated method stub

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


    private void populateUsers() throws ServerException, UserNameAlreadyExistsException {


        ProfileRepository userRepo = new ProfileRepository("DataBase", Profile.class);

            /**Populate*/
        HashSet<Profile> pool = new HashSet<Profile>();
        pool.add(new Profile("nacho", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));

            pool.add(new Profile("naty", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));

            pool.add(new Profile("kochi", "Daniel", "Kochian", new Date(7, 5, 1994) , false, "agua", "neuquen", "kochis.mail@gmail.com") );

            for(Profile each : pool)
                userRepo.add(each);
            System.out.println("asldfasdlkfasdf");
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
            //TableUtils.dropTable(connectionSource, type, false);
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

