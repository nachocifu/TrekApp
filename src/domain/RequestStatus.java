package domain;

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
