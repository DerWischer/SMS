package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileS;

public class SubscriberTest {

	private Subscriber s;
	
	@Before 
	public void setup(){
		
	}
	
	@After
	public void teardown() {
		s = null;
	}
	
	@Test
	public void test_CreateUser() {
		s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.PearaPhone4s, new GreenMobileS(), new Date());
		
		assertEquals("Beathe", s.getForename());
		assertEquals("Beispielbraut", s.getSurname());
		assertEquals("Beathe Beispielbraut", s.getFullName());
		
		assertEquals(TerminalType.PearaPhone4s, s.getTerminalType());
		assertEquals((new GreenMobileS()).toString(), s.getSubscriptionType().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_CreateUser_WithoutName(){
		s = SubscriberFactory.createSubsriber("", "Beispielbraut", TerminalType.PearaPhone4s, new GreenMobileS(), new Date());		
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_CreateUser_WithoutPassword(){
		s = SubscriberFactory.createSubsriber("Beathe", "", TerminalType.PearaPhone4s, new GreenMobileS(), new Date());		
	}
	
}
