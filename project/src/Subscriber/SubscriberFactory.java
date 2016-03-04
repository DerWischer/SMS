package Subscriber;

import java.util.Date;
import java.util.Random;

import InformationProvider.Terminal.TerminalType;
import SubscriptionType.SubscriptionType;

public class SubscriberFactory {
	private static Random r = new Random();
	
	private SubscriberFactory() {}
	
	private static String generateIMSI() {	
		long number = (long) Math.floor(r.nextDouble() * 9000000000L) + 1000000000L;
		return "" + number;		
	}
	
	static public Subscriber createSubsriber(String forename, String surname, TerminalType terminal, SubscriptionType subscription, Date subscriptionDate) {
		String imsi = generateIMSI();
		if (forename.length() == 0 || surname.length() == 0 || surname == null || terminal == null) {
			throw new IllegalArgumentException();
		}
		return new Subscriber(forename, surname, imsi, terminal, subscription, subscriptionDate);		
	}
}
