package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import InformationProvider.Service.ServiceType;
import InformationProvider.Signal.Signal;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileL;
import SubscriptionType.GreenMobileS;
import common.Invoice;
import common.SubscriberManager;

public class simulateDaysTest {

	private SubscriberManager manager;
	
	@Before
	public void setup() {
		manager = new SubscriberManager();
	}

	@After
	public void teardown() {
		manager = null;
		Signal.debug_UseRandomSignal();
	}
	
	
	@Test
	public void TestSimulateDays() {
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		assertTrue(manager.simulateDays(1).isEmpty());
		assertEquals(manager.getDate().toString(),"Sat Jan 02 00:00:00 CET 2016");
	}
	
	@Test
	public void TestSimulateMonthPassing(){
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		ArrayList<Invoice> invoiceArr = manager.simulateDays(31);
		GreenMobileL subscription = new GreenMobileL();
		assertEquals(invoiceArr.size(), manager.getSubscriberCount());
		assertEquals(invoiceArr.get(0).getCharges(), subscription.getBasicFee(), 0.1);
		assertEquals(manager.getDate().toString(),"Mon Feb 01 00:00:00 CET 2016");
	}
	
	@Test
	public void TestSimulateMultipleMonths(){
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		assertEquals(manager.simulateDays(60).size(), manager.getSubscriberCount()*2);
		assertEquals(manager.getDate().toString(),"Tue Mar 01 00:00:00 CET 2016");
	}
	
	@Test
	public void TestSimulateYearPassing(){
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		assertEquals(manager.simulateDays(366).size(), manager.getSubscriberCount()*12);
		assertEquals(manager.getDate().toString(),"Sun Jan 01 00:00:00 CET 2017");
	}
	
	@Test
	public void TestSubscriberInMiddleOfMonth(){
		manager.simulateDays(15);
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		ArrayList<Invoice> invoiceArr = manager.simulateDays(16);
		assertEquals(invoiceArr.size(), manager.getSubscriberCount());
		assertEquals(invoiceArr.get(0).getCharges(), (new GreenMobileL()).getBasicFee() * 16/31, 0.01);
		assertEquals(manager.getDate().toString(),"Mon Feb 01 00:00:00 CET 2016");
	}
	
	@Test
	public void TestSubscriberInMiddleOfMonthWithRemoval(){
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		//assertEquals(manager.simulateDays(366).size(), manager.getSubscriberCount()*12);
		Invoice invoice = manager.invoiceAndRemoveSubscriber(s);
		assertEquals(invoice.getCharges(),(new GreenMobileL()).getBasicFee() * 1/31, 0.001);
	}
	
	@Test
	public void TestSubscriberInMiddleOfMonthWithExtraMinutes(){
		manager.simulateDays(15);
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileS(), manager.getDate());
		manager.addSubscriber(s);
		manager.simulateSession(s, ServiceType.VoiceCall, 59);
		ArrayList<Invoice> invoiceArr = manager.simulateDays(16);
		assertEquals(invoiceArr.size(), manager.getSubscriberCount());
		assertEquals(invoiceArr.get(0).getCharges(), (new GreenMobileS()).getBasicFee() * 16/31 + 0.08, 0.01);
		assertEquals(manager.getDate().toString(),"Mon Feb 01 00:00:00 CET 2016");
	}

}
