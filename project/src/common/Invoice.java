package common;

import Subscriber.Subscriber;

public class Invoice {

	private final Subscriber subscriber;
	private final int usedExtraMinutes;
	private final double charges;
	
	public Invoice(Subscriber subscriber, int usedExtraMintes, double charges){
		this.subscriber = subscriber;
		this.usedExtraMinutes = usedExtraMintes;
		this.charges = charges;
	}
	
	public String getSubscriberName(){
		return subscriber.getFullName();
	}
	
	public double getCharges() {
		return charges;
	}
	
	public int getUsedExtraMinutes() {
		return usedExtraMinutes;
	}
	
	public String getSubscriptionType() {
		return subscriber.getSubscriptionType().toString();
	}
	
}
