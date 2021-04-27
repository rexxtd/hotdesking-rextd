package main;
import java.sql.*;


public class SQLConnection {

    public static Connection connect(){
        try{
          Class.forName("org.sqlite.JDBC");
          Connection connection = DriverManager.getConnection("jdbc:sqlite:assignment.db");
          return connection;

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
