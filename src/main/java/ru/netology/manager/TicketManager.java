package ru.netology.manager;

import ru.netology.data.Ticket;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import java.sql.Array;
import java.util.Arrays;

public class TicketManager {

    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    // методы зависимые от репозитория
    // добавление билета в массив
    public void addTicket(Ticket addTicket) {
        repository.addTicket(addTicket);
    }

    // удаление билета из массива по Id
    public void removeById(int removedId) {
        repository.removeById(removedId);
    }

    // логика менеджерра
    // поиск билета по аэропортам вылета-прилета
    public Ticket[] findAll(String airportDeparture, String airportArrival) {
        Ticket[] results = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matchesFromTo(ticket, airportDeparture, airportArrival)) {
                Ticket[] tmp = new Ticket[results.length + 1];
                System.arraycopy(results, 0, tmp, 0, results.length);
                tmp[results.length] = ticket;
                results = tmp;
            }
        }
        if (results.length == 0) {
            throw new NotFoundException("Ticket from " + airportDeparture + " to " + airportArrival + "not found");
        }
        Arrays.sort(results);
        return results;
    }

    // сравнение по аэропортам прилета-вылета
    public boolean matchesFromTo(Ticket ticket, String fromInput, String toInput) {
        boolean matchesFrom;
        matchesFrom = ticket.getAirportDeparture().toUpperCase().contains(fromInput.toUpperCase());
        boolean matchesTo;
        matchesTo = ticket.getAirportArrival().toUpperCase().contains(toInput.toUpperCase());
        return matchesFrom && matchesTo;
    }
}
