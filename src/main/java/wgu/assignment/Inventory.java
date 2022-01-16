package wgu.assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

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
        int index = -1;
        for (int i = 0; i < allParts.size(); i++) {
            if(partId == allParts.get(i).getId()) {
                index = i;
            };
        }
        return allParts.get(index);
    }

    public static Product lookupProduct(int productId) {
        int index = -1;
        for(int i = 0; i < allProducts.size(); i++) {
            if(productId == allProducts.get(i).getId()) {
                index = i;
            }
        }
        return allProducts.get(index);
    }

    public static ObservableList<Part> lookupPart(String partName) {
        //FIXME - Add logic to return a list result of a string search using partName and allParts
        return allParts;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        //FIXME - Add logic to return a list result of a sting search using productName and allProducts
        return allProducts;
    }

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

    public static void updateProduct(int index, Product selectedProduct) {
        //Select the existing product at index provided
        Product existingProduct = getAllProducts().get(index);

        //Update values on existing product to the selectedProduct
        existingProduct.setMax(selectedProduct.getMax());
        existingProduct.setMin(selectedProduct.getMin());
        existingProduct.setName(selectedProduct.getName());
        existingProduct.setPrice(selectedProduct.getPrice());
        existingProduct.setStock(selectedProduct.getStock());
    }

    public static boolean deletePart(Part selectedPart) {
        //We need to check the "Product Inventory" first to ensure this part is not associated with any products.

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

    public static boolean deleteProduct(Product selectedProduct) {
        return getAllProducts().remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
        FXCollections.sort(allParts, comparator);
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        Comparator<Product> comparator = Comparator.comparingInt(Product::getId);
        FXCollections.sort(allProducts, comparator);
        return allProducts;
    }
}
