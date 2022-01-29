package wgu.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;

/**
 * This class creates and manages instances of the properties of the Product object.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class Product {
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Creates an instance of Product.
     *
     * @param id The unique ID number of the product.
     * @param name The name/description of the product.
     * @param price The price of the product.
     * @param stock The quantity of the product on-hand.
     * @param min The minimum quantity of the product required in stock.
     * @param max The maximum quantity of the product allowed in stock.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /**
     * Sets the unique ID number of the product.
     * @param id The unique ID number of the product.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Sets the name/description of the product.
     * @param name The name/description of the product.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the price of the product.
     * @param price The price of the product.
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Sets the quantity of the product on-hand.
     * @param stock The quantity of the product on-hand.
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * Sets the minimum quantity of the product required in stock.
     * @param min The minimum quantity of the product required in stock.
     */
    public void setMin(int min) { this.min = min; }

    /**
     * Sets the maximum quantity of the product allowed in stock.
     * @param max The maximum quantity of the product allowed in stock.
     */
    public void setMax(int max) { this.max = max; }

    /**
     * Returns the unique ID number of the product.
     * @return The unique ID number of the product.
     */
    public int getId() { return this.id; }

    /**
     * Returns the name/description of the product.
     * @return The name/description of the product.
     */
    public String getName() { return this.name; }

    /**
     * Returns the price of the product.
     * @return The price of the product.
     */
    public double getPrice() { return this.price; }

    /**
     *  Returns the quantity of the product on-hand.
     * @return The quantity of the product on-hand.
     */
    public int getStock() { return this.stock; }

    /**
     * Returns the minimum quantity of the product required in stock.
     * @return The minimum quantity of the product required in stock.
     */
    public int getMin() { return this.min; }

    /**
     * Returns the maximum quantity of the product allowed in stock.
     * @return The maximum quantity of the product allowed in stock.
     */
    public int getMax() { return this.max; }

    /**
     * Add a Part to the list of associated parts required to product the Product.
     * @param part An instance of a part required to product the product.
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * Removes a Part from the list of associated parts.
     * @param selectedAssociatedPart An instance of the Part to be removed.
     * @return false if the associated part could not be removed, true if the associated part was removed successfully.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return this.associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Returns a reference to the list of associated parts.
     * @return A reference to the list of associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
        FXCollections.sort(this.associatedParts, comparator);
        return this.associatedParts;
    }
}
