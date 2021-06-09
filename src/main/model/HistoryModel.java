package main.model;

public class HistoryModel
{
    int id;
    String date,time,seat,approved;

    public HistoryModel(Integer id, String date, String time, String seat, String approved)
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.seat = seat;
        this.approved = approved;
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

    public String getApproved() { return approved; }

    public void setApproved(String approved)
    {
        this.approved = approved;
    }
}
