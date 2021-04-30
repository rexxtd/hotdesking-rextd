package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.LoginModel;
import main.model.ResetModel;
import main.model.SignUpModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetController implements Initializable
{
    public LoginModel loginModel = new LoginModel();
    public ResetModel resetModel = new ResetModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtSecret;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Hyperlink hpLogin;
    @FXML
    private Button signUpButton;
    private Stage stage;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (loginModel.isDbConnected())
        {
            isConnected.setText("Connected");
        }
        else
        {
            isConnected.setText("Not Connected");
        }
    }

    //switch to resetPassword scene
    public void loginPage(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
