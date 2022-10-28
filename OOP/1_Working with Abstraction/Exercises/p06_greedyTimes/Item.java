package greedyTimes;

public class Item {
    private ItemTypes itemTypes;
    private String name;
    private long quantity;

    public Item(ItemTypes itemTypes, String name, long quantity) {
        this.itemTypes = itemTypes;
        this.name = name;
        this.quantity = quantity;
    }


    public static Item createItem(String itemName, long quantity) {

        ItemTypes itemType = null;

        if (itemName.length() == 3) {
            itemType = ItemTypes.Cash;
        } else if (itemName.toLowerCase().endsWith("gem")) {
            itemType = ItemTypes.Gem;
        } else if (itemName.toLowerCase().equals("gold")) {
            itemType = ItemTypes.Gold;
        }

        if (itemType != null) {
            return new Item(itemType, itemName, quantity);
        }

        return null;
    }

    protected void increaseQuantity(long newQuantity){
        this.quantity+=newQuantity;
    }

    public ItemTypes getItemTypes() {
        return itemTypes;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }
}


