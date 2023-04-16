package runner;

import models.DeliveryMethod;
import models.PackageSize;
import models.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// interface for creating each discount rule
public interface DiscountRule {
    int applyRule(Transaction transaction); // we return the discount amount in cents
}

// you have to add each discount rule to getDiscountRuleList() in ApplyDiscounts.java

class MatchSmallPackagePrice implements DiscountRule {
    public int applyRule(Transaction transaction) {
        if(!transaction.getPackageSize().equals(PackageSize.S)){return 0;} // if the package is not small, return 0

        List<Integer> shipmentPrices = new ArrayList<>();
        for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
            shipmentPrices.add(deliveryMethod.getPriceCents(transaction.getPackageSize()));
        }
        // returning the discount (shipment cost - minimum shipment cost for an S package)
        return transaction.createShipmentCostCents() - Collections.min(shipmentPrices);
    }
}

