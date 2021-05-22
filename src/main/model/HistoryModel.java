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
    int id;
    String date,time,seat;

    public HistoryModel(Integer id, String date, String time, String seat)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.seat = seat;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public String getSeat()
    {
        return seat;
    }

    public void setSeat(String seat)
    {
        this.seat = seat;
    }
}
