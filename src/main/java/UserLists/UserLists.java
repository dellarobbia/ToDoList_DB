package UserLists;

/**
 * Interface to handle user-made lists.
 * @author Andrew McKay
 */

public interface UserLists {
    /**
     * Add a new UserListItem to the UserList
     * @param newUserListItem New item being added to the UserList
     */
    void addUserListItem(ToDoListItem newUserListItem);

    /**
     * Remove an existing UserListItem from the UserList
     * @param itemPosition Integer representing the position the item needing to be removed exists in
     */
    void removeUserListItem(int itemPosition);

    /**
     * Print the UserList to the screen
     */
    void displayUserList();
}
