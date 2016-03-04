package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import SubscriptionType.GreenMobileL;
import SubscriptionType.GreenMobileM;
import SubscriptionType.GreenMobileS;

public class JAXBHandler {

	private JAXBContext context;
	private Marshaller m;
	private Unmarshaller um;

	private final String pathToSaveFile;
	private static String SAVE_FILE_NAME = "saveFile.xml";
	private File saveFile;

	public JAXBHandler() {
		pathToSaveFile = System.getenv("APPDATA") + "\\SMS\\" + SAVE_FILE_NAME;
		initSaveFile();		
		try {
			context = JAXBContext.newInstance(SubscriberManager.class, GreenMobileS.class, GreenMobileM.class,
					GreenMobileL.class);

			m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			um = context.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static String getSaveFilePath() {
		return System.getenv("APPDATA") + "\\SMS\\" + SAVE_FILE_NAME;
	}

	private void initSaveFile() {
		saveFile = new File(pathToSaveFile);
		
		if (!saveFile.exists())
			try {				
				saveFile.createNewFile();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void marshall(SubscriberManager manager) {
		try {			
			m.marshal(manager, saveFile);
			encrypt();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public SubscriberManager unmarshall() {
		SubscriberManager manager = new SubscriberManager();
		if (saveFile.exists()) {
			try {
				decrypt();
				manager = (SubscriberManager) um.unmarshal(saveFile);				
			} catch (JAXBException e) {
				return manager;
			}
		}
		return manager;
	}

	private void encrypt() {
		crypt(Cipher.ENCRYPT_MODE, "SubscriberManage");
	}

	private void decrypt() {
		crypt(Cipher.DECRYPT_MODE, "SubscriberManage");
	}

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";

	private void crypt(int cipherMode, String key) {
		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);

			FileInputStream inputStream = new FileInputStream(saveFile);
			byte[] inputBytes = new byte[(int) saveFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(saveFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
		}
	}

}
