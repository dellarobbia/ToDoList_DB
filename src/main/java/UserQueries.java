import Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserQueries {
    public static ArrayList<User> query_AllUsers(Connection dbConnection){
        try {
            ArrayList<User> users = new ArrayList<>();
            PreparedStatement statement = dbConnection.prepareStatement("""
                    SELECT *
                    FROM users
            """);
            ResultSet results = statement.executeQuery();
            while(results.next()){
                users.add(new User(
                        results.getInt(1),
                        results.getString(2)));
            }
            dbConnection.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void query_insertNewUser(Connection dbConnection, String username){
        try{
            PreparedStatement statement = dbConnection.prepareStatement("""
                INSERT INTO users VALUES(null, ?)
            """);
            statement.setString(1, username);

            statement.executeUpdate();
            dbConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
