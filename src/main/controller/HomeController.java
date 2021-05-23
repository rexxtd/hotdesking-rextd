package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import main.FxmlLoader;
import main.controller.LoginController;
import main.model.HomeModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{
    static String al_username;
    public LoginController loginController = new LoginController();
    public HomeModel homeModel = new HomeModel();
    @FXML
    private Label isConnected;
    @FXML
    private BorderPane mainPane;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (homeModel.isDbConnected())
        {
            al_username = loginController.username;
            isConnected.setText("Welcome, " + al_username);
        }

        //display default content for the first time
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("homescreen");
        mainPane.setCenter(view);
    }

    //display booking inside homepage when click on booking button
    @FXML
    private void loadBooking(ActionEvent event) throws IOException
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("bookingscreen");
        mainPane.setCenter(view);
    }

    //display rules in a new window when user click on "Learn more"
    @FXML
    private void learnMore(ActionEvent event) throws IOException
    {
        Parent anotherRoot = FXMLLoader.load(getClass().getResource("../ui/learnmore.fxml"));
        Stage anotherStage = new Stage();
        Scene anotherScene = new Scene(anotherRoot);
        anotherStage.setScene(anotherScene);
        anotherStage.show();
        anotherStage.setTitle("Learn more");
    }

    //display default content inside homepage when click on home button
    @FXML
    private void loadHome(ActionEvent event) throws IOException
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("homescreen");
        mainPane.setCenter(view);
    }

    //display booking history content when user click on "View history"
    @FXML
    private void loadHistory(ActionEvent event) throws IOException
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("historyscreen");
        mainPane.setCenter(view);
    }

    //display account detail when user click on "Your Account"
    @FXML
        private void loadAccountDetail(ActionEvent event) throws IOException
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("accountdetail");
        mainPane.setCenter(view);
    }


    //logout user
    @FXML
    public void Logout(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}