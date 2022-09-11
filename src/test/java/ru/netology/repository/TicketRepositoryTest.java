package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Ticket;
import ru.netology.exception.NotFoundException;
import ru.netology.exception.AlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

public class TicketRepositoryTest {

    TicketRepository repository = new TicketRepository();

    // тестовые данные
    Ticket ticket1 = new Ticket(1, 1_000, "SVO", "LED", 45);
    Ticket ticket2 = new Ticket(2, 3_200, "DST", "TYG", 120);
    Ticket ticket3 = new Ticket(3, 753, "SDF", "SVO", 260);
    Ticket ticket4 = new Ticket(4, 10_580, "SVO", "LED", 45);
    Ticket ticket5 = new Ticket(5, 3_000, "DST", "REB", 90);
    Ticket ticket6 = new Ticket(6, 2_753, "SDF", "SVO", 259);
    Ticket ticket7 = new Ticket(7, 12_300, "SVO", "LED", 45);
    Ticket ticket8 = new Ticket(8, 300, "VFR", "LED", 220);
    Ticket ticket9 = new Ticket(9, 3_200, "DST", "TYG", 230);
    Ticket ticket10 = new Ticket(10, 100, "SVO", "LED", 60);
    Ticket ticket11 = new Ticket(11, 35_000, "NYR", "TYG", 120);
    Ticket ticket12 = new Ticket(12, 7_535, "SDF", "LED", 259);
    Ticket ticket5Duble = new Ticket(5, 234, "SDF", "SDS", 456);

    @BeforeEach
        // Пред условия для тестов
    void setup() {
        repository.addTicket(ticket1);
        repository.addTicket(ticket2);
        repository.addTicket(ticket3);
        repository.addTicket(ticket4);
        repository.addTicket(ticket5);
        repository.addTicket(ticket6);
        repository.addTicket(ticket7);
        repository.addTicket(ticket8);
        repository.addTicket(ticket9);
        repository.addTicket(ticket10);
        repository.addTicket(ticket11);
        repository.addTicket(ticket12);
    }

    @Test
    void shouldAddTicket() { // тест на добывление в массив

        Ticket[] expented = new Ticket[]{
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8,
                ticket9,
                ticket10,
                ticket11,
                ticket12
        };
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expented, actual);
    }

    @Test
        // тест на добавление билета Id которого уже добавлен
    void shouldAddTicketAlreadyExistsException() {
        assertThrows(AlreadyExistsException.class, () -> {
            repository.addTicket(ticket5Duble);
        });
    }

    @Test
    void shouldRemoveById() { // удаление билета по индетификатору Id

        repository.removeById(2);

        Ticket[] expected = new Ticket[]{
                ticket1,

                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8,
                ticket9,
                ticket10,
                ticket11,
                ticket12
        };

        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
        // тест на удаления билета из массива по индетификатору Id которого нет в массиве
    void shouldRemoveByIdNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(14);
        });
    }

    @Test
        // тест на поиск билета по индетификатору Id
    void shouldFindByIdPass() {

        Ticket expected = ticket6;

        Ticket actual = repository.findById(6);

        assertEquals(expected, actual);
    }

    @Test
        // тест на исключение при поиске билета по индетификатору Id которого нет в массиве
    void shouldFindByIdNull() {
        assertNull(repository.findById(18));
    }
}
