package common;

import java.util.Date;

import Subscriber.Subscriber;

public class Invoice {

	private final Subscriber subscriber;
	private final int usedExtraMinutes;
	private final double charges;
	private final Date date;
	
	public Invoice(Subscriber subscriber, int usedExtraMintes, double charges, Date date){
		this.subscriber = subscriber;
		this.usedExtraMinutes = usedExtraMintes;
		this.charges = charges;
		this.date = date;
	}
	
	public String getSubscriberName(){
		return subscriber.getFullName();
	}
	
	public double getCharges() {
		return charges;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getUsedExtraMinutes() {
		return usedExtraMinutes;
	}
	
	public String getSubscriptionType() {
		return subscriber.getSubscriptionType().toString();
	}
	
	public String getIMSI() {
		return subscriber.getIMSI();
	}
	
}
