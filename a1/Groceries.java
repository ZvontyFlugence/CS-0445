package cs445.a1;

public class Groceries implements GroceriesInterface {
    private Set<GroceryItem> bag;

    public Groceries() {
        bag = new Set<GroceryItem>();
    }

    public Groceries(int size) {
        bag = new Set<GroceryItem>(size);
    }

    public void addItem(GroceryItem item) {
        bag.add(item);
    }

    public void removeItem(GroceryItem item) {
        bag.remove(item);
    }

    public int modifyQuantity(GroceryItem item) {
        Object[] items = bag.toArray();
        for (int i = 0; i < bag.getSize(); i++) {
            if (items[i].equals(item)) {
                bag.remove((GroceryItem)items[i]);
                bag.add(item);
                return ((GroceryItem)items[i]).getQuantity();
            }
        }
        return -1;
    }

    public void printAll() {
        Object[] items = bag.toArray();
        System.out.println("Groceries:");
        for(int i=0; i<bag.getSize(); i++) {
            System.out.println(items[i]);
        }
    }
}
