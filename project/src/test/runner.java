package test;

import InformationProvider.SessionInformation;
import InformationProvider.Service.ServiceType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileM;
import SubscriptionType.GreenMobileS;
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
		
		Subscriber s3 = SubscriberFactory.createSubsriber(
				"Jürgen", "Schmidt", 
				TerminalType.PhairPhone, new GreenMobileS()
				);
		
		manager.addSubscriber(s1);
		manager.addSubscriber(s2);
		manager.addSubscriber(s3);
		
		SessionInformation si = manager.simulateSession(s1, ServiceType.HDVideo, 8000);
		System.out.println(si);
		si = manager.simulateSession(s1, ServiceType.HDVideo, 8000);
		System.out.println(si);
	}

}
