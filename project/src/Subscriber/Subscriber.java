package Subscriber;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;import java.util.Calendar;
import java.util.Date;import InformationProvider.Terminal.TerminalType;
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
	private Date subscriptionDate;
	
	Subscriber(String forename, String surname, String imsi, TerminalType terminal, SubscriptionType subscription, Date subscriptionDate){
		this.forename = forename;
		this.surname = surname;
		this.imsi = imsi;
		this.terminal = terminal;
		this.subscription = subscription;
		this.subscriptionDate = subscriptionDate;
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
	
	private void setIMSI(String imsi) {
		this.imsi = imsi;
	}
	
	@XmlAttribute
	public TerminalType getTerminalType(){
		return terminal;
	}
	
	public void setTerminalType(TerminalType type){
		this.terminal = type;
	}
		
	public SubscriptionType getSubscriptionType() {
		return subscription;
	}
	
	public Invoice invoice(Date date){
		//TODO generate an invoice
		
		/* NOTE: Attribute in SubscriptionType are reset to default after invoking 
		 * Invoice. Therefore store Attributes BEFORE invoking !!!
		 */
		
		int usedExtraMintes = getSubscriptionType().getUsedExtraMinutes();
		double charges = getSubscriptionType().invoice();
		
		//See whether subscription and invoice is in same Month
		Calendar cOld = Calendar.getInstance();
		cOld.setTime(subscriptionDate);
		Calendar cNew = Calendar.getInstance();
		cNew.setTime(date);
		int daysOfMonth = cNew.getActualMaximum(Calendar.DAY_OF_MONTH);
		double fee = this.subscription.getBasicFee();
		double discount = 0;
		if((cOld.get(Calendar.MONTH) == cNew.get(Calendar.MONTH)) && (cOld.get(Calendar.YEAR) == cNew.get(Calendar.YEAR))){
			//Subscription and invoice are in the same month
			int dayDiff = cNew.get(Calendar.DATE) - cOld.get(Calendar.DATE)+1;
			discount = fee*(daysOfMonth-dayDiff)/daysOfMonth;
		} else {
			discount = fee*(daysOfMonth - cNew.get(Calendar.DATE))/daysOfMonth;
		}
		charges = charges - discount;
		
		Invoice invoice = new Invoice(this, usedExtraMintes, charges, date);
		return invoice;
	}
}
