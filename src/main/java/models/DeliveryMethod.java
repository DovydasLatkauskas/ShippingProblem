package models;

/**
 * Represents a delivery method with different prices for S, M, and L packages.
 */
public enum DeliveryMethod {
    LP(1.0, 2.0, 3.0), // prices for S, M, L packages
    MR(1.5, 3.0, 4.5); // prices for S, M, L packages

    private final double priceS;
    private final double priceM;
    private final double priceL;

    private DeliveryMethod(double priceS, double priceM, double priceL) {
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
    }
    /**
     * Returns the price of the package based on the given size.
     *
     * @param size the package size
     * @return the price of the package for this delivery method and package size
     * @throws IllegalArgumentException if an invalid package size is provided
     */
    public double getPrice(PackageSize size) {
        switch(size) {
            case S:
                return priceS;
            case M:
                return priceM;
            case L:
                return priceL;
            default:
                throw new IllegalArgumentException("Invalid package size: " + size);
        }
    }
}

