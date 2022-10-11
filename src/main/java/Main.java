import Prompts.*;
import UserLists.*;

/**
 * This version of the ToDoList program uses a MySQL database to store ToDoList items
 * @author Andrew McKay
 * For CEN-4025C
 */
public class Main {
    static UserList userList = loadUserList();
    static int selection;

    public static void main(String[] args){
        InputPrompt mainMenu = new InputPrompt(
                """
                        Please select an option:\s
                        \t1: View To-Do List
                        \t2: Add Item to To-Do List
                        \t3: Mark Item as Complete
                        \t4: Remove Item from To-Do List
                        \t5: Exit Program
                        """);

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

    /**
     * Default UserList builder
     * @return A new, empty UserList object
     */
    private static UserList loadUserList(){
        return new UserList();
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
