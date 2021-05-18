package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.HistoryModel;
import java.net.URL;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoryController
{
    @FXML
    private TableView<HistoryModel> table;
    @FXML
    private TableColumn<HistoryModel, String> col_id;
    @FXML
    private TableColumn<HistoryModel, String> col_date;
    @FXML
    private TableColumn<HistoryModel, String> col_time;
    @FXML
    private TableColumn<HistoryModel, String> col_sit;
    @FXML
    private TableColumn<HistoryModel, Button> col_cancel;
    @FXML

    ObservableList<HistoryModel> oblist = FXCollections.observableArrayList();

    public void initialize()
    {
        try
        {
            //get connection from database to table
            SQLConnection sqlConnection = new SQLConnection();
            Connection connectionDB = sqlConnection.connect();

            //statement for getting data
            ResultSet rs = connectionDB.createStatement().executeQuery("select * from booking");

            //add information to the table
            while (rs.next())
            {
                oblist.add(new HistoryModel(rs.getString("id"), rs.getString("date"),
                                            rs.getString("time"), rs.getString("sit"),
                                            new Button("cancel")));
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, e);
        }
        UpdateTable();
    }

    public void UpdateTable()
    {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_sit.setCellValueFactory(new PropertyValueFactory<>("sit"));
        col_cancel.setCellValueFactory(new PropertyValueFactory<>("cancel"));

        table.setItems(oblist);
    }

    public void deleteBooking()
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        String query = "DELETE FROM Booking WHERE id = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            ObservableList<HistoryModel> historyModels;
            historyModels = table.getSelectionModel().getSelectedItems();
            System.out.println("historyModels.get(0).getId()");

            preparedStatement = connectionDB.prepareStatement(query);
            preparedStatement.setString(1, historyModels.get(0).getId());

            resultSet = preparedStatement.executeQuery();
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(query);

            UpdateTable();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
