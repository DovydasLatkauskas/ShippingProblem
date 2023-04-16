package models;
import static runner.Constants.TOTAL_DISCOUNT_MONTH_CENTS;

public class MonthlyRestriction { // add any future monthly restrictions here
    private Integer availableDiscountCents;
    private int largeLPShipmentsThisMonth;

    public MonthlyRestriction() { // all the discounts are available at creation
        this.availableDiscountCents = TOTAL_DISCOUNT_MONTH_CENTS;
        this.largeLPShipmentsThisMonth = 0;
    }

    public Integer getAvailableDiscountCents() {
        return availableDiscountCents;
    }

    public void setAvailableDiscountCents(Integer availableDiscountCents) {
        this.availableDiscountCents = availableDiscountCents;
    }

    public int getLargeLPShipmentsThisMonth() {
        return this.largeLPShipmentsThisMonth;
    }

    public void setLargeLPShipmentsThisMonth(int largeLPShipmentAvailable) {
        this.largeLPShipmentsThisMonth = largeLPShipmentAvailable;
    }
    public void incrementLargeLPShipmentsThisMonth() {
        this.largeLPShipmentsThisMonth = getLargeLPShipmentsThisMonth() + 1;
    }
}
