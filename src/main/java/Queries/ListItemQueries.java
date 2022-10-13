package Queries;

import UserLists.ToDoListItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListItemQueries {
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

    public static void query_updateCompletedStatus(Connection dbConnection, int listID, ToDoListItem updateItem){
        try{
            PreparedStatement statement = dbConnection.prepareStatement("""
                UPDATE list_items
                SET item_completed = ?
                WHERE list_id = ? AND item_description = ? AND item_due_date = ?
            """);
            statement.setBoolean(1, updateItem.isCompleted());
            statement.setInt(2, listID);
            statement.setString(3, updateItem.getItemDescription());
            statement.setString(4, updateItem.getDueDate());

            statement.executeUpdate();
            dbConnection.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
