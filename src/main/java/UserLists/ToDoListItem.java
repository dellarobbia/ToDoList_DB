package UserLists;

/**
 * UserListItem that represents an item that would appear on a To-Do list
 * @author Andrew McKay
 */

public class ToDoListItem extends UserListItem{
    //Properties
    private String dueDate;
    private boolean completed;

    //Getters & Setters
    public String getDueDate(){
        return dueDate;
    }
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {return completed;}
    public void setCompleted(boolean completed) { this.completed = completed;}

    //Constructor
    /**
     * Creates a To-Do list item with a default completed value of false
     * @param toDoDescription String to describe the item that needs to be done.
     * @param dueDate String to show when the item should be completed by.
     */
    public ToDoListItem(String toDoDescription, String dueDate) {
        super(toDoDescription);
        setDueDate(dueDate);
        setCompleted(false);
    }

    /**
     * Creates a To-Do list item allowing for completed to be set at instantiation
     * @param toDoDescription String to describe the item that needs to be done.
     * @param dueDate String to show when the item should be completed by.
     * @param completed boolean to show if the item has been completed or not.
     */
    public ToDoListItem(String toDoDescription, String dueDate, boolean completed) {
        super(toDoDescription);
        setDueDate(dueDate);
        setCompleted(completed);
    }

    @Override
    public String toString() {
        String completedString;
        if(completed){
            completedString = "Completed";
        } else {
            completedString = "Incomplete";
        }
        return getItemDescription() + " (Due on " + dueDate + "): " + completedString;
    }
}