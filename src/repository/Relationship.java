package repository;

/**
 * Enum to be used by the repositorys only to facilitate persistance and
 * avoid the necessity of native querys when persisting collections.
 * @see ProfileRelationship
 * @author nacho
 *
 */
public enum Relationship {

     REJECTED("rejected"), PENDING("pending"), FRIEND("friend"), BLOCKED("blocked");

     private String status;

     Relationship(String status){
         this.status = status;
     }

     public String getStatus() {
              return status;
     }

     public static Relationship fromInteger(Integer i){
            if(i.equals(Relationship.REJECTED.getStatus()))
                return Relationship.REJECTED;
            if(i.equals(Relationship.PENDING.getStatus()))
                return Relationship.PENDING;
            if(i.equals(Relationship.FRIEND.getStatus()))
                return Relationship.FRIEND;
            return null;
        }
}