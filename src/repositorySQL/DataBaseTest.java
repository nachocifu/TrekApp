package repositorySQL;

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
import domain.InvalidPermissionException;
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


    public static void main(String[] args) throws Exception {
        System.err.println("### Local Database TEST ###");
        System.out.println("starting database admin");
        DataBaseTest repoAdmin = new DataBaseTest();
        System.out.println("Starting tables");
        repoAdmin.start(Profile.class);
        repoAdmin.start(Group.class);
        repoAdmin.start(Trip.class);
        repoAdmin.start(ProfileRelationship.class);
        repoAdmin.start(Coordinates.class);

        System.err.println("#########POPULATE USERS");
        repoAdmin.populateUsers();
        System.err.println("#########OPERATE WITH USERS");
        //repoAdmin.operateWithUsers();
        System.err.println("#########POPULATE GROUPS");
        //repoAdmin.populateGroups();
        System.err.println("#########OPERATE WITH GROUPS");

        System.err.println("--------END-------");

        //Application app = Application.getInstance();
    }


    private void operateWithUsers() throws ServerException, UserNameAlreadyExistsException, InvalidPermissionException {
        ProfileRepository userRepo = new ProfileRepository("DataBase", Profile.class);

        /** Operate */
        System.out.println("get by id user1");
        Profile user1 = userRepo.getById(1,2);
        System.out.println("get by id user2");
        Profile user2 = userRepo.getById(2,2);
        System.out.println("update user2");
        userRepo.update(user2);
        HashSet<Profile> lista = new HashSet<Profile>();
        lista.add(user2);
        user1.setFriends(lista);
        user1.addFriendRequest(user2);
        System.out.println("update user1");
        userRepo.update(user1,2);
        //System.out.println(user1.getName() +" "+user1.getUsrName()+ " " + user1.getFriends());
        user1 = userRepo.getById(1,2);
        System.out.println("print users inside " + user1.getUsrName());
        for(Profile each: user1.getFriends())
        	System.out.println(each.getUsrName() + "lista" + each.getFriends().isEmpty());
    }

    private void populateGroups() throws ServerException, UserNameAlreadyExistsException {
    	ProfileRepository userRepo = new ProfileRepository("DataBase", Profile.class);
    	GroupRepository groupRepo = new GroupRepository("DataBase", Group.class);
        System.out.println("### Populating Group table ###");


        List<Group> pool = new ArrayList<Group>();
            /**Populate*/
            Profile admin = userRepo.getById(1);
            Group   group = new Group("grupo1", admin, 5, 3, "baires");
            System.out.println(group == null);
            groupRepo.add(group);
            //admin.joinGroup(group);
            userRepo.update(admin, 2);

            admin = userRepo.getById(2);
            group = new Group("grupo2", admin, 5, 3, "Mendoza");
            groupRepo.add(group);
            //admin.joinGroup(groupRepo.getById(2));
            userRepo.update(admin, 2);


    }


    private void populateUsers() throws ServerException, UserNameAlreadyExistsException {


        ProfileRepository userRepo = new ProfileRepository("DataBase", Profile.class);

            /**Populate*/
        HashSet<Profile> pool = new HashSet<Profile>();
        pool.add(new Profile("nacho", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));

            pool.add(new Profile("naty", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));

            pool.add(new Profile("kochi", "Daniel", "Kochian", new Date(7, 5, 1994) , false, "agua", "neuquen", "kochis.mail@gmail.com") );

            for(Profile each : pool){
            	System.out.println();
                System.out.println("Add user: " + each.getUsrName());
            	userRepo.add(each);
            }
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

