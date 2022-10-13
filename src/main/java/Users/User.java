package Users;

/**
 * Represents a user capable of creating their own list
 */
public class User {
    //Properties
    private int userID;
    private String userName;

    //Getters & Setters
    public int getUserID(){
        return userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    //Constructors
    public User(int userID, String userName){
        setUserID(userID);
        setUserName(userName);
    }

    //Methods

    @Override
    public String toString(){
        return "User " + userName + " ID# " + userID;
    }
}
