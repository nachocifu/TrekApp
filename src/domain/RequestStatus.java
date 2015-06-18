package domain;

/**
 * class containing the status of a request, used for friend requests, join group requests, etc.
 */
public enum RequestStatus {
	
	 REJECTED(0), WAITING(1);
	 
	 private Integer status;
	 
	 /**
	  * @param status with which the request will be initiated
	  */
	 RequestStatus(Integer status){
		 this.status = status;
	 }
		
	 /**
	  * @return the request status
	  */
	 public Integer getStatus() {
		      return status;
	 }
	 
	 /**
	  * @param i with which it compares the status
	  * @return enum value, can either be REJECTED or WAITING
	  */
	 public static RequestStatus fromInteger(Integer i){
	        if(i.equals(RequestStatus.REJECTED.getStatus()))
	            return RequestStatus.REJECTED;
	        if(i.equals(RequestStatus.WAITING.getStatus()))
	            return RequestStatus.WAITING;
	        return null;
	    }
}
