package models;

/**
 * Represents a delivery method with different prices for S, M, and L packages.
 */
public enum DeliveryMethod {
    LP(150, 490, 690), // prices for S, M, L packages in cents
    MR(200, 300, 400); // prices for S, M, L packages in cents

    private final int priceS;
    private final int priceM;
    private final int priceL;

    private DeliveryMethod(int priceS, int priceM, int priceL) {
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
    public int getPriceCents(PackageSize size) {
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

