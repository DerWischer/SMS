package test;

import InformationProvider.Service.ServiceType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileM;
import SubscriptionType.GreenMobileS;
import common.JAXBHandler;
import common.SubscriberManager;

public class runner {

	public static void main(String[] args) {		
		SubscriberManager manager = new SubscriberManager();
		
		Subscriber s1 = SubscriberFactory.createSubsriber(
				"Hans", "Schmidt", 
				TerminalType.PhairPhone, new GreenMobileS()
				);
		
		Subscriber s2 = SubscriberFactory.createSubsriber(
				"Peter", "Schmidt", 
				TerminalType.PhairPhone, new GreenMobileM()
				);		
		
		manager.addSubscriber(s1);
		manager.addSubscriber(s2);
		
		manager.simulateSession(s1, ServiceType.VoiceCall, 61);
		manager.simulateSession(s1, ServiceType.Browsing,5);
		
		JAXBHandler handler = new JAXBHandler();
		handler.marshall(manager);
		
		manager = null;
		manager = handler.unmarshall();
		
		System.out.println(manager.getSubscriberCount());
		
	}

}
