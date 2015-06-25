package domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class GroupTest {

	/*Group Tests*/
	@SuppressWarnings("deprecation")
	Profile admin=new Profile("username", "natalia", "navas", 1234, new Date(1992,12,18), true, "123456", "buenos aires", "email@itba.edu.ar");
	Group grp= new Group("groupName",admin , 3, 21, "buenos aires");
	
	@Test(expected= IllegalArgumentException.class)
	public void groupNameTest1(){
		grp.setGroupName(null);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void groupNameTest2(){
		grp.setGroupName("     ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setAdminTest(){
		@SuppressWarnings("deprecation")
		Profile fakeAdmin= new Profile("user", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		grp.setAdminUser(fakeAdmin);
	}

	/*When added a trip is added as OPEN, in order to send a review the group trip should be closed*/
	@SuppressWarnings("deprecation")
	@Test(expected=TripNotClosedException.class)
	public void tripNotClosedTest() throws TripNotClosedException, InvalidPermissionException{
		Profile fakeUser= new Profile("user", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Profile fakeUser2= new Profile("user2", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		grp.addMember(fakeUser);
		grp.addMember(fakeUser2);
		grp.addGroupTrip(new Trip(new Date(2015,1,1), new Date(2015,2,2), 232.2, "description", "buenos aires", "neuquen"));
		grp.sendReviewToAMember(fakeUser, fakeUser2, "msg", 3);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addMemberTest1() throws InvalidPermissionException{
		grp.addMember(admin);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addMemberTest2() throws InvalidPermissionException{
		@SuppressWarnings("deprecation")
		Profile fakeUser= new Profile("user", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		grp.addMember(fakeUser);
		grp.addMember(fakeUser);
	}
	
	/*the amount of maximum members for this group is three*/
	@SuppressWarnings("deprecation")
	@Test(expected=InvalidPermissionException.class)
	public void addMemberTest3() throws InvalidPermissionException{
		grp.addMember(new Profile("user1", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));
		grp.addMember(new Profile("user2", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));
		grp.addMember(new Profile("user3", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));
		grp.addMember(new Profile("user4", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));
		grp.addMember(new Profile("user5", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));
		grp.addMember(new Profile("user6", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));	
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteMemberTest1(){
		Profile notMember =new Profile("user1", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		grp.deleteMember(notMember);
	}
	
	@Test
	public void deleteMemberTest2() throws InvalidPermissionException{
		Profile admin2=new Profile("username2", "natalia", "navas", 1234, new Date(1992,12,18), true, "123456", "buenos aires", "email@itba.edu.ar");
		//System.out.println(grp.groupSize());
		Group grp2= new Group("groupName2",admin2 , 3, 21, "buenos aires");
		assertTrue(grp2.deleteMember(admin2));
	}
	
	@Test
	public void deleteMemberTest3() throws InvalidPermissionException{
		Profile member=new Profile("user1", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		grp.addMember(member);
		grp.addMember(new Profile("user2", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar"));
		assertTrue(member.getGroups().contains(grp));
		assertFalse(grp.deleteMember(member));
		assertFalse(member.getGroups().contains(grp));		
	}
	
	@Test
	public void deleteMemberTest4() throws InvalidPermissionException{
		Profile member=new Profile("user1", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Group grp4= new Group("groupName2",member , 3, 21, "buenos aires");
		grp.addMember(member);
		assertTrue(member.getGroups().contains(grp4));
		assertTrue(grp4.deleteMember(member));
		assertFalse(member.getGroups().contains(grp4));	
		
		
		
	}
	@Test(expected=InvalidPermissionException.class)
	public void addGroupTest() throws InvalidPermissionException{
		Trip trip1= new Trip(new Date(2014,1,1), new Date(2016,2,2), 123.1, "string1", "string2", "string4");
		Trip trip2= new Trip(new Date(2014,1,1), new Date(2016,2,2), 123.1, "string1", "string2", "string4");
		grp.addGroupTrip(trip1);
		grp.addGroupTrip(trip2);
	}
	
	@Test
	public void adminInGroupTest(){
		Profile groupAdm= new Profile("groupAdmin", "name", "surname" ,new Date(2000,01,01), true, "pass", "buenos aires", "mail@itba.edu.ar");
		Group grp3= new Group("groupName",groupAdm , 3, 21, "buenos aires");
		assertTrue(groupAdm.getGroups().contains(grp3));
		assertTrue(grp3.deleteMember(groupAdm));
		assertFalse(groupAdm.getGroups().contains(grp3));
	}


}
