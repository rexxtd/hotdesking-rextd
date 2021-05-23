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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.ManageAccountModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageAccountController implements Initializable
{
    @FXML
    private TableView<ManageAccountModel> table;
    @FXML
    private TableColumn<ManageAccountModel, Integer> col_id;
    @FXML
    private TableColumn<ManageAccountModel, String> col_firstname;
    @FXML
    private TableColumn<ManageAccountModel, String> col_lastname;
    @FXML
    private TableColumn<ManageAccountModel, String> col_role;
    @FXML
    private TableColumn<ManageAccountModel, String> col_username;
    @FXML
    private TableColumn<ManageAccountModel, String> col_password;
    @FXML
    private TableColumn<ManageAccountModel, String> col_question;
    @FXML
    private TableColumn<ManageAccountModel, String> col_answer;
    @FXML
    private Label txtID;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtQuestion;
    @FXML
    private TextField txtAnswer;

    ObservableList<ManageAccountModel> listM;
    int index = -1;

    public void initialize(URL location, ResourceBundle resources)
    {
        /*
        **
        try
        {
            UpdateTable();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            e.getCause();
        }
        **
        */
    }

     /*
        **
    public static ObservableList<ManageAccountModel> getDatausers() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        ObservableList<ManageAccountModel> list = FXCollections.observableArrayList();
        try
        {
            PreparedStatement preparedStatement = connectionDB.prepareStatement("SELECT * FROM Employee");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                list.add(new ManageAccountModel(Integer.parseInt(rs.getString("id")), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getString("role"), rs.getString("username"),
                        rs.getString("password"), rs.getString("secret_qs"), rs.getString("answer")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public void UpdateTable() throws SQLException
    {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));
        col_answer.setCellValueFactory(new PropertyValueFactory<>("answer"));

        listM = getDatausers();
        table.setItems(listM);
    }

    @FXML
    void getSelected(MouseEvent event)
    {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1)
        {
            return;
        }
        txtID.setText(col_id.getCellData(index).toString());
        txtFirstname.setText(col_firstname.getCellData(index));
        txtLastname.setText(col_lastname.getCellData(index));
        txtRole.setText(col_role.getCellData(index));
        txtUsername.setText(col_username.getCellData(index));
        txtPassword.setText(col_password.getCellData(index));
        txtQuestion.setText(col_question.getCellData(index));
        txtAnswer.setText(col_answer.getCellData(index));
    }

    public void Delete() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String sql = "DELETE FROM Employee WHERE username = ?";
        try
        {
            PreparedStatement preparedStatement = connectionDB.prepareStatement(sql);
            preparedStatement.setString(1, txtUsername.getText());
            preparedStatement.execute();

            UpdateTable();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Add() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String sql = "INSERT INTO Employee (firstname, lastname, role, username, password, secret_qs, answer) VALUES (?,?,?,?,?,?,?) ";
        try
        {
            PreparedStatement preparedStatement = connectionDB.prepareStatement(sql);
            preparedStatement.setString(1, txtFirstname.getText());
            preparedStatement.setString(2, txtLastname.getText());
            preparedStatement.setString(3, txtRole.getText());
            preparedStatement.setString(4, txtUsername.getText());
            preparedStatement.setString(5, txtPassword.getText());
            preparedStatement.setString(6, txtQuestion.getText());
            preparedStatement.setString(7, txtAnswer.getText());
            preparedStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Update() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        try
        {
            String value1 = txtFirstname.getText();
            String value2 = txtLastname.getText();
            String value3 = txtRole.getText();
            String value4 = txtUsername.getText();
            String value5 = txtPassword.getText();
            String value6 = txtQuestion.getText();
            String value7 = txtAnswer.getText();
            String sql = "UPDATE Employee SET firstname = '" + value1 + "', lastname = '" + value2 + "', role = '" +
                    value3 + "', username = '" + value4 + "', password = '" + value5 + "', secret_qs = '" +
                    value6 + "', answer = '" + value7 + "';";
            PreparedStatement preparedStatement = connectionDB.prepareStatement(sql);
            preparedStatement.execute();

            UpdateTable();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Back(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      */
}
