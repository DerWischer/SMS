package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import InformationProvider.SessionInformation;
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
import exception.NoSignalException;

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
		SubscriptionType subscription = new GreenMobileS();
		Subscriber s = SubscriberFactory.createSubsriber("Hans", "Schmidt", TerminalType.PearaPhone4s, subscription);
		
		manager.addSubscriber(s);
		manager.simulateSession(s, ServiceType.VoiceCall, 1);

		int extraMinutes = subscription.getUsedExtraMinutes();
		assertEquals(1, extraMinutes);
	}

	@Test
	public void test_FreeMinutes1() {
		SubscriptionType subscription = new GreenMobileM();
		subscription.consumeMinutes(subscription.getFreeMinutes()-1);
		Subscriber s = SubscriberFactory.createSubsriber("Hans", "Schmidt", TerminalType.PearaPhone4s, subscription);
		
		manager.addSubscriber(s);
		manager.simulateSession(s, ServiceType.VoiceCall, 1);

		int freeMinutes = subscription.getFreeMinutes();
		int extraMinutes = subscription.getUsedExtraMinutes();
		
		assertEquals(0, freeMinutes);
		assertEquals(0,  extraMinutes);
	}

	@Test
	public void test_FreeMinutes2() {
		SubscriptionType subscription = new GreenMobileM();
		Subscriber s = SubscriberFactory.createSubsriber("Hans", "Schmidt", TerminalType.PearaPhone4s, subscription);		

		manager.addSubscriber(s);
		manager.simulateSession(s, ServiceType.VoiceCall, 1);

		int freeMinutes = subscription.getFreeMinutes();
		int extraMinutes = subscription.getUsedExtraMinutes();
		assertEquals(99, freeMinutes);
		assertEquals(0, extraMinutes);
	}

	@Test
	public void test_NoDataVolume() {
		SubscriptionType subscription = new GreenMobileM();
		subscription.consumeDataVolume(subscription.getDataVolumeInMBits());
		
		Subscriber s = SubscriberFactory.createSubsriber("Hans", "Schmidt", TerminalType.PearaPhone4s, subscription);
		
		Signal.debug_UseFixedSignal(SignalQualityType.Good);
		
		manager.addSubscriber(s);
		SessionInformation sessionInformation = manager.simulateSession(s, ServiceType.AppDownload, 1);
		
		String time = sessionInformation.getTime();
		assertEquals("0", time);
	}

	@Test
	public void test_AvailableDataVolume() {
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.SoniZperiaX3, new GreenMobileL(), manager.getDate());
		manager.addSubscriber(s);
		int avDataVolume = s.getSubscriptionType().getDataVolumeInMBits();
		Signal.debug_UseFixedSignal(SignalQualityType.Medium);
		manager.simulateSession(s, ServiceType.AppDownload, 8);
		assertEquals(avDataVolume - 80, s.getSubscriptionType().getDataVolumeInMBits());
	}

	@Test
	public void test_AvailableThroughput() {
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.PearaPhone4s, new GreenMobileS(), manager.getDate());
		manager.addSubscriber(s);
		int avDataVolume = s.getSubscriptionType().getDataVolumeInMBits();
		Signal.debug_UseFixedSignal(SignalQualityType.Low);
		manager.simulateSession(s, ServiceType.HDVideo, 8);
		assertEquals(avDataVolume - 16, s.getSubscriptionType().getDataVolumeInMBits());
	}

	@Test
	public void test_EqualThroughput() {
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.PhairPhone, new GreenMobileS(), manager.getDate());
		manager.addSubscriber(s);
		int avDataVolume = s.getSubscriptionType().getDataVolumeInMBits();
		Signal.debug_UseFixedSignal(SignalQualityType.Low);
		manager.simulateSession(s, ServiceType.Browsing, 8);
		assertEquals(avDataVolume - 16, s.getSubscriptionType().getDataVolumeInMBits());
	}

	@Test
	public void test_NoSignal() {
		Subscriber s = SubscriberFactory.createSubsriber("Beathe", "Beispielbraut", TerminalType.PhairPhone, new GreenMobileS(), manager.getDate());
		manager.addSubscriber(s);
		int avDataVolume = s.getSubscriptionType().getDataVolumeInMBits();
		Signal.debug_UseFixedSignal(SignalQualityType.NA);
		SessionInformation info = manager.simulateSession(s, ServiceType.Browsing, 1);
		assertEquals(avDataVolume, s.getSubscriptionType().getDataVolumeInMBits());
		assertEquals(info.getInfo(), new NoSignalException().getMessage());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_NoSessionTime() {
		SubscriberManager manager = new SubscriberManager();
				TerminalType.PearaPhone4s, new GreenMobileS(),
				manager.getDate()
				);
		manager.addSubscriber(s);

		manager.simulateSession(s, ServiceType.VoiceCall, 0);
	}

	@Test
	public void test_NegativeDataVolume() {

	}

	@Test
	public void test_OverMaxDataVolumeS() {
		SubscriptionType subscription = new GreenMobileS();		
		int availableDataVolume=subscription.getDataVolumeInMBits();
		assertEquals(4000, availableDataVolume);
	}

	@Test
	public void test_OverMaxDataVolumeM() {
         SubscriptionType subscription = new GreenMobileM();
         int availableDataVolume= subscription.getDataVolumeInMBits();
         assertEquals(16000, availableDataVolume);
	}

	@Test
	public void test_OverMaxDataVolumeL() {
		SubscriptionType subscription = new GreenMobileL();
        int availableDataVolume= subscription.getDataVolumeInMBits();
        assertEquals(40000, availableDataVolume);    
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
