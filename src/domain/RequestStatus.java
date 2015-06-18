package domain;

/**
 * class containing the status of a request, used for friend requests, join group requests, etc.
 */
public enum RequestStatus {


     REJECTED("rejected"), WAITING("waiting");

     private String status;

     RequestStatus(String status){
         this.status = status;
     }

     public String getStatus() {
              return status;
     }

     public static RequestStatus fromString(String i){
            if(i.equals(RequestStatus.REJECTED.getStatus()))
                return RequestStatus.REJECTED;
            if(i.equals(RequestStatus.WAITING.getStatus()))
                return RequestStatus.WAITING;
            return null;
        }

}
