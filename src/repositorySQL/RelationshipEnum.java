package repositorySQL;

/**
 * Enum to be used by the repositorys only to facilitate persistance and
 * avoid the necessity of native querys when persisting collections.
 * @see ProfileRelationship
 * @author nacho
 *
 */
enum RelationshipEnum {

     REJECTED("rejected"), WAINTING("waiting"), FRIEND("friend"), BLOCKED("blocked"), MEMBER("member");

     private String status;

     RelationshipEnum(String status){
         this.status = status;
     }

     public String getStatus() {
              return status;
     }

     public static RelationshipEnum fromString(String i){
            if(i.equals(RelationshipEnum.REJECTED.getStatus()))
                return RelationshipEnum.REJECTED;
            if(i.equals(RelationshipEnum.WAINTING.getStatus()))
                return RelationshipEnum.WAINTING;
            if(i.equals(RelationshipEnum.FRIEND.getStatus()))
                return RelationshipEnum.FRIEND;
            if(i.equals(RelationshipEnum.MEMBER.getStatus()))
                return RelationshipEnum.MEMBER;
            return null;
        }
}