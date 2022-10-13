import Prompts.*;
import Queries.ListItemQueries;
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
                        \t4: Exit Program
                        """);

        int selection;
        boolean finished = false;

        do{
            mainMenu.displayPrompt();
            selection = Integer.parseInt(mainMenu.getUserInput());
            switch (selection) {
                case 1 -> {
                    try {
                        loadUserList().displayUserList();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> addUserItemMenu();
                case 3 -> {
                    try {
                        userItemCompleteMenu();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 4 -> finished = true;
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
        try {
            loadUserList().displayUserList();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Display prompts needed to get input for the new To-Do list item

        addItemDescriptionPrompt.displayPrompt();
        toDoItemDescription = addItemDescriptionPrompt.getUserInput();

        addDueDatePrompt.displayPrompt();
        toDoItemDueDate = addDueDatePrompt.getUserInput();

        //Construct the new ToDOListItem and add it to the to-do list
        ToDoListItem newToDoListItem = new ToDoListItem(toDoItemDescription, toDoItemDueDate);
        try {
            ListItemQueries.query_insertNewListItem(connectToDB(), userList.getUserListID(), newToDoListItem);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void userItemCompleteMenu() throws SQLException, ClassNotFoundException {
        //Prompts
        InputPrompt userItemCompletePrompt = new InputPrompt("Which item was completed?");

        //Display userList so the user can see it's current state
        UserList userList = loadUserList();

        //Display prompt
        userList.displayUserList();
        userItemCompletePrompt.displayPrompt();
        int userItemComplete = Integer.parseInt(userItemCompletePrompt.getUserInput()) - 1;

        //Attempt to mark the item as complete
        if(userItemComplete < userList.getUserListItems().size() && userItemComplete >=0){
            userList.getUserListItems().get(userItemComplete).setCompleted(true);
            ListItemQueries.query_updateCompletedStatus(connectToDB(), userList.getUserListID(), userList.getUserListItems().get(userItemComplete));
        } else {
            System.out.println("Invalid selection.");
        }
    }
}
