package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import main.SQLConnection;
import main.model.HistoryModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private TableColumn<HistoryModel, Button> col_view;
    @FXML

    ObservableList<HistoryModel> oblist = FXCollections.observableArrayList();

    public void initialize() throws SQLException
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
                                            new Button("View")));
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, e);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_sit.setCellValueFactory(new PropertyValueFactory<>("sit"));
        col_view.setCellValueFactory(new PropertyValueFactory<>("view"));

        table.setItems(oblist);
    }
}
