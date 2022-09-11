package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.data.Ticket;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketManagerTest {

    @Mock
    TicketRepository repository = Mockito.mock(TicketRepository.class);

    @InjectMocks
    TicketManager manager = new TicketManager(repository);

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

    // тесты на исключения NotFoundException в методе findAll()
    // при поиске в пустом массиве
    @Test
    void shouldNotFindMockEmpty() {
        Ticket[] mockEmpty = {};

        doReturn(mockEmpty).when(repository).findAll();

        assertThrows(NotFoundException.class, () -> {
            manager.findAll("DME", "LED");
        });
    }

    //  при поиске в массиве из одного элемента
    @Test
    void souldNotFindMockOneTicket() {
        Ticket[] oneTicket = {
                ticket1
        };

        doReturn(oneTicket).when(repository).findAll();

        assertThrows(NotFoundException.class, () -> {
            manager.findAll("ASI", "GRY");
        });
    }

    // при поиске в массиве из 10 элементов
    @Test
    void souldNotFindMockTenTicket() {
        Ticket[] tenTicket = {
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8,
                ticket9,
                ticket10
        };

        doReturn(tenTicket).when(repository).findAll();

        assertThrows(NotFoundException.class, () -> {
            manager.findAll("DMA", "MVO");
        });
    }

    // тесты на один результат поиска в методе findAll()
    //  при поиске в массиве из одного элемента
    @Test
    void shouldFindOneResultMockWithOneTicket() {
        Ticket[] oneTicket = {
                ticket1
        };

        doReturn(oneTicket).when(repository).findAll();

        Ticket[] expected = new Ticket[]{ticket1};

        Ticket[] actual = manager.findAll("SVO", "LED");

        assertArrayEquals(expected, actual);
    }

    // при поиске в массиве из 10 элементов
    @Test
    void shouldFindOneResultMockWithTenTicket() {
        Ticket[] tenTicket = {
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8,
                ticket9,
                ticket10
        };

        doReturn(tenTicket).when(repository).findAll();

        Ticket[] expected = new Ticket[]{ticket5};

        Ticket[] actual = manager.findAll("DST", "REB");

        assertArrayEquals(expected, actual);
    }

    // тесты на несколько результатов в методе findAll() c сортировкой по цене (цена по возрастанию)
    @Test
    void shouldFindManyResultsMockWithTenTicketOne() {
        Ticket[] tenTicket = {
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8,
                ticket9,
                ticket10
        };

        doReturn(tenTicket).when(repository).findAll();

        Ticket[] expected = new Ticket[]{
                ticket10,
                ticket1,
                ticket4,
                ticket7
        };

        Ticket[] actual = manager.findAll("SVO", "LED");

        assertArrayEquals(expected, actual);
    }

    // тесты на несколько результатов в методе findAll() два билета с одинаковой ценой
    @Test
    void shouldFindManyResultsMockWithTenTicketOne2() {
        Ticket[] tenTicket = {
                ticket1,
                ticket2,
                ticket3,
                ticket4,
                ticket5,
                ticket6,
                ticket7,
                ticket8,
                ticket9,
                ticket10
        };

        doReturn(tenTicket).when(repository).findAll();

        Ticket[] expected = new Ticket[]{
                ticket2,
                ticket9
        };

        Ticket[] actual = manager.findAll("DST", "TYG");

        assertArrayEquals(expected, actual);
    }

    // тесты на matches()
    // тест на сравнение при двух верных данных (нижний регистр)
    @Test
    void shouldMatchesFromToTrue() {
        Ticket ticketOne = ticket1;

        assertTrue(manager.matchesFromTo(ticketOne, "svo", "led"));
    }

    // тест на сравнение при двух верных данных (вверхний регистр)
    @Test
    void shouldMatchesFromToTrue2() {
        Ticket ticketOne = ticket1;

        assertTrue(manager.matchesFromTo(ticketOne, "SVO", "LED"));
    }

    // тест на сравнение при неверном пункте вылета и верном пункте прилета
    @Test
    void shouldMatchesFromToFalseOne() {
        Ticket ticketOne = ticket1;

        assertFalse(manager.matchesFromTo(ticketOne, "dme", "led"));
    }

    // тест на сравнение при верном пункте вылета и не верном пункте прилета
    @Test
    void shouldMatchesFromToFalseTwo() {
        Ticket ticketOne = ticket1;

        assertFalse(manager.matchesFromTo(ticketOne, "SVO", "RVH"));
    }

    // тест на сравнение при не верном пункте вылета и не верном пункте прилета
    @Test
    void shouldMatchesFromToFalseThree() {
        Ticket ticketOne = ticket1;

        assertFalse(manager.matchesFromTo(ticketOne, "VKO", "RVH"));
    }
}
