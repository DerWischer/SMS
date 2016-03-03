package common;

import java.util.ArrayList;

import InformationProvider.RAN.RANInformation;
import InformationProvider.RAN.RANTechnology;
import InformationProvider.Service.ServiceInformation;
import InformationProvider.Service.ServiceType;
import InformationProvider.Signal.Signal;
import InformationProvider.Signal.SignalQualityType;
import InformationProvider.Terminal.TerminalInformation;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import SubscriptionType.SubscriptionType;
import exception.NoDataVolumeException;
import exception.NoSignalException;
import exception.NoSupportedRanTechnologyException;

public class Session {
	private ServiceType service;
	private int time;
	private Subscriber subscriber;
	private SignalQualityType signal;

	public Session(Subscriber subscriber) {
		this.subscriber = subscriber;
		time = -1;
	}

	public void simulate(ServiceType service, int timeInSeconds)
			throws NoSignalException, NoSupportedRanTechnologyException, NoDataVolumeException, IllegalArgumentException {
		if (timeInSeconds <= 0)
			throw new IllegalArgumentException("Time must not be below 1 second");
		
		TerminalType terminal = subscriber.getTerminalType();
		this.service = service;
		this.signal = Signal.getSignalQuality();
		switch (service) {
		case AppDownload:
			simulateDataUsage(service, terminal, timeInSeconds);
			break;
		case Browsing:
			simulateDataUsage(service, terminal, timeInSeconds);
			break;
		case HDVideo:
			simulateDataUsage(service, terminal, timeInSeconds);
			break;
		case VoiceCall:
			simulateVoiceCall(terminal, timeInSeconds);
			break;
		default:
			break;
		}
	}

	private void simulateDataUsage(ServiceType service, TerminalType terminal, int timeInSeconds) 
			throws NoSignalException, NoSupportedRanTechnologyException, NoDataVolumeException {
		
		if (signal == SignalQualityType.NA) {
			time = 0;
			throw new NoSignalException();
		} else {
			ArrayList<RANTechnology> ranList = TerminalInformation.getSupportedRANTechnology(terminal);

			RANTechnology usedRAN;
			if (ranList.contains(RANTechnology.LTE))
				usedRAN = RANTechnology.LTE;
			else if (ranList.contains(RANTechnology.HSPA))
				usedRAN = RANTechnology.HSPA;
			else 
				throw new NoSupportedRanTechnologyException();				

			// determine throughput
			int availableThroughput = RANInformation.getAvailableThroughput(signal, usedRAN);
			int neededThroughput = ServiceInformation.getDataRateInMBits(service);
			int actualThroughput = Math.min(availableThroughput, neededThroughput);

			SubscriptionType subscription = getSubscriptionType();
			// determine volume
			int availableVolume = subscription.getDataVolumeInMBits();
			int consumedVolume = (int) (actualThroughput * timeInSeconds);

			if (availableVolume == 0) {
				time = 0;
				throw new NoDataVolumeException();
			} else if (consumedVolume <= availableVolume) {
				subscription.consumeDataVolume(consumedVolume);
				time = timeInSeconds;
			} else if (consumedVolume > availableVolume) {
				int actualSessionTimeInSeconds = availableVolume / actualThroughput;
				time = actualSessionTimeInSeconds;
				subscription.consumeDataVolume(availableThroughput);
			}
		}
	}

	private void simulateVoiceCall(TerminalType terminal, int timeInSeconds)
			throws NoSignalException, NoSupportedRanTechnologyException {
		ArrayList<RANTechnology> ranList = TerminalInformation.getSupportedRANTechnology(terminal);

		RANTechnology usedTechnology = null;
		for (RANTechnology tmp : ranList) {
			if (RANInformation.supportForVoiceCall(tmp)) {
				usedTechnology = tmp;
				break;
			}
		}

		if (usedTechnology == null)
			throw new NoSupportedRanTechnologyException();

		int minutes = getMinutes(timeInSeconds);
		subscriber.getSubscriptionType().consumeMinutes(minutes);
		time = timeInSeconds;
	}

	private int getMinutes(int timeInSeconds) {
		double dMin = (double) timeInSeconds / 60;
		return (int) Math.ceil(dMin);
	}

	public ServiceType getServiceType() {
		return service;
	}

	public int getTimeInSecons() {
		return time;
	}

	public String getUserName() {
		return subscriber.getFullName();
	}

	public TerminalType getUserTerminal() {
		return subscriber.getTerminalType();
	}

	public SubscriptionType getSubscriptionType() {
		return subscriber.getSubscriptionType();
	}
	
	public SignalQualityType getSignal() {
		return signal;
	}
}
