package wgu.assignment;

public enum ModalMode {
    CONFIRM_DELETE_PART("Confirm Part Deletion", "Are you sure you want to delete this part from the inventory?"),
    CONFIRM_DELETE_PRODUCT("Confirm Product Deletion", "Are you sure you want to delete this product from the inventory?"),
    CONFIRM_EXIT("Confirm Exit","Are you sure you want to exit the application?"),
    DELETION_ERROR_UNKNOWN("Deletion Error", "Unknown error, item could not be deleted."),
    DELETION_ERROR_ASSOCIATED_PARTS("Deletion Error", "Unable to delete part. Check to ensure the part is not associated with any products."),
    EMPTY_LIST_PRODUCTS("Empty List", "There are no products. Consider adding a product."),
    EMPTY_LIST_PARTS("Empty List", "There are no parts. Consider adding a part."),
    INVALID_MACHINE_ID("Invalid Entry", "The value entered in the 'Machine ID' field is invalid. Please enter an non-decimal numeric value."),
    INVALID_MAX("Invalid Entry","The value entered in the 'Max' field is invalid. Please enter a non-decimal numeric value."),
    INVALID_MIN("Invalid Entry","The value entered in the 'Min' field is invalid. Please enter a non-decimal numeric value."),
    INVALID_MIN_MAX("Invalid Entry","The value in 'Max' cannot be less than the value in 'Min'."),
    INVALID_PRICE("Invalid Entry","The value entered in the 'Price' field is invalid. Please enter a valid dollar amount."),
    INVALID_STOCK("Invalid Entry", "The value entered in the 'Inv' field is invalid. Please enter an non-decimal numeric value."),
    NEGATIVE_STOCK("Invalid Entry","The value entered in the 'Inv' field is invalid. Number cannot be negative."),
    NEGATIVE_PRICE("Invalid Entry","The value entered in the 'Price' field is invalid. Number cannot be negative."),
    NEGATIVE_MAX("Invalid Entry","The value entered in the 'Max' field is invalid. Number cannot be negative."),
    NEGATIVE_MIN("Invalid Entry","The value entered in the 'Min' field is invalid. Number cannot be negative."),
    NO_SELECTION_PRODUCT("Missing Selection", "Please select a product."),
    NO_SELECTION_PART("Missing Selection", "Please select a part."),
    OUT_OF_RANGE_STOCK("Invalid Entry","The value is 'Inv' cannot be less than 'Min' or less than 'Max'.");



    private final String title;
    private final String message;

    ModalMode(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
