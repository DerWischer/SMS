package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import InformationProvider.Service.ServiceType;
import InformationProvider.Terminal.TerminalType;
import Subscriber.Subscriber;
import Subscriber.SubscriberFactory;
import SubscriptionType.GreenMobileL;
import SubscriptionType.GreenMobileM;
import SubscriptionType.GreenMobileS;
import common.SubscriberManager;

public class runner {

	public static void main(String[] args) {
		SubscriberManager manager = new SubscriberManager();
		
		Subscriber s1 = SubscriberFactory.createSubsriber(
				"Hans", "Schmidt", 
				TerminalType.PhairPhone, new GreenMobileS()
				);
		
		Subscriber s2 = SubscriberFactory.createSubsriber(
				"Peter", "Schmidt", 
				TerminalType.PhairPhone, new GreenMobileM()
				);		
		
		manager.addSubscriber(s1);
		manager.addSubscriber(s2);
		
		manager.simulateSession(s1, ServiceType.VoiceCall, 61);
		manager.simulateSession(s1, ServiceType.Browsing,5);
		
		
		
		 try {
		 		JAXBContext context = JAXBContext.newInstance(SubscriberManager.class, GreenMobileS.class, GreenMobileM.class, GreenMobileL.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			int extra = manager.getSubscriber(0).getSubscriptionType().getUsedExtraMinutes();
			int vol = manager.getSubscriber(0).getSubscriptionType().getDataVolumeInMBits();
			System.out.println("Extra = " + extra + ", Vol = " + vol);
			
			File saveFile = new File("saveFile");
			System.out.println("Count = " + manager.getSubscriberCount());
			marshaller.marshal(manager, saveFile);
			
			manager = null;
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			manager = (SubscriberManager) unmarshaller.unmarshal(saveFile);
			
			extra = manager.getSubscriber(0).getSubscriptionType().getUsedExtraMinutes();
			vol = manager.getSubscriber(0).getSubscriptionType().getDataVolumeInMBits();
			System.out.println("Extra = " + extra + ", Vol = " + vol);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(GreenMobileS.class, GreenMobileM.class, GreenMobileL.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				SubscriptionType t = new GreenMobileL();
				File f = new File("saveFile");
				m.marshal(t, f);
				t = null;
				
				Unmarshaller um = context.createUnmarshaller();
				t = (SubscriptionType) um.unmarshal(f);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		
	}

}
