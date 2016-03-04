package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import InformationProvider.SessionInformation;
import InformationProvider.Service.ServiceType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileL;
import exception.NoDataVolumeException;
import exception.NoSignalException;
import exception.NoSupportedRanTechnologyException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@XmlRootElement
public class SubscriberManager {
	
	@XmlElement
	private ArrayList<Subscriber> subscriberList;
	private Date date;
	private int amountofDays;

	public SubscriberManager() {
		if(subscriberList == null)
			subscriberList = new ArrayList<>();
		if(date == null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				date = format.parse("2016/01/01");
			} catch (ParseException e) {
				//Shouldn't be possible to reach.
			}
		}
	}

	public void addSubscriber(Subscriber subscriber) {
		subscriberList.add(subscriber);
	}

	public int getSubscriberCount() {
		return subscriberList.size();
	}

	public Subscriber getSubscriber(int index) throws IndexOutOfBoundsException {
		if (index >= getSubscriberCount())
			throw new IndexOutOfBoundsException();
		else
			return subscriberList.get(index);
	}
	
	public Date getDate(){
		return date;
	}

	public void removeSubscriber(Subscriber subscriber) {
		subscriberList.remove(subscriber);
	}

	public SessionInformation simulateSession(Subscriber subscriber, ServiceType service, int timeInSeconds)
			throws IllegalArgumentException {
		Session session = new Session(subscriber);

		String info = "";
		try {
			session.simulate(service, timeInSeconds);
		} catch (NoSignalException | NoSupportedRanTechnologyException | NoDataVolumeException e) {
			info = e.getMessage();
		} catch (IllegalArgumentException e) {
			throw e;
		}

		String strName = session.getUserName();
		String strService = session.getServiceType().name();
		String strSubscription = session.getSubscriptionType().toString();
		String strTerminal = session.getUserTerminal().name();
		String strTime = "" + session.getTimeInSecons();
		String strSignal = "" + session.getSignal();		
		SessionInformation sessionInfo = new SessionInformation(strName, strService, strSignal, strSubscription,
				strTerminal, strTime, info);
		
		return sessionInfo;
	}

	public ArrayList<Invoice> simulateDays(int amountOfDays) {
		if(amountOfDays < 1){
			throw new IllegalArgumentException("The amount of simulated days has to be greater than 0.");
		}

		ArrayList<Invoice> invoiceArr = new ArrayList<>();
		
		for(int i = 0; i<amountOfDays; i++){
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int oldMonth = c.get(Calendar.MONTH);
			int oldYear = c.get(Calendar.YEAR);
		
			c.add(c.DATE, 1);
			int newMonth = c.get(Calendar.MONTH);
			int newYear = c.get(Calendar.YEAR);
		
			int monthDiff = newMonth - oldMonth;
			int yearDiff = newYear - oldYear;
		
			if(monthDiff > 0 || yearDiff > 0){
					invoiceArr.addAll(this.invoiceAllSubscriber(date));
			}
			date = c.getTime();
		}
		return invoiceArr;	}

	public ArrayList<Invoice> invoiceAllSubscriber(Date date) {
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		for (Subscriber tmp : subscriberList) {
			invoiceList.add(tmp.invoice(date));
		}
		return invoiceList;
	}

	public Invoice invoiceAndRemoveSubscriber(Subscriber subscriber) {
		Invoice invoice = subscriber.invoice(date);
		removeSubscriber(subscriber);
		return invoice;
	}
}
