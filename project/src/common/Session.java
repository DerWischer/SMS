package common;
import InformationProvider.Service.ServiceType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import SubscriptionType.SubscriptionType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Session {
	private ServiceType service;
	private long time;
	private Subscriber subscriber;
	
	public Session(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	
	public void simulate(ServiceType service, long timeInMillis){
		// TODO Simulate a session
		throw new NotImplementedException();
	}
	
	public ServiceType getServiceType(){
		return service;
	}
	
	public long getTimeInMillis(){
		return time;
	}
	
	public String getUserName(){
		return subscriber.getFullName();
	}
	
	public TerminalType getUserTerminal() {
		return subscriber.getTerminalType();
	}
	
	public SubscriptionType getSubscriptionType() {
		return subscriber.getSubscriptionType();
	}
}
