package InformationProvider.Terminal;

import java.util.ArrayList;

import InformationProvider.RAN.RANTechnology;

public class TerminalInformation {

	static public ArrayList<RANTechnology> getSupportedRANTechnology(TerminalType terminal){
		ArrayList<RANTechnology> ranList = new ArrayList<>();
		switch (terminal) {
		case PearaPhone4s:
			ranList.add(RANTechnology.GSM);
			ranList.add(RANTechnology.HSPA);
			break;
		case PhairPhone:
			ranList.add(RANTechnology.GSM);
			ranList.add(RANTechnology.HSPA);
			break;
		case SoniZperiaX3:
			ranList.add(RANTechnology.GSM);
			ranList.add(RANTechnology.HSPA);
			ranList.add(RANTechnology.LTE);
			break;
		default:			
			break;
		}
		return ranList;
	}
}
