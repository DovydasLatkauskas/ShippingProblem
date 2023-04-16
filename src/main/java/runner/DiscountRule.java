package runner;

import models.DeliveryMethod;
import models.MonthlyRestriction;
import models.PackageSize;
import models.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// interface for creating each discount rule
public abstract class DiscountRule {
    public abstract int applyRule(Transaction transaction, MonthlyRestriction thisMonth); // we return the discount amount in cents
    public static List<DiscountRule> getDiscountRuleList(){
        List<DiscountRule> discountList = new ArrayList<>();
        discountList.add(new MatchSmallPackagePrice());
        return discountList;
    }
}

// you have to add each discount rule to getDiscountRuleList()
class MatchSmallPackagePrice extends DiscountRule {
    public int applyRule(Transaction transaction, MonthlyRestriction thisMonth) {
        if(!transaction.getPackageSize().equals(PackageSize.S)){return 0;} // if the package is not small, return 0

        List<Integer> shipmentPrices = new ArrayList<>();
        for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
            shipmentPrices.add(deliveryMethod.getPriceCents(transaction.getPackageSize()));
        }
        // returning the discount (shipment cost - minimum shipment cost for an S package)
        return transaction.getShipmentCostCents() - Collections.min(shipmentPrices);
    }
}

class ThirdLargeShipmentLP extends DiscountRule {
    public int applyRule(Transaction transaction, MonthlyRestriction thisMonth) {
        // if the package is not Large or not from LP, return 0
        if(!transaction.getPackageSize().equals(PackageSize.L) || !transaction.getDeliveryMethod().equals(DeliveryMethod.LP)){return 0;}

        thisMonth.incrementLargeLPShipmentsThisMonth(); // we count this shipment
        if(thisMonth.getLargeLPShipmentsThisMonth() == 3){
            return transaction.getShipmentCostCents();
        } else {
            return 0;
        }
    }
}

