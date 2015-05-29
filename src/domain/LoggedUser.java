package domain;

public class LoggedUser extends Profile{

	private static Profile loggedUser;
	   
	   /** A private Constructor prevents any other 
	    * class from instantiating.
	    */
	   private LoggedUser(){ }
	   
	   /** Static 'instance' method */
	   public static Profile getInstance( ) {
		   if(loggedUser==null)
			   loggedUser=new LoggedUser();
	      return loggedUser;
	   }
}
