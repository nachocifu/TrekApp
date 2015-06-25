package domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class ProfileTest {

	@Test(expected=IllegalArgumentException.class)
	public void addReviewTest1() {
		Profile usr1= new Profile("user1", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Profile usr2= new Profile("user2", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		usr1.addReview(usr2, "    ",3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void addReviewTest2(){
		Profile usr3= new Profile("user3", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Profile usr4= new Profile("user4", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		usr3.addReview(usr4, "good", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void joinGroupTest(){
		Profile usr5= new Profile("user5", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Group grp=new Group("groupName",usr5 , 3, 21, "buenos aires");
		usr5.joinGroup(grp);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void leaveGroupTest(){
		Profile usr6= new Profile("user6", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Group grp=new Group("groupName",usr6 , 3, 21, "buenos aires");
		usr6.leaveGroup(grp);
		usr6.leaveGroup(grp);
		
	}
	
	@Test(expected=InvalidPasswordException.class)
	public void changePassTest1() throws InvalidPasswordException{
		Profile usr7= new Profile("user7", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		usr7.changePass("fakePassword", "newPass");
	}
	
	@Test(expected=InvalidPasswordException.class)
	public void changePassTest2() throws InvalidPasswordException{

		Profile usr8= new Profile("user8", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		usr8.changePass("pass", "    ");
	}
	
	
}

