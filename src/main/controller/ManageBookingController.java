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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.SQLConnection;
import main.model.ManageBookingModel;
import main.model.BookCheckingModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageBookingController implements Initializable
{
    private BookCheckingModel bookCheckingModel = new BookCheckingModel();
    @FXML
    private TableView<ManageBookingModel> table;
    @FXML
    private TableColumn<ManageBookingModel, Integer> col_id;
    @FXML
    private TableColumn<ManageBookingModel, String> col_username;
    @FXML
    private TableColumn<ManageBookingModel, String> col_date;
    @FXML
    private TableColumn<ManageBookingModel, String> col_time;
    @FXML
    private TableColumn<ManageBookingModel, String> col_seat;
    @FXML
    private TableColumn<ManageBookingModel, String> col_approved;
    @FXML
    private Label txtID;
    @FXML
    private Label txtUsername;
    @FXML
    private Label txtDate;
    @FXML
    private Label txtTime;
    @FXML
    private Label txtSeat;
    @FXML
    private Label txtApproved;
    @FXML
    private Label failMessage;
    @FXML
    private Label successMessage;

    ObservableList<ManageBookingModel> listM;
    int index = -1;

    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            UpdateTable();
            failMessage.setText("Choose the booking from the table and select action (Approve/Cancel)");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public static ObservableList<ManageBookingModel> getDatabookings() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        ObservableList<ManageBookingModel> list = FXCollections.observableArrayList();
        try
        {
            preparedStatement = connectionDB.prepareStatement("SELECT * FROM Booking");
            rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                list.add(new ManageBookingModel(Integer.parseInt(rs.getString("id")), rs.getString("username"),
                        rs.getString("date"), rs.getString("time"), rs.getString("seat"),rs.getString("approved")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        finally
        {
            preparedStatement.close();
            rs.close();
        }
        return list;
    }

    public void UpdateTable() throws SQLException
    {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        col_approved.setCellValueFactory(new PropertyValueFactory<>("approved"));

        listM = getDatabookings();
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
        txtUsername.setText(col_username.getCellData(index));
        txtDate.setText(col_date.getCellData(index));
        txtTime.setText(col_time.getCellData(index));
        txtSeat.setText(col_seat.getCellData(index));
        txtApproved.setText(col_approved.getCellData(index));
    }

    public void Cancel() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();
        PreparedStatement preparedStatement = null;

        String id = txtID.getText();
        String value = txtApproved.getText();

        if (bookCheckingModel.checkDateTime(txtDate.getText(),txtTime.getText()))
        {
            failMessage.setText("This booking is finished, cannot change.");
            successMessage.setText("");
        }
        else
        {
            if (value.equals("no"))
            {
                failMessage.setText("This booking has been cancelled already!");
                successMessage.setText("");
            }
            else
            {
                String sql = "UPDATE Booking SET approved ='" + "no" + "'WHERE id = '" + id + "';";
                try
                {
                    preparedStatement = connectionDB.prepareStatement(sql);
                    preparedStatement.execute();

                    failMessage.setText(null);
                    successMessage.setText("Booking has been cancelled successfully!");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    e.getCause();
                }
                finally
                {
                    preparedStatement.close();
                }
                UpdateTable();
            }
        }
    }

    public void Approve() throws SQLException
    {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connectionDB = sqlConnection.connect();
        PreparedStatement preparedStatement = null;

        String id = txtID.getText();
        String value = txtApproved.getText();


        if (bookCheckingModel.checkDateTime(txtDate.getText(),txtTime.getText()))
        {
            failMessage.setText("This booking is finished, cannot change.");
            successMessage.setText("");
        }
        else
        {
            if (value.equals("yes"))
            {
                failMessage.setText("This booking has been approved already!");
                successMessage.setText("");
            }
            else
            {
                String sql = "UPDATE Booking SET approved ='" + "yes" + "'WHERE id = '" + id + "';";
                try
                {
                    preparedStatement = connectionDB.prepareStatement(sql);
                    preparedStatement.execute();

                    failMessage.setText(null);
                    successMessage.setText("Booking has been approved successfully!");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    e.getCause();
                }
                finally
                {
                    preparedStatement.close();
                }
                UpdateTable();
            }
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
}
