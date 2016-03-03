package test;

import static org.junit.Assert.*;

import java.lang.Character.Subset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import InformationProvider.Service.ServiceType;
import InformationProvider.Signal.Signal;
import InformationProvider.Signal.SignalQualityType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileL;
import SubscriptionType.GreenMobileM;
import SubscriptionType.GreenMobileS;
import SubscriptionType.SubscriptionType;
import common.SubscriberManager;

public class SessionTest {

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
	public void test_ExtraMinutes() {

	}

	@Test
	public void test_FreeMinutes1() {

	}

	@Test
	public void test_FreeMinutes2() {
		
	}

	@Test
	public void test_NoDataVolume() {

	}

	@Test
	public void test_AvailableDataVolume() {
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL());
		manager.addSubscriber(s);
		int avDataVolume = s.getSubscriptionType().getDataVolumeInMBits();
		Signal.debug_UseFixedSignal(SignalQualityType.Medium);
		manager.simulateSession(s, ServiceType.AppDownload, 8);
		assertEquals(avDataVolume - 80, s.getSubscriptionType().getDataVolumeInMBits());
	}

	@Test
	public void test_AvailableThroughput() {

	}

	@Test
	public void test_EqualThroughput() {

	}

	@Test
	public void test_NoSignal() {

	}

	@Test(expected = IllegalArgumentException.class)
	public void test_NoSessionTime() {
		SubscriberManager manager = new SubscriberManager();
		Subscriber s = SubscriberFactory.createSubsriber("Hans", "Schmidt", 
				TerminalType.PearaPhone4s, new GreenMobileS()
				);
		manager.addSubscriber(s);
		
		manager.simulateSession(s, ServiceType.VoiceCall, 0);				
	}

	@Test
	public void test_NegativeDataVolume() {

	}

	@Test
	public void test_OverMaxDataVolumeS() {

	}

	@Test
	public void test_OverMaxDataVolumeM() {

	}

	@Test
	public void test_OverMaxDataVolumeL() {

	}

	@Test
	public void test_NegativeFreeMinutes() {

	}

	@Test
	public void test_OverMaxFreeMinutesS() {
		SubscriptionType subscription = new GreenMobileS();
		int freeMinutes = subscription.getFreeMinutes();
		
		assertEquals(freeMinutes, 0);
	}

	@Test
	public void test_OverMaxFreeMinutesM() {
		SubscriptionType subscription = new GreenMobileM();
		int freeMinutes = subscription.getFreeMinutes();
		
		assertEquals(freeMinutes, 100);
	}

	@Test
	public void test_OverMaxFreeMinutesL() {
		SubscriptionType subscription = new GreenMobileL();
		int freeMinutes = subscription.getFreeMinutes();
		
		assertEquals(freeMinutes, 150);
	}	

}
