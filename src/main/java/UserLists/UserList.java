package UserLists;

import java.util.ArrayList;

/**
 * Class that creates an ordered list of user-input values.
 * @author Andrew McKay
 */
public class UserList implements UserLists{
    //Properties
    private int userListID;
    private ArrayList<UserListItem> userListItems;

    //Getters & Setters
    public int getUserListID(){return userListID;}
    public void setUserListID(int userListID){this.userListID = userListID;}
    public ArrayList<UserListItem> getUserListItems() {return userListItems;}
    public void setUserListItems(ArrayList<UserListItem> userListItems) {this.userListItems = userListItems;}
    public void setUserListItems() {this.userListItems = new ArrayList<UserListItem>();}

    //Constructors

    /**
     * Construct a UserList with a new ArrayList
     */
    public UserList() {setUserListItems();}

    /**
     * Construct a UserList using an existing ArrayList
     * @param userListItems ArrayList of user-created items
     */
    public UserList(ArrayList<UserListItem> userListItems) {setUserListItems(userListItems);}

    @Override
    public void addUserListItem(UserListItem newUserListItem) {
        userListItems.add(newUserListItem);
    }

    @Override
    public void removeUserListItem(int itemPosition) {
        userListItems.remove(itemPosition);
    }

    @Override
    public void displayUserList() {
        int displayNumber = 0;
        for(int i = 0; i < userListItems.size(); i++){
            displayNumber = i + 1;
            System.out.println(displayNumber + ": " + userListItems.get(i).toString());
        }
    }
}
