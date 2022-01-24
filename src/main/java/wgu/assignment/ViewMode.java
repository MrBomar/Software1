package wgu.assignment;

public enum ViewMode {
    ADD_PART("Add Part", "Price/Cost"),
    MODIFY_PART("Modify Part", "Price/Cost"),
    ADD_PRODUCT("Add Product", "Price"),
    MODIFY_PRODUCT("Modify Product", "Price");

    public final String label;
    public final String priceLabel;

    private ViewMode(String label, String priceLabel) {
        this.label = label;
        this.priceLabel = priceLabel;
    }
}
