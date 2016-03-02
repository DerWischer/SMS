package InformationProvider.RAN;

import InformationProvider.Signal.SignalQualityType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RANInformation {
	static public int getMaxThroughput(RANTechnology technology) {
		// TODO determine the max. possible throughput of the specified technology
		throw new NotImplementedException();
	}
	
	static public int getAvailableThroughput(SignalQualityType signalQuality, RANTechnology technology){
		// TODO determine the available throughput according to the specified signal quality and technology
		throw new NotImplementedException();
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
