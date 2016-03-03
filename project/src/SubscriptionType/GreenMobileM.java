package SubscriptionType;

public class GreenMobileM extends SubscriptionType {

	@Override
	protected void resetAttributes() {
		basicFee = 22;
		freeMinutes = 100;
		priceForExtraMinutes = 0.06;
		dataVolumeInMBits = 16000;
	}

}
