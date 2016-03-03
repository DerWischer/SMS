package SubscriptionType;

import exception.NoDataVolumeException;

public abstract class SubscriptionType {
	protected double basicFee;
	protected int freeMinutes;
	protected double priceForExtraMinutes;
	protected int dataVolumeInMBits;
	private int usedExtraMinutes = 0;
	
	public SubscriptionType(){
		resetAttributes();
	}
	
	public int getFreeMinutes() {
		return freeMinutes;		
	}
	
	public int getDataVolumeInMBits() {
		return dataVolumeInMBits;
	}
	
	public int getUsedExtraMinutes() {
		return usedExtraMinutes;
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
		return getClass().getSimpleName();
	}
}
