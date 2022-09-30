package UserLists;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class that creates an ordered list of user-input values.
 * @author Andrew McKay
 */
public class UserList implements UserLists{
    //Properties
    private ArrayList<UserListItem> userListItems;

    //Getters & Setters
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
    public void addUserListItem(int position, UserListItem newUserListItem) {

    }

    @Override
    public void removeUserListItem(int itemPosition) {

    }

    @Override
    public void displayUserList() {

    }
}
