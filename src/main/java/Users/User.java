package Users;

import UserLists.UserLists;

/**
 * Represents a user capable of creating their own list
 */
public class User {
    //Properties
    private int userID;
    private String userName;
    private int userListID;

    //Getters & Setters
    public int getUserID(){
        return userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public int getUserListID() {return userListID;}
    public void setUserListID(int userListID) {this.userListID = userListID;}

    //Constructors
    public User(int userID, String userName, int userListID){
        setUserID(userID);
        setUserName(userName);
        setUserListID(userListID);
    }
    public User(){

    }

    //Methods
}
