package repositorySQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.sun.xml.internal.ws.util.StringUtils;

import domain.Group;
import domain.Profile;
import domain.Trip;
import domain.TripStatus;

public class TripRepository extends AbstractRepository<Trip> {

    public TripRepository(String pathToDataBase, Class<Trip> reposClass) {
        super(pathToDataBase, reposClass);
        // TODO Auto-generated constructor stub
    }

    /**
     * Method to search the users
     * @param searchTxt string to search
     * @return response list of Profiles
     */
    public List<Trip> searchBy(String txt, Date StartDate, Date endDate, String originCity, String endCity, TripStatus status, Profile currentUser ) {
        ConnectionSource connectionSource = null;
        List<Trip> response = new ArrayList<Trip>();
        Boolean orFlag = false;

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<Trip, String> dao = DaoManager.createDao(connectionSource, Trip.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT trip.* ");
                qryBuilder.append("FROM Trip trip ");
                qryBuilder.append("WHERE ");

                if( txt != null && txt.length() == 0 ){
                    if( orFlag )
                        qryBuilder.append("OR ");
                    orFlag = true;
                    qryBuilder.append( "(trip. LIKE '%" + txt + "%') ");
                }


                String query = qryBuilder.toString();

                RawRowMapper<Trip> mapper = dao.getRawRowMapper();
                GenericRawResults<Trip> rawResponse = dao.queryRaw(query, mapper);
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

    @Override
    public List<Trip> searchBy(String searchTxt, Profile currentUser) {
        return this.searchBy(searchTxt, null, null, null, null, null, currentUser);
    }

    /**
     * Query for trip by trip ID
     * @param id The trip ID
     * @param depth as to how inside to load the trip
     * @return response The trip or null if no results
     */
    public Trip getById(Integer tripId, Integer depth){
       Trip obj = super.getById(tripId);
       System.err.println("En este momento se deberian levantar los objetos y colocar adentro del trip con id: " + tripId);
       //obj = this.loadObjectsInside(obj, depth);
       return obj;
    }

    /**
     * Update the trip until the depth indicated
     * @param obj The trip
     * @param depth as to how inside to persist the trip
     */
    public void update(Trip obj, Integer depth){
        super.update(obj);
        System.err.println("En este momento se deberian persistir los objetos adentro del trip con id: " + obj.getId());
        //this.persistObjectsInside(obj, depth);
    }

}
