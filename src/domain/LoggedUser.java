package domain;

/**
 * Singleton representing the User that is logged currently in the application, depending on who this user is
 * are the priviledges he has in the applications groups, profiles, etc.
 */
public class LoggedUser extends Profile{


	private static Profile loggedUser;
	   
	   /** A private Constructor prevents any other 
	    * class from instantiating.
	    */
	   private LoggedUser(){ }
	   
	   /** Static 'instance' method, if a user is logged in it will return it
	    * if not it will create a new one
	    */
	   public static Profile getInstance( ) {
		   if(loggedUser==null)
			   loggedUser=new LoggedUser();
	      return loggedUser;
	   }
}
