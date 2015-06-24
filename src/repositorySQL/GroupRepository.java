/**
 *
 */
package repositorySQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import domain.Group;
import domain.Profile;
import domain.RequestStatus;
import domain.Review;
import domain.Trip;

/**
 * The connection to the file system regarding groups and their persistence.
 */
public class GroupRepository extends AbstractRepository<Group> {

    public GroupRepository(String pathToDataBase, Class<Group> reposClass) {
        super(pathToDataBase, reposClass);
        // TODO Auto-generated constructor stub
    }

    /**
     * Search groups by text.
     *
     * @param searchTxt
     *            the text to search
     * @param currentUser
     *            to detect for blocked lists
     * @return response the list of groups to return
     */
    @Override
    public List<Group> searchBy(String searchTxt, Profile currentUser) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Query for group by group ID
     *
     * @param id
     *            The user ID
     * @param depth
     *            as to how inside to load the group
     * @return response The group or null if no results
     */
    public Group getById(Integer groupId, Integer depth) {
        Group obj = super.getById(groupId);
        obj = this.loadObjectsInside(obj, depth);
        return obj;
    }

    /**
     * Update the GROUP until the depth indicated
     *
     * @param obj
     *            The goup
     * @param depth
     *            as to how inside to persist the group
     */
    public void update(Group obj, Integer depth) {
        super.update(obj);
        this.persistObjectsInside(obj, depth);
    }

    private void persistObjectsInside(Group obj, Integer depth) {
        if (depth <= 0)
            return;

        ConnectionSource connectionSource = null;

        try {
            try {
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** Get necesarry repositorys */
                ProfileRepository userRepo = new ProfileRepository(
                        this.pathToDataBase, Group.class);

                /** Build native query */
                StringBuffer qryBuilder;

                //Persist Profiles
                /** instantiate the dao */
                Dao<GroupProfileRelationship, ?> daoProfiles = DaoManager
                        .createDao(connectionSource,
                                GroupProfileRelationship.class);
                /** Create collection to persist */
                List<GroupProfileRelationship> listToPersist = new ArrayList<GroupProfileRelationship>();

                for(Profile each:obj.getMembers()){
                    userRepo.update(each, depth-1);
                    listToPersist.add(new GroupProfileRelationship(obj, each, each.equals(obj.getAdminUser()), RelationshipEnum.MEMBER));
                }
                for(Entry<Profile, RequestStatus> each:obj.getMemberRequests().entrySet()){
                    userRepo.update(each.getKey(), depth - 1);
                    listToPersist.add(new GroupProfileRelationship(obj, each.getKey(), false, RelationshipEnum.fromString(each.getValue().getStatus())));
                }
                for(GroupProfileRelationship each: listToPersist)
                    daoProfiles.createOrUpdate(each);

              //Persist ReviewsPending
                /** instantiate the dao */
                Dao<GroupReviewsRelationship, ?> daoReview = DaoManager
                        .createDao(connectionSource,
                                GroupReviewsRelationship.class);
                /** Create collection to persist */
                for(Entry<Profile, HashSet<Profile>> each: obj.getMissingReviewsToMake().entrySet()){
                    for(Profile to: each.getValue()){
                        daoReview.createOrUpdate(new GroupReviewsRelationship(to, each.getKey(), obj));
                        userRepo.update(to, depth-1);
                    }
                    userRepo.update(each.getKey(), depth-1);
                }

                //Persist Trip
                if(obj.getGroupTrip() != null){
                TripRepository tripRepo = new TripRepository(this.pathToDataBase, Trip.class);
                Dao<TripGroupRelationship, ?> daoTrips = DaoManager.createDao(
                        connectionSource, TripGroupRelationship.class);
                daoTrips.createOrUpdate(new TripGroupRelationship(obj, obj.getGroupTrip()));
                tripRepo.update(obj.getGroupTrip());
                }
            } catch (Exception e) {
                System.err.println("[ERROR] || " + e.getMessage());
            } finally {
                /** close the connection source */
                connectionSource.close();
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] || " + e.getMessage());
        }
        return;
    }

