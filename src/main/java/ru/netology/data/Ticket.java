package ru.netology.data;

public class Ticket implements Comparable<Ticket> {

    protected int id;
    protected int price;
    protected String airportDeparture;
    protected String airportArrival;
    protected int timeTravel;

    public Ticket() {
    }

    public Ticket(int id, int price, String airportDeparture, String airportArrival, int timeTravel) {
        this.id = id;
        this.price = price;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
        this.timeTravel = timeTravel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(String airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public String getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(String airportArrival) {
        this.airportArrival = airportArrival;
    }

    public int getTimeTravel() {
        return timeTravel;
    }

    public void setTimeTravel(int timeTravel) {
        this.timeTravel = timeTravel;
    }


    @Override
    public int compareTo(Ticket o) {
        if (this.getPrice() < o.price) {
            return -1;
        } else if (this.getPrice() > o.price) {
            return 1;
        } else {
            return 0;
        }
    }
}
