package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.FxmlLoader;
import main.SQLConnection;
import main.model.HistoryModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AccountDetailController implements Initializable
{
    private static HomeController homeController = new HomeController();
    @FXML
    private Label txtUsername;
    @FXML
    private Label txtFirstname;
    @FXML
    private Label txtLastname;
    @FXML
    private Label txtRole;
    @FXML
    private Label failMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getDetail();
    }

    //get general account information
    public void getDetail()
    {
        //get connection from database to table
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        try
        {
            PreparedStatement preparedStatement = connectionDB.prepareStatement("SELECT * FROM Employee WHERE username = '" + homeController.al_username + "';");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
            {
                txtUsername.setText(rs.getString("username"));
                txtFirstname.setText(rs.getString("firstname"));
                txtLastname.setText(rs.getString("lastname"));
                txtRole.setText(rs.getString("role"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //check if user is admin or not
    public boolean isAdmin()
    {
        if(txtRole.getText() != "admin")
        {
            //failMessage.setText("Only admin can use this function !");
            return false;
        }
        else
        {
            failMessage.setText("");
            return true;
        }
    }

    @FXML
    public void ManageAccount(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/manageaccount.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void ManageBooking(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
