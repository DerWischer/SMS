package InformationProvider;

public class SessionInformation {

	private final String username, service, signal, subscription, terminal, time, info, volumeInMBit;

	public SessionInformation(String username, String service, String signal, String subscription, String terminal, String time,
			String info, String volume) {
		this.username = username;
		this.service = service;
		this.signal = signal;
		this.subscription = subscription;
		this.terminal = terminal;
		this.time = time;
		this.info = info;
		this.volumeInMBit = volume;
	}

	public String getUsername() {
		return username;
	}

	public String getService() {
		return service;
	}

	public String getSubscription() {
		return subscription;
	}

	public String getTerminal() {
		return terminal;
	}

	public String getTime() {
		return time;
	}

	public String getInfo() {
		return info;
	}

	public String getSignal() {
		return signal;
	}
	
	@Override
	public String toString() {
		String res = "*** Session Information ***\n";
		res += "Name: " + getUsername() + "\n";
		res += "Service: " + getService() + "\n";
		res += "Signal: " + getSignal() + "\n";
		res += "Subscrip.: " + getSubscription() + "\n";
		res += "Terminal: " + getTerminal() + "\n";
		res += "Time: " + getTime() + "\n";
		res += "Info: " + getInfo() + "\n";
		res += "***************************";

		return res;
	}
	
}
