package wgu.assignment;

/**
 * Enum list for switching the view modes on the PartView and ProductView forms.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public enum ViewMode {
    /** The ADD_PART view mode. */
    ADD_PART("Add Part", "Price/Cost"),
    /** The MODIFY_PART view mode. */
    MODIFY_PART("Modify Part", "Price/Cost"),
    /** The ADD_PRODUCT view mode. */
    ADD_PRODUCT("Add Product", "Price"),
    /** The MODIFY_PRODUCT view mode. */
    MODIFY_PRODUCT("Modify Product", "Price");

    /** Specifies the label to apply above the TableView. */
    public final String label;
    /** Specifies the text of the labels for the price field. */
    public final String priceLabel;

    ViewMode(String label, String priceLabel) {
        this.label = label;
        this.priceLabel = priceLabel;
    }
}
