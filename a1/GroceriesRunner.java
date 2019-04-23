package cs445.a1;

public class GroceriesRunner {
    public static void main(String[] args) {

        // Create grocery bag
        GroceriesInterface groceries = new Groceries();

        // Add items to bag
        groceries.addItem(new GroceryItem("Cheese", 1));
        groceries.addItem(new GroceryItem("Bread", 1));
        groceries.addItem(new GroceryItem("Meat", 1));
        groceries.addItem(new GroceryItem("Soda", 1));

        // Print all groceries so far
        groceries.printAll();
        System.out.println();

        // Modify quantity of an item
        groceries.modifyQuantity(new GroceryItem("Soda", 5));

        // Add a duplicate item
        groceries.addItem(new GroceryItem("Cheese", 1));

        // Remove an item
        groceries.removeItem(new GroceryItem("Bread", 1));

        // Print final grocery bag
        groceries.printAll();
    }
}
