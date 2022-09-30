package UserLists;

/**
 * Represents an item created on a user-made list
 * @author Andrew McKay
 */

public class UserListItem {
    //Properties
    private String itemDescription;

    //Getters & Setters
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
