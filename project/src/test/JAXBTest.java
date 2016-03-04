package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import InformationProvider.Service.ServiceType;
import InformationProvider.Signal.Signal;
import InformationProvider.Signal.SignalQualityType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileS;
import SubscriptionType.SubscriptionType;
import common.JAXBHandler;
import common.SubscriberManager;

public class JAXBTest {

	private JAXBHandler handler;
	private SubscriberManager manager;

	@Before
	public void setup() {
		handler = new JAXBHandler();
		manager = null;
	}

	@After
	public void teardown() {
		handler = null;
		manager = null;
		Signal.debug_UseRandomSignal();
	}
	
	private void deleteSaveFile(){
		File f = new File(JAXBHandler.getSaveFilePath());
		if (f.exists()){
			try {
				Files.delete(f.toPath());
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void test_NoSaveFile() {
		deleteSaveFile();
		
		manager = handler.unmarshall();
		
		assertNotNull(manager);		
	}
	
	@Test
	public void test_EmptySaveFile() {
		deleteSaveFile();
		handler = new JAXBHandler();
		
		manager = handler.unmarshall();
		assertNotNull(manager);
	}
	
	@Test
	public void test_SubscriberAttributesContained(){
		manager = handler.unmarshall();
		Subscriber bSub = createSubscriberS();
		
		String bName = bSub.getFullName();
		String bImsi = bSub.getIMSI();
		TerminalType bTerminal = bSub.getTerminalType();
		
		manager.addSubscriber(bSub);
		handler.marshall(manager);
		
		manager = null;
		
		manager = handler.unmarshall();
		Subscriber aSub = manager.getSubscriber(0);
		
		String aName = aSub.getFullName();
		String aImsi = aSub.getIMSI();
		TerminalType aTerminal = aSub.getTerminalType();
		
		assertEquals(bName, aName);	
		assertEquals(bImsi, aImsi);
		assertEquals(bTerminal.name(), aTerminal.name());
	}
	
	@Test
	public void test_SubscriberSubscriptionTypeContained(){
		deleteSaveFile();
		manager = handler.unmarshall();
		Subscriber bSub = createSubscriberS();
		manager.addSubscriber(bSub);
		manager.simulateSession(bSub, ServiceType.VoiceCall, 125); // 3 minutes
		Signal.debug_UseFixedSignal(SignalQualityType.Medium);
		manager.simulateSession(bSub, ServiceType.AppDownload, 8); // consumes 80 MBit
				
		SubscriptionType subscription = bSub.getSubscriptionType();
		int bFreeMinutes = subscription.getFreeMinutes();
		int bExtraMinutes = subscription.getUsedExtraMinutes();
		int bVolume = subscription.getDataVolumeInMBits();
		subscription = null;
		
		handler.marshall(manager);
		manager = null;
		manager = handler.unmarshall();
		
		Subscriber aSub = manager.getSubscriber(0);
		subscription = aSub.getSubscriptionType();
		int aFreeMinutes = subscription.getFreeMinutes();
		int aExtraMinutes = subscription.getUsedExtraMinutes();
		int aVolume = subscription.getDataVolumeInMBits();
		
		assertEquals(bFreeMinutes, aFreeMinutes);
		assertEquals(bExtraMinutes, aExtraMinutes);
		assertEquals(bVolume, aVolume);
	}
	
	private Subscriber createSubscriberS(){
		String s1_Forename = "Hans";
		String s1_Surname = "Herrgott";
		TerminalType s1_terminal = TerminalType.PearaPhone4s;
		SubscriptionType s1_subscription = new GreenMobileS();
		Subscriber s1 = SubscriberFactory.createSubsriber(
				s1_Forename, s1_Surname, 
				s1_terminal, s1_subscription,
				manager.getDate());
		return s1;
	}
}
