package SubscriptionType;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
		//TODO subtract from freeMinutes, or add to usedExtraMinutes
		throw new NotImplementedException();
	}
	
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
