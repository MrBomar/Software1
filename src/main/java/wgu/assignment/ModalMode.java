package wgu.assignment;

/**
 *  This list contains the messages that can be displayed in the application modal.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2022-01-20
 */
public enum ModalMode {
    /** Used if a required text field is blank. */
    BLANK_FIELDS("Blank Entries", "All fields must have data."),
    /** Displays if search results are empty. */
    BLANK_SEARCH_RESULT("No Results", "No item was found using your search entry."),
    /** Used to confirm the user wants to delete the Part. */
    CONFIRM_DELETE_PART("Confirm Part Deletion", "Are you sure you want to delete this part from the inventory?"),
    /** Used to confirm the user want to delete the Product. */
    CONFIRM_DELETE_PRODUCT("Confirm Product Deletion", "Are you sure you want to delete this product from the inventory?"),
    /** Used to confirm the user wants to exit the application. */
    CONFIRM_EXIT("Confirm Exit","Are you sure you want to exit the application?"),
    /** Displays if an item couldn't be deleted. */
    DELETION_ERROR_UNKNOWN("Deletion Error", "Unknown error, item could not be deleted."),
    /** Displays if the selected Part is associated to any Products. */
    DELETION_ERROR_ASSOCIATED_PARTS("Deletion Error", "Unable to delete part. Check to ensure the part is not associated with any products."),
    /** Displays if user attempts to edit a part on an empty list. */
    EMPTY_LIST_PARTS("Empty List", "There are no parts. Consider adding a part."),
    /** Displays if user attempts to edit a product on an empty list. */
    EMPTY_LIST_PRODUCTS("Empty List", "There are no products. Consider adding a product."),
    /** Displays if the user entered an invalid Machine ID. */
    INVALID_MACHINE_ID("Invalid Entry", "The value entered in the 'Machine ID' field is invalid. Please enter an non-decimal numeric value."),
    /** Displays on an invalid maximum inventory entry. */
    INVALID_MAX("Invalid Entry","The value entered in the 'Max' field is invalid. Please enter a non-decimal numeric value."),
    /** Displays on an invalid maximum inventory entry. */
    INVALID_MIN("Invalid Entry","The value entered in the 'Min' field is invalid. Please enter a non-decimal numeric value."),
    /** Displays when the Max is less than Min. */
    INVALID_MIN_MAX("Invalid Entry","The value in 'Max' cannot be less than the value in 'Min'."),
    /** Displays when anything other than a double is entered for the price. */
    INVALID_PRICE("Invalid Entry","The value entered in the 'Price' field is invalid. Please enter a valid dollar amount."),
    /** Displays when anything other than a valid integer is entered in the inventory. */
    INVALID_STOCK("Invalid Entry", "The value entered in the 'Inv' field is invalid. Please enter an non-decimal numeric value."),
    /** Displays when a negative number has been entered in the inventory field. */
    NEGATIVE_STOCK("Invalid Entry","The value entered in the 'Inv' field is invalid. Number cannot be negative."),
    /** Displays when price entered is negative. */
    NEGATIVE_PRICE("Invalid Entry","The value entered in the 'Price' field is invalid. Number cannot be negative."),
    /** Displays when the Max inventory is negative. */
    NEGATIVE_MAX("Invalid Entry","The value entered in the 'Max' field is invalid. Number cannot be negative."),
    /** Displays when the Min inventory is negative. */
    NEGATIVE_MIN("Invalid Entry","The value entered in the 'Min' field is invalid. Number cannot be negative."),
    /** Displays when user attempts to modify an empty list. */
    NO_SELECTION_PRODUCT("Missing Selection", "Please select a product."),
    /** Displays when user attempts to modify an empty list. */
    NO_SELECTION_PART("Missing Selection", "Please select a part."),
    /** Displays when inventory is not between min and max. */
    OUT_OF_RANGE_STOCK("Invalid Entry","The value is 'Inv' cannot be less than 'Min' or grater than 'Max'.");

    private final String title;
    private final String message;

    ModalMode(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * Return the message to be displayed in Modal title.
     * @return The text to be displayed on the modal title bar.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return the message to be displayed in the body of the modal.
     * @return The message to be displayed in the body of the modal.
     */
    public String getMessage() {
        return message;
    }
}
