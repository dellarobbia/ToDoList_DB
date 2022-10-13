import Prompts.*;
import Queries.UserListQueries;
import Queries.UserQueries;
import UserLists.*;
import Users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This version of the ToDoList program uses a MySQL database to store ToDoList items
 * @author Andrew McKay
 * For CEN-4025C
 */
public class Main {
    static Connection mySQLConnection;

    static {
        try {
            mySQLConnection = connectToDB();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static User user;

    static {
        try {
            user = selectUser();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static UserList userList;

    static {
        try {
            userList = loadUserList();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        InputPrompt mainMenu = new InputPrompt(user.toString() + "\n" +
                """
                        Please select an option:\s
                        \t1: View To-Do List
                        \t2: Add Item to To-Do List
                        \t3: Mark Item as Complete
                        \t4: Remove Item from To-Do List
                        \t5: Exit Program
                        """);

        int selection;
        boolean finished = false;

        do{
            mainMenu.displayPrompt();
            selection = Integer.parseInt(mainMenu.getUserInput());
            switch (selection) {
                case 1 -> userList.displayUserList();
                case 2 -> addUserItemMenu();
                case 3 -> userItemCompleteMenu();
                case 4 -> removeItemMenu();
                case 5 -> finished = true;
                default -> System.out.println("Invalid selection.");
            }
        }while(!finished);
    }

    private static Connection connectToDB() throws SQLException, ClassNotFoundException {
        Connection newConnection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        newConnection = DriverManager.getConnection("jdbc:mysql://localhost/todolist_db", "ToDoListApp", "Java123");
        return newConnection;
    }

    private static User selectUser() throws SQLException, ClassNotFoundException {
        InputPrompt existingUserPrompt = new InputPrompt("""
                Are you an existing user?
                \t1. Yes
                \t2. No
                """);
        existingUserPrompt.displayPrompt();
        int existingUserSelection = Integer.parseInt(existingUserPrompt.getUserInput());
        if (existingUserSelection == 1) {
            InputPrompt selectUserPrompt = new InputPrompt("Enter your user ID#:");
            selectUserPrompt.displayPrompt();
            int userID = Integer.parseInt(selectUserPrompt.getUserInput());
            return UserQueries.query_User(connectToDB(), userID);
        } else {
            InputPrompt newUserPrompt = new InputPrompt("Enter a username:");
            newUserPrompt.displayPrompt();
            String username = newUserPrompt.getUserInput();
            UserQueries.query_insertNewUser(connectToDB(), username);
            User newUser = UserQueries.query_User(connectToDB(), username);
            UserListQueries.query_insertNewList(connectToDB(), newUser.getUserID());
            return UserQueries.query_User(connectToDB(), username);
        }
    }

    private static UserList loadUserList() throws SQLException, ClassNotFoundException {
        return UserListQueries.query_getList(connectToDB(), user.getUserID());
    }

    private static void addUserItemMenu(){
        //Prompts
        InputPrompt addItemDescriptionPrompt = new InputPrompt(
                "What do you need to do?");
        InputPrompt addDueDatePrompt = new InputPrompt(
                "When does it need to be done by?");

        //ToDoList constructor values
        String toDoItemDescription;
        String toDoItemDueDate;

        //Display the current to-do list so the user can see its current state
        userList.displayUserList();

        //Display prompts needed to get input for the new To-Do list item

        addItemDescriptionPrompt.displayPrompt();
        toDoItemDescription = addItemDescriptionPrompt.getUserInput();

        addDueDatePrompt.displayPrompt();
        toDoItemDueDate = addDueDatePrompt.getUserInput();

        //Construct the new ToDOListItem and add it to the to-do list
        ToDoListItem newToDoListItem = new ToDoListItem(toDoItemDescription, toDoItemDueDate);
        userList.addUserListItem(newToDoListItem);
    }

    private static void userItemCompleteMenu(){
        //Prompts
        InputPrompt userItemCompletePrompt = new InputPrompt("Which item was completed?");

        //Display userList so the user can see it's current state
        userList.displayUserList();

        //Display prompt
        userItemCompletePrompt.displayPrompt();
        int userItemComplete = Integer.parseInt(userItemCompletePrompt.getUserInput()) - 1;

        //Attempt to mark the item as complete
        if(userItemComplete < userList.getUserListItems().size() && userItemComplete >=0){
            userList.getUserListItems().get(userItemComplete).setCompleted(true);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void removeItemMenu(){
        //Prompts
        InputPrompt removeItemPositionPrompt = new InputPrompt(
                "Which item will be removed?");
        InputPrompt removeItemConfirmationPrompt = new InputPrompt(
                """
                        Please confirm the removal:
                        \t1: Yes
                        \t2: No
                        """);

        int confirmSelection;
        int positionSelection;
        boolean finished = false;

        userList.displayUserList();
        do{
            removeItemPositionPrompt.displayPrompt();
            positionSelection = Integer.parseInt(removeItemPositionPrompt.getUserInput()) - 1;

            removeItemConfirmationPrompt.displayPrompt();
            confirmSelection = Integer.parseInt(removeItemConfirmationPrompt.getUserInput());
            switch (confirmSelection) {
                case 1 -> {
                    userList.removeUserListItem(positionSelection);
                    finished = true;
                }
                case 2 -> {
                    System.out.println("Removal canceled. Returning to main menu...");
                    finished = true;
                }
                default -> System.out.println("Invalid selection");
            }
        } while(!finished);
    }
}
