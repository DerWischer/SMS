package SubscriptionType;

public class GreenMobileL extends SubscriptionType {

	@Override
	protected void resetAttributes() {
		basicFee = 42;
		freeMinutes = 150;
		priceForExtraMinutes = 0.04;
		dataVolumeInMBits = 40000;
	}

}
