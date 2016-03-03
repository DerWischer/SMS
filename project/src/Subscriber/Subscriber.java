package Subscriber;

import InformationProvider.Terminal.TerminalType;
import SubscriptionType.SubscriptionType;
import common.Invoice;

public class Subscriber {
	private String forename, surname, imsi;
	private TerminalType terminal;
	private SubscriptionType subscription;
	
	Subscriber(String forename, String surname, String imsi, TerminalType terminal, SubscriptionType subscription){
		this.forename = forename;
		this.surname = surname;
		this.imsi = imsi;
		this.terminal = terminal;
		this.subscription = subscription;
	}
	
	public String getForename() {
		return forename;
	}
	
	public void setForename(String name) {
		this.forename = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String name) {
		this.surname = name;
	}
	
	public String getFullName() {
		return getForename() + " " + getSurname();
	}
	
	public String getIMSI() {
		return imsi;
	}
	
	public TerminalType getTerminalType(){
		return terminal;
	}
	
	public void setTerminType(TerminalType type){
		this.terminal = type;
	}
	
	public SubscriptionType getSubscriptionType() {
		return subscription;
	}
	
	public Invoice invoice(){
		//TODO generate an invoice
		
		/* NOTE: Attribute in SubscriptionType are reset to default after invoking 
		 * Invoice. Therefore store Attributes BEFORE invoking !!!
		 */
		
		int usedExtraMintes = getSubscriptionType().getUsedExtraMinutes();
		double charges = getSubscriptionType().invoice();
		Invoice invoice = new Invoice(this, usedExtraMintes, charges);
		return invoice;
	}
}
