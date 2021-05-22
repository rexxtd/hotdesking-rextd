package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import main.SQLConnection;
import main.model.HistoryModel;
import main.model.BookCheckingModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HistoryController implements Initializable
{
    int index = -1;
    static String id,date,time;
    ObservableList<HistoryModel> oblist;

    private static HomeController homeController = new HomeController();
    private BookCheckingModel bookCheckingModel = new BookCheckingModel();
    @FXML
    private TableView<HistoryModel> table;
    @FXML
    private TableColumn<HistoryModel, Integer> col_id;
    @FXML
    private TableColumn<HistoryModel, String> col_date;
    @FXML
    private TableColumn<HistoryModel, String> col_time;
    @FXML
    private TableColumn<HistoryModel, String> col_seat;
    @FXML
    private Label successMessage;
    @FXML
    private Label failMessage;

    public void initialize(URL location, ResourceBundle resources)
    {
        UpdateTable();
    }

    public static ObservableList<HistoryModel> getDatausers()
    {
        //get connection from database to table
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();

        ObservableList<HistoryModel> list = FXCollections.observableArrayList();
        try
        {
            PreparedStatement preparedStatement = connectionDB.prepareStatement("SELECT * FROM Booking WHERE username = '" + homeController.al_username + "';");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                list.add(new HistoryModel(rs.getInt("id"), rs.getString("date"),
                        rs.getString("time"), rs.getString("seat")));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public void UpdateTable()
    {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_seat.setCellValueFactory(new PropertyValueFactory<>("seat"));

        oblist = getDatausers();
        table.setItems(oblist);
    }

    // get selected row from tableview
    @FXML
    void getSelected (MouseEvent event)
    {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1)
        {
            return;
        }
        id = col_id.getCellData(index).toString();
        date = col_date.getCellData(index);
        time = col_time.getCellData(index);
    }

    // delete row from database
    public void deleteBooking()
    {
        // need to check first if the selected booking has finished or it's a future one
        if(!bookCheckingModel.checkDateTime(date,time))
        {
            SQLConnection sqlConnection = new SQLConnection();
            Connection connectionDB = sqlConnection.connect();

            String query = "DELETE FROM Booking WHERE id = ?";

            PreparedStatement preparedStatement = null;
            try
            {
                preparedStatement = connectionDB.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.execute();

                UpdateTable();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
            failMessage.setText("");
            successMessage.setText("Cancel booking successfully !");
        }
        else
        {
            successMessage.setText("");
            failMessage.setText("This booking is finished, cannot cancel !");
        }
    }

    //cancel booking button action event
    public void Cancel(ActionEvent event) throws IOException
    {
        deleteBooking();
    }
}
