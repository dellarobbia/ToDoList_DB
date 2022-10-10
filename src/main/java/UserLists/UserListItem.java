package UserLists;

/**
 * Represents an item created on a user-made list
 * @author Andrew McKay
 */

public class UserListItem {
    //Properties
    private int itemID;
    private String itemDescription;

    //Getters & Setters
    public int getItemID(){
        return itemID;
    }
    public void setItemID(int itemID){
        this.itemID = itemID;
    }
    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    //Constructor
    public UserListItem (String itemDescription){
        setItemDescription(itemDescription);
    }
}
