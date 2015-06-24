package repositorySQL;


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
import domain.Coordinates;
import domain.Group;
import domain.Profile;
import domain.RequestStatus;
import domain.Review;
import domain.Trip;
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
                    response.add(this.loadObjectsInside(each, 2));
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
                qryBuilder.append("SELECT prf.* ");
                qryBuilder.append("FROM Profile prf ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "prf.usrName = \"" + userName + "\"");
                qryBuilder.append(" AND prf.password = \"" + passWord + "\"");

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
                qryBuilder.append( "prfl.usrName = \"" + userName + "\"");

                String query = qryBuilder.toString();

                RawRowMapper<Profile> mapper = dao.getRawRowMapper();
                GenericRawResults<Profile> rawResponse = dao. queryRaw(query, mapper);

                response = rawResponse.getFirstResult();
                response = this.loadObjectsInside(response, 2);
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
     * @param depth as to how inside to load the user
     * @return response The Profile or null if no results
     */
    public Profile getById(Integer userId, Integer depth){
       Profile obj = super.getById(userId);
       return this.loadObjectsInside(obj, depth);
    }

    /**
     * Update the user until the depth indicated
     * @param id The user ID
     * @param depth as to how inside to persist the user
     */
    public void update(Profile obj, Integer depth){
        super.update(obj);
        this.persistObjectsInside(obj, depth);
    }

    private void persistObjectsInside(Profile profile, Integer depth){

        /** If max depth is reached,  */
        if(depth <= 0 ||
                profile.getFriendRequests() == null ||
                profile.getFriends() == null ||
                profile.getBlockedUsrs() == null ||
                profile.getGroups() == null ||
                profile.getTrips() == null ||
                profile.getReviews() == null)
            return;
        System.out.println("persito objetos dentro de " + profile.getUsrName());
        ConnectionSource connectionSource = null;
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                //Persist Profiles
                /** Generate Collection to persist */
                Collection<ProfileRelationship> relationsList = new HashSet<ProfileRelationship>();
                /** instantiate the dao */
                Dao<ProfileRelationship, String> daoRelations = DaoManager.createDao(connectionSource, ProfileRelationship.class);

                for( Profile each: profile.getFriends()){
                    relationsList.add( new ProfileRelationship(profile, each, RelationshipEnum.FRIEND));
                    this.update(each, depth-1);
                }

                for( Profile each: profile.getBlockedUsrs()){
                    relationsList.add( new ProfileRelationship(profile, each, RelationshipEnum.BLOCKED));
                    this.update(each, depth-1);
                }
                for( Entry<Profile, RequestStatus> each: profile.getFriendRequests().entrySet()){
                    RelationshipEnum relation;
                    if( each.getValue().equals(RequestStatus.REJECTED))
                        relation = RelationshipEnum.REJECTED;
                    else
                        relation = RelationshipEnum.WAINTING;

                    relationsList.add( new ProfileRelationship(profile, each.getKey(), relation));
                }

                /** persist friend relations */
                for( ProfileRelationship each: relationsList){
                    daoRelations.createOrUpdate(each);
                }

                //Persist Groups
                System.out.println("llega a groups");
                /** instantiate the dao */
                Dao<GroupProfileRelationship, String> daoGroups = DaoManager.createDao(connectionSource, GroupProfileRelationship.class);

                GroupRepository groupRepo = new GroupRepository(this.pathToDataBase, Group.class);
                for( Group each: profile.getGroups()){
                    daoGroups.createOrUpdate( new GroupProfileRelationship(each, profile, each.getAdminUser().equals(profile), RelationshipEnum.MEMBER));
                    groupRepo.update(each, depth-1);
                }
                System.out.println("llega a trips");
                //Persist Trips
                /** instantiate the dao */
                Dao<TripProfileRelationship, String> daoTrips = DaoManager.createDao(connectionSource, TripProfileRelationship.class);

                TripRepository tripRepo = new TripRepository(this.pathToDataBase, Trip.class);
                for( Trip each: profile.getTrips()){
                    daoTrips.createOrUpdate(new TripProfileRelationship(each, profile));
                    tripRepo.update(each, depth-1);
                }

                //Persist Reviews
                /** instantiate the dao */
                Dao<ReviewRelationship, String> daoReview = DaoManager.createDao(connectionSource, ReviewRelationship.class);
                Dao<Review, String> daoRvw = DaoManager.createDao(connectionSource, Review.class);

                for( Review each: profile.getReviews()){
                    daoRvw.createOrUpdate(each);
                    daoReview.createOrUpdate(new ReviewRelationship(each));
                    this.update(each.getprofileOrigin(), depth-1);
                    this.update(each.getprofileTarget(), depth-1);
                }

                //Persist Coordinates
                Dao<Coordinates, String> daoCoordinates = DaoManager.createDao(connectionSource, Coordinates.class);
                daoCoordinates.update(profile.getCoordinates());

            }
            catch(Exception e){
                System.err.println("[ERROR] || Persisting objects inside profile " + profile.getUsrName());
                System.err.println( e.getMessage() );
            }
            finally{
                /** close the connection source */
                try {
                    connectionSource.close();
                } catch (SQLException e) {
                    System.err.println("ERROR || Errorr closing conection to file system");
                    e.printStackTrace();
                }
            }

        }

    private Profile loadObjectsInside(Profile profile, Integer depth){
        if( depth <= 0 )
            return profile;

        ConnectionSource connectionSource = null;

        try{
            try{
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                //Get Profiles
                /** instantiate the dao */
                Dao<ProfileRelationship, ?> daoProfiles = DaoManager.createDao(connectionSource, ProfileRelationship.class);

                /** Build native query */
                StringBuffer qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM profileRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "rel.thisUser = " + profile.getUsrId());

                String query = qryBuilder.toString();

                RawRowMapper<ProfileRelationship> mapper = daoProfiles.getRawRowMapper();
                GenericRawResults<ProfileRelationship> rawResponse = daoProfiles.queryRaw(query, mapper);
                List<ProfileRelationship> response = rawResponse.getResults();

                HashSet<Profile> friends = new HashSet<Profile>();
                HashSet<Profile> blockedUsers = new HashSet<Profile>();
                HashMap<Profile, RequestStatus> friendRequests = new HashMap<Profile, RequestStatus>();

                for(ProfileRelationship relation: response){
                    switch (RelationshipEnum.fromString(relation.getRelation())) {
                    case FRIEND:
                        friends.add(this.getById(relation.getOtherUser(), depth-1));
                        break;
                    case BLOCKED:
                        blockedUsers.add(this.getById(relation.getOtherUser(), depth-1));
                        break;
                    case REJECTED:
                    case WAINTING:
                        friendRequests.put(this.getById(relation.getOtherUser(), depth-1),RequestStatus.fromString(relation.getRelation()));
                        break;
                    }
                }
                System.out.println("cargo listas en user:: " + profile.getUsrName());
                profile.setFriends(friends);
                profile.setBlockedUsr(blockedUsers);
                profile.setFriendRequests(friendRequests);

                //Get Groups
                /** instantiate the dao */
                Dao<GroupProfileRelationship, ?> daoGroups = DaoManager.createDao(connectionSource, GroupProfileRelationship.class);

                /** Build native query */
                qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM GroupProfileRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "rel.user = " + profile.getUsrId());
                qryBuilder.append( " AND rel.rel = \"" + RelationshipEnum.MEMBER.getStatus() + "\"");

                query = qryBuilder.toString();

                RawRowMapper<GroupProfileRelationship> mapperGroup = daoGroups.getRawRowMapper();
                GenericRawResults<GroupProfileRelationship> rawResponseGroup = daoGroups.queryRaw(query, mapperGroup);
                List<GroupProfileRelationship> responseGroups = rawResponseGroup.getResults();

                GroupRepository groupRepo = new GroupRepository(this.pathToDataBase, Group.class);
                HashSet<Group> groups = new HashSet<Group>();

                for(GroupProfileRelationship each: responseGroups){
                    groups.add(groupRepo.getById(each.getGroup(), depth-1));
                }
                profile.setGroups(groups);

                //Get Trips
                /** instantiate the dao */
                Dao<TripProfileRelationship, ?> daoTrips = DaoManager.createDao(connectionSource, TripProfileRelationship.class);

                /** Build native query */
                qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM TripProfileRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "rel.user = " + profile.getUsrId());

                query = qryBuilder.toString();

                RawRowMapper<TripProfileRelationship> mapperTrip = daoTrips.getRawRowMapper();
                GenericRawResults<TripProfileRelationship> rawResponseTrip = daoTrips.queryRaw(query, mapperTrip);
                List<TripProfileRelationship> responseTrips = rawResponseTrip.getResults();

                TripRepository tripRepo = new TripRepository(this.pathToDataBase, Trip.class);
                HashSet<Trip> trips = new HashSet<Trip>();

                for(TripProfileRelationship each: responseTrips){
                    trips.add(tripRepo.getById(each.getTrip(), depth-1));
                }
                profile.setTrips(trips);

              //Get Reviews
                /** instantiate the dao */
                Dao<ReviewRelationship, ?> daoReviews = DaoManager.createDao(connectionSource, ReviewRelationship.class);

                Dao<Review, String> daoRvw = DaoManager.createDao(connectionSource, Review.class);

                /** Build native query */
                qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM ReviewRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append( "rel.toUser = " + profile.getUsrId());

                query = qryBuilder.toString();

                RawRowMapper<ReviewRelationship> mapperReviews = daoReviews.getRawRowMapper();
                GenericRawResults<ReviewRelationship> rawResponseReview = daoTrips.queryRaw(query, mapperReviews);
                List<ReviewRelationship> responseReview = rawResponseReview.getResults();

                HashSet<Review> reviews = new HashSet<Review>();
                Review aux;
                for(ReviewRelationship each: responseReview){
                    aux = daoRvw.queryForId(each.getReviewId().toString());
                    aux.setProfileOrigin(this.getById(each.getFrom(),2));
                    aux.setProfileTarget(this.getById(each.getTo(),2));
                    reviews.add(aux);
                }
                profile.setReviews(reviews);

              //Get Coordinates
                Dao<Coordinates, String> daoCoordinates = DaoManager.createDao(connectionSource, Coordinates.class);
                profile.checkIn(daoCoordinates.queryForId(profile.getUsrId().toString()));
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
