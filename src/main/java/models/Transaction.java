package models;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private PackageSize packageSize;
    private DeliveryMethod deliveryMethod;

    // if I'm dealing with money, I want to use non-approximated data types
    private Integer shipmentCostCents; // in cents
    private Integer discountCents; // in cents
    private String badInputText; // if we get bad input we need to store it for printing

    // used when reading the input file
    public Transaction(LocalDate date, PackageSize packageSize, DeliveryMethod deliveryMethod) {
        this.date = date;
        this.packageSize = packageSize;
        this.deliveryMethod = deliveryMethod;
        this.shipmentCostCents = createShipmentCostCents();
        this.discountCents = 0;
        this.badInputText = null;
    }

    public Transaction(String badInputText) { // if we get bad input
        this.badInputText = badInputText;
    }

    public Transaction(LocalDate date, PackageSize packageSize, DeliveryMethod deliveryMethod,
                       Integer shipmentCostCents, Integer discountCents) {
        this.date = date;
        this.packageSize = packageSize;
        this.deliveryMethod = deliveryMethod;
        this.shipmentCostCents = shipmentCostCents;
        this.discountCents = discountCents;
        this.badInputText = null;
    }

    @Override
    public String toString() {
        if (!(badInputText == null)){
            return String.format("%s Ignored", getBadInputText());
        } else if (shipmentCostCents == null && discountCents.equals(0)){
            return String.format("%tY-%tm-%td %s %s", getDate(), getDate(), getDate(), getPackageSize(), getDeliveryMethod());
        }else if (discountCents.equals(0)){
            return String.format("%tY-%tm-%td %s %s %s -", getDate(), getDate(), getDate(), getPackageSize(), getDeliveryMethod(), getShipmentCostString());
        } else{
            return String.format("%tY-%tm-%td %s %s %s %s", getDate(), getDate(), getDate(),
                    getPackageSize(), getDeliveryMethod(), getShipmentCostString(), getDiscountString());
        }

    }

    // methods for converting money in cents to a string of euros
    private String getShipmentCostString() {
        Integer euros = getShipmentCostCents() / 100; // could be pounds or dollars, doesn't matter
        Integer cents = getShipmentCostCents() % 100;
        return String.format("%d.%02d", euros, cents);
    }
    private String getDiscountString() {
        Integer euros = getDiscountCents() / 100;
        Integer cents = getDiscountCents() % 100;
        return String.format("%d.%02d", euros, cents);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PackageSize getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(PackageSize packageSize) {
        this.packageSize = packageSize;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Integer getShipmentCostCents() {
        return shipmentCostCents;
    }

    public void setShipmentCostCents(Integer shipmentCostCents) {
        this.shipmentCostCents = shipmentCostCents;
    }
    public int createShipmentCostCents() { // creates the shipment cost with the existing discount amount
        this.shipmentCostCents = getDeliveryMethod().getPriceCents(getPackageSize()) - getDiscountCents();
        return this.shipmentCostCents;
    }
    public int createShipmentCostCents(Integer discountAmountCents) { // creates the shipment cost with a discount amount
        int shipmentPrice = getDeliveryMethod().getPriceCents(getPackageSize());
        this.shipmentCostCents = shipmentPrice - discountAmountCents;
        return this.shipmentCostCents;
    }

    public Integer getDiscountCents() {
        return discountCents;
    }

    public void setDiscountCents(Integer discountCents) { this.discountCents = discountCents; }
    public void increaseDiscountCents(Integer discountCents) { // also changes the price accordingly
        this.discountCents = discountCents + getDiscountCents();
        createShipmentCostCents();
    }

    public String getBadInputText() {
        return badInputText;
    }

    public void setBadInputText(String badInputText) { this.badInputText = badInputText; }
}