    private Group loadObjectsInside(Group obj, Integer depth) {
        if (depth <= 0)
            return obj;

        ConnectionSource connectionSource = null;

        try {
            try {
                Class.forName("org.sqlite.JDBC");
                /** create a connection source to our database */
                connectionSource = new JdbcConnectionSource(this.databaseUrl);

                /** Build native query */
                StringBuffer qryBuilder;

                // Get Profiles
                /** instantiate the dao */
                Dao<GroupProfileRelationship, ?> daoProfiles = DaoManager
                        .createDao(connectionSource,
                                GroupProfileRelationship.class);

                /** Build native query */
                qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM GroupProfileRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append("rel.\"group\" = " + obj.getId());

                String query = qryBuilder.toString();

                RawRowMapper<GroupProfileRelationship> mapperProfile = daoProfiles
                        .getRawRowMapper();
                GenericRawResults<GroupProfileRelationship> rawResponseProfiles = daoProfiles
                        .queryRaw(query, mapperProfile);
                List<GroupProfileRelationship> responseProfiles = rawResponseProfiles
                        .getResults();

                ProfileRepository userRepo = new ProfileRepository(
                        this.pathToDataBase, Group.class);
                HashSet<Profile> profiles = new HashSet<Profile>();
                HashMap<Profile, RequestStatus> requests = new HashMap<Profile, RequestStatus>();

                Profile user = null;
                Profile admin = null;
                for (GroupProfileRelationship each : responseProfiles) {
                    user = userRepo.getById(each.getUser(), depth - 1);
                    if (each.getRel().equals(
                            RelationshipEnum.MEMBER.getStatus())) {
                        profiles.add(user);
                        if (each.getAdmin())
                            admin = user;
                    } else {
                        requests.put(user,
                                RequestStatus.fromString(each.getRel()));
                    }
                }
                obj.setRequests(requests);
                obj.setMembers(profiles);
                if(admin != null)
                    obj.setAdminUser(admin);

                // Get missingReviews list
                /** instantiate the dao */
                Dao<GroupReviewsRelationship, ?> daoReviews = DaoManager
                        .createDao(connectionSource,
                                GroupReviewsRelationship.class);

                /** Build native query */
                qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM GroupReviewsRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append("rel.\"group\" = " + obj.getId());

                query = qryBuilder.toString();

                RawRowMapper<GroupReviewsRelationship> mapperReviews = daoReviews
                        .getRawRowMapper();
                GenericRawResults<GroupReviewsRelationship> rawResponseReview = daoReviews
                        .queryRaw(query, mapperReviews);
                List<GroupReviewsRelationship> responseReview = rawResponseReview
                        .getResults();

                HashMap<Profile, HashSet<Profile>> reviews = new HashMap<Profile, HashSet<Profile>>();
                Profile from;
                Profile to;
                HashSet<Profile> toReview;
                for (GroupReviewsRelationship each : responseReview) {
                    from = userRepo.getById(each.getFrom(), depth - 1);
                    to = userRepo.getById(each.getTo(), depth - 1);

                    if (reviews.containsKey(from))
                        reviews.get(from).add(to);
                    else {
                        toReview = new HashSet<Profile>();
                        toReview.add(to);
                        reviews.put(from, toReview);
                    }
                }
                obj.setMissingReviewsToMake(reviews);

                // Get Trip
                /** instantiate the dao */
                Dao<TripGroupRelationship, ?> daoTrips = DaoManager.createDao(
                        connectionSource, TripGroupRelationship.class);

                /** Build native query */
                qryBuilder = new StringBuffer();
                qryBuilder.append("SELECT rel.* ");
                qryBuilder.append("FROM TripGroupRelationship rel ");
                qryBuilder.append("WHERE ");
                qryBuilder.append("rel.\"group\" = " + obj.getId());

                query = qryBuilder.toString();

                RawRowMapper<TripGroupRelationship> mapperTrip = daoTrips
                        .getRawRowMapper();
                GenericRawResults<TripGroupRelationship> rawResponseTrip = daoTrips
                        .queryRaw(query, mapperTrip);
                TripGroupRelationship responseTrips = rawResponseTrip
                        .getFirstResult();

                if(responseTrips != null){
                TripRepository tripRepo = new TripRepository(
                        this.pathToDataBase, Trip.class);
                obj.addGroupTrip(tripRepo.getById(responseTrips.getTrip()));
                }

            } catch (Exception e) {
                System.err.println("[ERROR] || Error Retrievieng group from repositories." + e.getMessage());
            } finally {
                /** close the connection source */
                connectionSource.close();
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] || Error closing connection to repo after retrieving group" + e.getMessage());
        }
        return obj;
    }

}
