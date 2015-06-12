package domain;

public enum RequestStatus {
	
	 REJECTED(new Integer(0)), WAITING(new Integer(1));
	 
	 private Integer status;
	 
	 RequestStatus(Integer status){
		 this.status = status;
	 }
		 
	 public Integer getStatus() {
		      return status;
	 }
	 
	 public static RequestStatus fromInteger(Integer i){
	        if(i.equals(RequestStatus.REJECTED.getStatus()))
	            return RequestStatus.REJECTED;
	        if(i.equals(RequestStatus.WAITING.getStatus()))
	            return RequestStatus.WAITING;
	        return null;
	    }
}
