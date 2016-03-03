package InformationProvider.RAN;

import InformationProvider.Signal.Signal;
import InformationProvider.Signal.SignalQualityType;

public class RANInformation {
	static public int getMaxThroughputInMBits(RANTechnology technology) {
		int mbits = 0;
		switch (technology) {
		case GSM:
			mbits = 0;
			break;
		case HSPA:
			mbits = 20;
			break;
		case LTE:
			mbits = 300;
			break;
		default:
			break;		
		}
		return mbits;			
	}
	
	static public int getAvailableThroughput(SignalQualityType signalQuality, RANTechnology technology){		
		int maxThroughput = getMaxThroughputInMBits(technology);
		double signalFactor = Signal.SignalQualityToFactor(signalQuality);
		
		return (int) (maxThroughput * signalFactor);				
	}
	
	static public boolean supportForVoiceCall(RANTechnology technology) {
		boolean support = false;
		switch (technology) {		
		case GSM:
			support = true;
			break;
		default:
			support = false;
			break;
		}
		return support;
	}
}
