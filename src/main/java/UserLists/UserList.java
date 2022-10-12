package UserLists;

import java.util.ArrayList;

/**
 * Class that creates an ordered list of user-input values.
 * @author Andrew McKay
 */
public class UserList implements UserLists{
    //Properties
    private int userListID;
    private ArrayList<ToDoListItem> userListItems;

    //Getters & Setters
    public int getUserListID(){return userListID;}
    public void setUserListID(int userListID){this.userListID = userListID;}
    public ArrayList<ToDoListItem> getUserListItems() {return userListItems;}
    public void setUserListItems(ArrayList<ToDoListItem> userListItems) {this.userListItems = userListItems;}
    public void setUserListItems() {this.userListItems = new ArrayList<>();}

    //Constructors

    /**
     * Construct a UserList with a new ArrayList
     */
    public UserList() {setUserListItems();}

    /**
     * Construct a UserList using an existing ArrayList
     * @param userListItems ArrayList of user-created items
     */
    public UserList(int userListID, ArrayList<ToDoListItem> userListItems) {
        setUserListID(userListID);
        setUserListItems(userListItems);
    }

    @Override
    public void addUserListItem(ToDoListItem newUserListItem) {
        userListItems.add(newUserListItem);
    }

    @Override
    public void removeUserListItem(int itemPosition) {
        userListItems.remove(itemPosition);
    }

    @Override
    public void displayUserList() {
        int displayNumber;
        for(int i = 0; i < userListItems.size(); i++){
            displayNumber = i + 1;
            System.out.println(displayNumber + ": " + userListItems.get(i).toString());
        }
    }
}
