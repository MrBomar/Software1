package wgu.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.util.Comparator;

/**
 * This class creates and manages the static inventory state.
 *
 * @author Leslie Calvin Bomar 3rd
 * @version 1.0
 * @since 2020-01-20
 */
public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Appends a Part to the allParts ObservableList.
     * @param newPart Takes an instance of Part and adds it to the parts inventory.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Appends a Product to the allProducts ObservableList.
     * @param newProduct Takes an instance of Product and adds it to the products inventory.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Will return a Part with the matching ID number.
     * Only use this method with a validated Part ID, a value not in inventory will result in an error.
     * @param partId Takes an integer value representing a part ID.
     * @return Returns an instance of Part that the provided partId references.
     */
    public static Part lookupPart(int partId) {
        int index = -1;
        for (int i = 0; i < allParts.size(); i++) {
            if(partId == allParts.get(i).getId()) {
                index = i;
            }
        }
        return allParts.get(index);
    }

    /**
     * This method will return a Product with the matching ID number.
     * Only use this method with a validated Product ID, using a value not in inventory will result in an error.
     * @param productId Takes an integer value representing a product ID.
     * @return Return an instance of Product that the provided productId references.
     */
    public static Product lookupProduct(int productId) {
        int index = -1;
        for(int i = 0; i < allProducts.size(); i++) {
            if(productId == allProducts.get(i).getId()) {
                index = i;
            }
        }
        return allProducts.get(index);
    }

    /**
     * This method will return a list of all Parts whose name matches the partName.
     * @param partName Any string value.
     * @return A filtered ObservableList containing Parts whose name contain the provided partName.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        return new FilteredList<>(allParts, p -> p.getName().toLowerCase().contains(partName.toLowerCase()));
    }

    /**
     * This method will return a list of all Products whose name matches the productName.
     * @param productName Any string value.
     * @return A filtered ObservableList containing Products whose name contains the provided productName.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        return new FilteredList<>(allProducts, p -> p.getName().toLowerCase().contains(productName.toLowerCase()));
    }

    /**
     * This method replaces the original Part with the updated Part.
     * @param oldIndex The index number of the Part in inventory to be updated.
     * @param selectedPart A new instance of Part containing the newly updated information.
     */
    public static void updatePart(int oldIndex, Part selectedPart) {
        //Add new part to the inventory
        addPart(selectedPart);
        int newIndex = allParts.indexOf(selectedPart);

        //Iterate through products and change out the part
        for(Product product: allProducts) {
            if(product.deleteAssociatedPart(allParts.get(oldIndex))){
                product.addAssociatedPart(allParts.get(newIndex));
            }
        }

        //Remove old part from inventory
        deletePart(allParts.remove(oldIndex));
    }

    /**
     * This method replaces the original Product with the updated Product.
     * @param index The index number of the Product in inventory to be updated.
     * @param selectedProduct A new instance of Product containing the newly updated information.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        //Delete the old entry and replace it with the selectedProduct
        allProducts.remove(index);
        addProduct(selectedProduct);
    }

    /**
     * This method removes the selectedPart form the allParts ObservableList.
     * @param selectedPart The original instance of the Part to be removed.
     * @return Returns 'true' if the Part was successfully removed from the Parts inventory.
     */
    public static boolean deletePart(Part selectedPart) {
        //Loop through all the products
        for(Product product: allProducts) {
            for(Part part: product.getAllAssociatedParts()) {
                if(part.equals(selectedPart)) {
                    return false;
                }
            }
        }

        return getAllParts().remove(selectedPart);
    }

    /**
     * This method removes the selectedProduct from the allProducts ObservableList.
     * @param selectedProduct The original instance of the Product to be removed.
     * @return Returns 'true' if the Product was successfully removed from the Products inventory.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return getAllProducts().remove(selectedProduct);
    }

    /**
     * Returns a reference to the allParts ObservableList.
     * @return A sorted ObservableList of all Parts in inventory.
     */
    public static ObservableList<Part> getAllParts() {
        Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
        FXCollections.sort(allParts, comparator);
        return allParts;
    }

    /**
     * Return a reference to the allProducts ObservableList.
     * @return A sorted ObservableList of all Parts in inventory.
     */
    public static ObservableList<Product> getAllProducts() {
        Comparator<Product> comparator = Comparator.comparingInt(Product::getId);
        FXCollections.sort(allProducts, comparator);
        return allProducts;
    }
}
