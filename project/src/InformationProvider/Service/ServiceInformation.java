package InformationProvider.Service;

import java.util.ArrayList;

import InformationProvider.RAN.RANTechnology;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ServiceInformation {
	
	static public ArrayList<RANTechnology> getRANTechnologies(ServiceType service){
		// TODO determine RANTechnologies that are supported by the specified service
		throw new NotImplementedException();
	}
	
	static public int getDataRateInMBits(ServiceType service) {
		// TODO determine DataRate of service 
		throw new NotImplementedException();
	}
}
