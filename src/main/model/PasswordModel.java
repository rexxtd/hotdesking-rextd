package main.model;

import main.SQLConnection;

import java.sql.*;
import java.util.Random;

public class PasswordModel {
    Connection connection;
    public ResetModel resetModel = new ResetModel();

    public PasswordModel() {
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    //generate random password
    public String passwordGenerate(int max, int min)  //set minimum length = 4 and  maximum length = 16
    {
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // generate random length for password
        int passLength = (int) ((Math.random() * (max - min)) + min);

        for(int i = 0; i < passLength; i++)
        {
            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        return sb.toString();
    }

    //add password to database
    public void addPassword(String newPass)
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String insertFields = "UPDATE Employee SET password = '" ;
        String insertValues =  newPass + "' WHERE username = '" + resetModel.getAccount() + "'";
        String query = insertFields + insertValues;

        try
        {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}