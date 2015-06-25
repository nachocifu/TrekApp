package domain;


import java.util.Date;

import org.junit.Test;

public class ReviewTest{

	@SuppressWarnings("deprecation")
	@Test(expected=IllegalArgumentException.class)
	public void test(){
		Profile usr1= new Profile("user1", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Profile usr2= new Profile("user2", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		@SuppressWarnings("unused")
		Review rev= new Review(usr1, usr2, "", 8);
	}

}
