package model;

public class Bus {

    private int id;
    private String name;
    private String from;
    private String to;
    private String date;
    private String time;
    private int seats;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
}