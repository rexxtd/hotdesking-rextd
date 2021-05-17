package main.model;

import java.awt.*;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HistoryModel
{
    String id,date,time,sit;
    Button cancel;

    public HistoryModel(String id, String date, String time, String sit, Button cancel)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.sit = sit;
        this.cancel = cancel;

        // open new window to cancel specific booking
        cancel.setOnAction(e ->
        {
            Parent anotherRoot = null;
            try
            {
                anotherRoot = FXMLLoader.load(getClass().getResource("../ui/cancelbooking.fxml"));
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            Stage anotherStage = new Stage();
            Scene anotherScene = new Scene(anotherRoot);
            anotherStage.setScene(anotherScene);
            anotherStage.show();
            anotherStage.setTitle("Cancel Booking");
        });
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getSit()
    {
        return sit;
    }

    public void setSit(String sit)
    {
        this.sit = sit;
    }

    public Button getCancel()
    {
        return cancel;
    }

    public void setCancel(Button cancel)
    {
        this.cancel = cancel;
    }
}
