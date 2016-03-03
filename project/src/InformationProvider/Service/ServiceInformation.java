package InformationProvider.Service;

import java.util.ArrayList;

import InformationProvider.RAN.RANTechnology;

public class ServiceInformation {
	
	static public ArrayList<RANTechnology> getRANTechnologies(ServiceType service){
		// TODO determine RANTechnologies that are supported by the specified service
		ArrayList<RANTechnology> ranList = new ArrayList<>();
		switch(service) {
		case AppDownload:
			ranList.add(RANTechnology.HSPA);
			ranList.add(RANTechnology.LTE);
			break;
		case Browsing:
			ranList.add(RANTechnology.HSPA);
			ranList.add(RANTechnology.LTE);
			break;
		case HDVideo:
			ranList.add(RANTechnology.HSPA);
			ranList.add(RANTechnology.LTE);
			break;
		case VoiceCall:
			ranList.add(RANTechnology.GSM);
			break;
		default:
			break;		
		}
		return ranList;		
	}
	
	static public int getDataRateInMBits(ServiceType service) {
		// TODO determine DataRate of service 
		int mbits = 0;
		switch (service) {
		case AppDownload:
			mbits = 10;
			break;
		case Browsing:
			mbits = 2;
			break;
		case HDVideo:
			mbits = 100;
			break;
		case VoiceCall:
			mbits = 0;
			break;
		default:
			break;		
		}
		return mbits;		
	}
}
