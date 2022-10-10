package Users;

import java.util.ArrayList;
import UserLists.UserLists;

/**
 * Represents a user capable of creating their own list
 */
public class User {
    //Properties
    private int userID;
    private ArrayList<UserLists> userLists;

    //Getters & Setters
    public int getUserID(){
        return userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }

    public ArrayList<UserLists> getUserLists(){
        return userLists;
    }
    public void setUserLists(ArrayList<UserLists> userLists){
        this.userLists = userLists;
    }
    public void setUserLists(){
        userLists = new ArrayList<>();
    }

    //Constructors
    public User(int userID, ArrayList<UserLists> userLists){
        setUserID(userID);
        setUserLists(userLists);
    }

    //Methods
    public void addUserList(UserLists userList){
        userLists.add(userList);
    }
}
