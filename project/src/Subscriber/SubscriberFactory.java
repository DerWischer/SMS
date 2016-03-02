package Subscriber;

import java.util.Random;

import InformationProvider.Terminal.TerminalType;
import SubscriptionType.SubscriptionType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SubscriberFactory {
	private static Random r = new Random();
	
	private static String generateIMSI() {
		// TODO generate a unique 10 digit long number 
		throw new NotImplementedException();		
	}
	
	static public Subscriber createSubsriber(String forename, String surname, TerminalType terminal, SubscriptionType subscription) {
		String imsi = generateIMSI();
		// TODO validate data
		return new Subscriber(forename, surname, imsi, terminal, subscription);		
	}
}
