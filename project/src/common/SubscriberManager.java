package common;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import InformationProvider.SessionInformation;
import InformationProvider.Service.ServiceType;
import Subscriber.Subscriber;
import exception.NoDataVolumeException;
import exception.NoSignalException;
import exception.NoSupportedRanTechnologyException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@XmlRootElement
public class SubscriberManager {
	
	@XmlElement
	private ArrayList<Subscriber> subscriberList;

	public SubscriberManager() {
		subscriberList = new ArrayList<>();
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

	public void simulateDays(int amountOfDays) {
		// TODO validate amountOfDays and simulate
		throw new NotImplementedException();			
	}

	public ArrayList<Invoice> invoiceAllSubscriber() {
		ArrayList<Invoice> invoiceList = new ArrayList<>();
		for (Subscriber tmp : subscriberList) {
			invoiceList.add(tmp.invoice());
		}
		return invoiceList;
	}

	public Invoice invoiceAndRemoveSubscriber(Subscriber subscriber) {
		Invoice invoice = subscriber.invoice();
		removeSubscriber(subscriber);
		return invoice;
	}
}
