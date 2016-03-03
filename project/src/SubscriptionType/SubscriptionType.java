package SubscriptionType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import exception.NoDataVolumeException;

@XmlTransient
public abstract class SubscriptionType {
	
	protected double basicFee;		
	protected int freeMinutes;	
	protected double priceForExtraMinutes;	
	protected int dataVolumeInMBits;	
	private int usedExtraMinutes = 0;
	
	public SubscriptionType(){
		resetAttributes();
	}
	
	@XmlAttribute
	public int getFreeMinutes() {
		return freeMinutes;		
	}
	
	public void setFreeMinutes(int min){
		this.freeMinutes = min;
	}
	
	@XmlAttribute
	public int getDataVolumeInMBits() {
		return dataVolumeInMBits;
	}
	
	@SuppressWarnings("unused")
	private void setDataVolumeInMBits(int vol){
		this.dataVolumeInMBits = vol;
	}
	
	@XmlAttribute
	public int getUsedExtraMinutes() {
		return usedExtraMinutes;
	}
	
	@SuppressWarnings("unused")
	private void setUsedExtraMinutes(int min){
		this.usedExtraMinutes = min;
	}
	
	public double getBasicFee() {
		return basicFee;
	}
	
	public void consumeMinutes(int minutes){
		freeMinutes -= minutes;
		
		if (freeMinutes < 0){
			usedExtraMinutes += Math.abs(freeMinutes);
			freeMinutes = 0;
		}		
	}
	
	public void consumeDataVolume(int mbits) throws NoDataVolumeException{
		dataVolumeInMBits -= mbits;
		
		if (dataVolumeInMBits < 0) {
			dataVolumeInMBits = 0;
			throw new NoDataVolumeException();
		}
	}
	
	/**
	 * Returns the charges and resets all attributes to default
	 */
	public double invoice(){
		double charge = basicFee + usedExtraMinutes * priceForExtraMinutes;
		resetAttributes();
		usedExtraMinutes = 0;
		return charge;
	}
	
	protected abstract void resetAttributes();
	
	@Override
	public String toString() {		
		return getClass().getName();
	}
}
