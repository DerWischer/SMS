package InformationProvider.Signal;

public class Signal {	
	
	static public SignalQualityType getSignalQuality() {
		if (Signal.signal != null)
			return Signal.signal;
		
		double r = Math.random();
		SignalQualityType quality;
		if (r < 0.25) {
			quality = SignalQualityType.NA;
		} else if (r < 0.5) {
			quality = SignalQualityType.Low;
		} else if (r < 0.75) {
			quality = SignalQualityType.Medium;
		} else {
			quality = SignalQualityType.Good;
		}
		return quality;
	}
	
	private static SignalQualityType signal = null;
	static public void debug_UseFixedSignal(SignalQualityType signal) {
		Signal.signal = signal;
	}
	
	static public void debug_UseRandomSignal() {
		Signal.signal = null;
	}
	
	static public double SignalQualityToFactor(SignalQualityType signalQuality) {
		switch (signalQuality) {
		case Good:
			return 0.5;
		case Low:
			return 0.1;
		case Medium:
			return 0.25;
		case NA:
			return 0;			
		default:
			return 0;			
		}		
	}
}
