package Queries;

import UserLists.ToDoListItem;
import UserLists.UserList;
import Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserListQueries {
    public static UserList query_getList(Connection dbConnection, int userID){
        try{
            PreparedStatement statement = dbConnection.prepareStatement("""
                    SELECT list_id
                    FROM lists
                    WHERE user_id = ?
            """);
            statement.setInt(1, userID);

            ResultSet results = statement.executeQuery();

            results.next();
            return new UserList(
                    results.getInt(1),
                    query_getIncompleteListItems(dbConnection, results.getInt(1))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<ToDoListItem> query_getIncompleteListItems(Connection dbConnection, int listID){
        try {
            PreparedStatement statement = dbConnection.prepareStatement("""
                    SELECT lists.list_id, li.item_description, li.item_due_date, li.item_completed
                    FROM lists
                    INNER JOIN list_items li on lists.list_id = li.list_id
                    WHERE lists.list_id = ? AND li.item_completed = FALSE
            """);
            statement.setInt(1,listID);

            ResultSet results = statement.executeQuery();

            ArrayList<ToDoListItem> toDoListItems = new ArrayList<>();

            while(results.next()){
                toDoListItems.add(new ToDoListItem(
                        results.getString(2),
                        results.getString(3),
                        results.getBoolean(4)
                ));
            }

            dbConnection.close();
            return toDoListItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void query_insertNewList(Connection dbConnection, int userID){
        try{
            PreparedStatement statement = dbConnection.prepareStatement("""
                INSERT INTO lists VALUES(NULL, ?)
            """);
            statement.setInt(1, userID);

            statement.executeUpdate();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void query_insertNewListItem(Connection dbConnection, int listID, ToDoListItem newItem){
        try{
            PreparedStatement statement = dbConnection.prepareStatement("""
                INSERT INTO list_items VALUES(NULL, ?, ?, ?, ?)
            """);
            statement.setInt(1, listID);
            statement.setString(2, newItem.getItemDescription());
            statement.setString(3, newItem.getDueDate());
            statement.setBoolean(4, newItem.isCompleted());

            statement.executeUpdate();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
