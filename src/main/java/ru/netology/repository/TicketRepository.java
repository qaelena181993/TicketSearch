package ru.netology.repository;

import ru.netology.data.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class TicketRepository {

    public Ticket[] tickets = new Ticket[0];

    // добавление нового билета в массив
    public void addTicket(Ticket addTicket) {
        if (findById(addTicket.getId()) != null) {
            throw new AlreadyExistsException("Element with Id: " + addTicket.getId() + "  already added");
        }
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tickets.length] = addTicket;
        tickets = tmp;
    }

    // получить все добавленые билеты в порядке добавления
    public Ticket[] findAll() {
        return tickets;
    }

    // удалить билет по инднтификатору Id
    public void removeById(int removedId) {
        if (findById(removedId) == null) {
            throw new NotFoundException("Element with Id: " + removedId + " not found");
        }
        Ticket[] tmp = new Ticket[tickets.length - 1];
        int index = 0;
        for (Ticket product : tickets) {
            if (product.getId() != removedId) {
                tmp[index] = product;
                index++;
            }
        }
        tickets = tmp;
    }

    // Поиск товара в массиве по индетификатору Id
    public Ticket findById(int findIid) {
        for (Ticket product : tickets) {
            if (product.getId() == findIid) {
                return product;
            }
        }
        return null;
    }
}
