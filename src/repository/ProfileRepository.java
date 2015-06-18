package repository;


import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;

import controllers.Application;
import domain.Profile;
import domain.RequestStatus;
import domain.UserNameAlreadyExistsException;

public class ProfileRepository extends AbstractRepository<Profile> {

    /** the class of the objects this repository handles */
    private final Class<Profile> profileClass = null;

    public ProfileRepository(String pathToDataBase,Class reposClass) {
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
        List<Profile> list = new ArrayList<Profile>();
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
                qryBuilder.append("FROM Profile prf ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "prf.usrName LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.email LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.city LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.name LIKE '%" + searchTxt + "%' ");
                qryBuilder.append("OR prf.surname LIKE '%"+ searchTxt + "%' ");

                String query = qryBuilder.toString();

                RawRowMapper<Profile> mapper = dao.getRawRowMapper();
                GenericRawResults<Profile> rawResponse = dao.queryRaw(query, mapper);
                list = rawResponse.getResults();
                for(Profile each:list)
                    response.add(this.loadProfilesInside(each, 2));
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
     * Query for profile by Username
     * @param userName The username of the profile
     * @return response The Profile or null if no results
     */
    public Profile getById(String userName){
        ConnectionSource connectionSource = null;
        Profile response = null;

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<Profile, String> dao = DaoManager.createDao(connectionSource, Profile.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT prfl.* ");
                qryBuilder.append("FROM Profile prfl ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "prfl.usrName = " + userName);

                String query = qryBuilder.toString();

                RawRowMapper<Profile> mapper = dao.getRawRowMapper();
                GenericRawResults<Profile> rawResponse = dao. queryRaw(query, mapper);

                response = rawResponse.getFirstResult();
                response = this.loadProfilesInside(response, 2);
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
     * Query for profile by user ID
     * @param id The user ID
     * @return response The Profile or null if no results
     */
    public Profile getById(Integer userId){
        ConnectionSource connectionSource = null;
        Profile response = null;

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<Profile, String> dao = DaoManager.createDao(connectionSource, Profile.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT prfl.* ");
                qryBuilder.append("FROM Profile prfl ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "prfl.usrId = " + userId);

                String query = qryBuilder.toString();

                RawRowMapper<Profile> mapper = dao.getRawRowMapper();
                GenericRawResults<Profile> rawResponse = dao. queryRaw(query, mapper);

                response = rawResponse.getFirstResult();
                response = this.loadProfilesInside(response, 2);
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

    public Integer update(Profile obj, Integer eagerLevel){
        this.persistProfilesInside(obj, eagerLevel);
        return super.update(obj);
    }

    private void persistProfilesInside(Profile profile, Integer depth){

        /** If max depth is reached,  */
        if(depth <= 0)
            return;

        ConnectionSource connectionSource = null;
            try{
                Class.forName("org.sqlite.JDBC");

                /** Generate Collection to persist */
                Collection<ProfileRelationship> relationsList = new HashSet<ProfileRelationship>();

                for( Profile each: profile.getFriends())
                    relationsList.add( new ProfileRelationship(profile, each, Relationship.FRIEND));
                for( Profile each: profile.getBlockedUsrs())
                    relationsList.add( new ProfileRelationship(profile, each, Relationship.BLOCKED));

                for( Entry<Profile, RequestStatus> each: profile.getFriendRequests().entrySet()){
                    Relationship relation;
                    if( each.getValue().equals(RequestStatus.REJECTED))
                        relation = Relationship.REJECTED;
                    else
                        relation = Relationship.WAINTING;

                    relationsList.add( new ProfileRelationship(profile, each.getKey(), relation));
                }

                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(Application.getInstance().getDatabase());

                /** instantiate the dao */
                Dao<ProfileRelationship, String> dao = DaoManager.createDao(connectionSource, ProfileRelationship.class);

                /** persist friends */

                for( ProfileRelationship each: relationsList){
                    dao.update(each);
                }
            }
            catch(Exception e){
                System.err.println("[ERROR] || " + e.getMessage());
            }
            finally{
                /** close the connection source */
                try {
                    connectionSource.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            persistProfilesInside(profile, depth-1);
        }

    private Profile loadProfilesInside(Profile profile, Integer depth){

        ConnectionSource connectionSource = null;

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** instantiate the dao */
                Dao<ProfileRelationship, ?> dao = DaoManager.createDao(connectionSource, ProfileRelationship.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM profilesRelationships rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "rel.thisUser = " + profile.getUsrId());

                String query = qryBuilder.toString();

                RawRowMapper<ProfileRelationship> mapper = dao.getRawRowMapper();
                GenericRawResults<ProfileRelationship> rawResponse = dao.queryRaw(query, mapper);
                List<ProfileRelationship> response = rawResponse.getResults();

                Collection<Profile> friends = new HashSet<Profile>();
                Collection<Profile> blockedUsers = new HashSet<Profile>();
                HashMap<Profile, RequestStatus> friendRequests = new HashMap<Profile, RequestStatus>();
                for(ProfileRelationship relation: response){
                    switch (Relationship.fromString(relation.getRelation())) {
                    case FRIEND:
                        friends.add(this.getById(relation.getThisUser()));
                        break;
                    case BLOCKED:
                        blockedUsers.add(this.getById(relation.getThisUser()));
                        break;
                    case REJECTED:
                    case WAINTING:
                        friendRequests.put(this.getById(relation.getThisUser()),RequestStatus.fromString(relation.getRelation()));
                        break;
                    }
                }
                profile.setFriends(friends);
                profile.setBlockedUsr(blockedUsers);
                profile.setFriendRequests(friendRequests);
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
        return profile;
    }

}
