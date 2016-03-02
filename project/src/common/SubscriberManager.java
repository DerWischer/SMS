package common;

import java.util.ArrayList;

import InformationProvider.Service.ServiceType;
import Subscriber.Subscriber;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SubscriberManager {

	private ArrayList<Subscriber> subscriberList;
	
	public SubscriberManager() {
		subscriberList = new ArrayList<>();
	}
	
	public void addSubscriber(Subscriber subscriber) {
		subscriberList.add(subscriber);
	}
	
	public void removeSubscriber(Subscriber subscriber) {
		subscriberList.remove(subscriber);
	}
	
	public void simulateSession(Subscriber subscriber, ServiceType service, long timeInMillis) {
		// TODO validate parameters and simulate
		throw new NotImplementedException();
	}
	
	public void simulateDays(int amountOfDays) {
		// TODO validate amountOfDays and simulate 
		throw new NotImplementedException();
	}
	
	public ArrayList<Invoice> invoiceAllSubscriber() {
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		for (Subscriber tmp: subscriberList) {
			invoiceList.add(tmp.invoice());
		}
		return invoiceList;
	}
	
	public Invoice invoiceAndRemoveSubscriber(Subscriber subscriber){
		Invoice invoice = subscriber.invoice();
		removeSubscriber(subscriber);
		return invoice;
	}
}
