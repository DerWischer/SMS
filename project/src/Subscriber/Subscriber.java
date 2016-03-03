package Subscriber;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import InformationProvider.Terminal.TerminalType;
import SubscriptionType.SubscriptionType;
import common.Invoice;

@XmlRootElement
public class Subscriber {
	
	private String forename;	
	private String surname;
	private String imsi;
	private TerminalType terminal;
	@XmlElement
	private SubscriptionType subscription;
	
	Subscriber(String forename, String surname, String imsi, TerminalType terminal, SubscriptionType subscription){
		this.forename = forename;
		this.surname = surname;
		this.imsi = imsi;
		this.terminal = terminal;
		this.subscription = subscription;
	}
	
	public Subscriber() {
		// TODO Auto-generated constructor stub
	}
	
	@XmlAttribute
	public String getForename() {
		return forename;
	}
	
	public void setForename(String name) {
		this.forename = name;
	}
	
	@XmlAttribute
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String name) {
		this.surname = name;
	}
	
	@XmlAttribute
	public String getFullName() {
		return getForename() + " " + getSurname();
	}
	
	@XmlAttribute
	public String getIMSI() {
		return imsi;
	}
	
	@XmlAttribute
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
