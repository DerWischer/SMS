package SubscriptionType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GreenMobileS extends SubscriptionType {

	@Override
	protected void resetAttributes() {
		basicFee = 8;
		freeMinutes = 0;
		priceForExtraMinutes = 0.08;
		dataVolumeInMBits = 4000;
	}

}
