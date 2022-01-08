package wgu.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        //FIXME - Add logic for looking up a part in the allParts list using the part ID
        return new InHouse(1,"Test Part", 12.89, 5,2,10, 1);
    }

    public static Product lookupProduct(int productId) {
        //FIXME - Add logic for looking up a part in the allProducts list using the productID
        return new Product(1, "Test Product", 120.50, 10, 5, 20);
    }

    public static ObservableList<Part> lookupPart(String partName) {
        //FIXME - Add logic to return a list result of a string search using partName and allParts
        return allParts;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        //FIXME - Add logic to return a list result of a sting search using productName and allProducts
        return allProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        //FIXME - Add logic to remove the part at specified index and insert the selectedPart into the allParts
    }

    public static void updateProduct(int index, Product selectedProduct) {
        //FIXME - Add logic to remove the product at the specified index and insert selectedProduct into allProducts
    }

    public static boolean deletePart(Part selectedPart) {
        //FIXME - Add logic to locate the selected part and remove it from allParts
        return true;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        //FIXME - Add logic to locate the selected part and remove it from allProducts
        return true;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
