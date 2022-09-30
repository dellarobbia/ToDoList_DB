package UserLists;

/**
 * Interface to handle user-made lists.
 * @author Andrew McKay
 */

public interface UserLists {
    /**
     * Add a new UserListItem to the UserList
     * @param position integer representing the position in the UserList the new item will exist in
     * @param newUserListItem New item being added to the UserList
     */
    public void addUserListItem(int position, UserListItem newUserListItem);

    /**
     * Remove an existing UserListItem from the UserList
     * @param itemPosition Integer representing the position the item needing to be removed exists in
     */
    public void removeUserListItem(int itemPosition);

    /**
     * Print the UserList to the screen
     */
    public void displayUserList();
}
