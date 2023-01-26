package be.technifutur.mvc.utils;

import be.technifutur.mvc.models.entities.*;
import be.technifutur.mvc.models.entities.enums.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInit implements InitializingBean {

    private final EntityManager manager;

    public DataInit(EMFSharer emfSharer) {
        this.manager = emfSharer.createEntityManager();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        manager.getTransaction().begin();

        Employee employee = new Employee();
        employee.setId(4L);
        employee.setFirstName("luc");
        employee.setLastName("dubois");
        employee.setAddress("une adresse");
        employee.setEmail("@.com");
        employee.setPhone("000");

        employee = manager.merge(employee);

        Hotel hotel = new Hotel();
        hotel.setId(3L);
        hotel.setAddress("ici");
        hotel.setName("z hotel");
        hotel.setNbStar(3);
        hotel.setReceptionist(employee);

        hotel = manager.merge(hotel);

        Room room = new Room();
        room.setId(1L);
        room.setSize(10);
        room.setHotel(hotel);
        room.setAvailable(true);
        room.setSimpleBeds(1);
        room.setDoubleBeds(1);
        room.setAdditionalBeds(0);
        room.setNumber(101);
        room.setFloor(1);
        room.setType(RoomType.BASIC);
        room.setView(RoomView.AVERAGE);

        room = manager.merge( room );
//
//        User user = new User();
//
//        user.setId(1L);
//        user.setUsername("user");
//        user.setPassword("pass");
//        user.setRole("customer");
//        user.setAddress("adresse");
//        user.setEmail("email");
//        user.setPhone("phone");
//        user.setFirstName("luc");
//        user.setLastName("dubois");
//
//        manager.merge(user);
//
//        Reservation reservation = new Reservation();
//
//        reservation.setId(1L);
//        reservation.setBegin(LocalDateTime.of(2023, 10, 29,12,0,0));
//        reservation.setEnd(LocalDateTime.of(2023, 11, 02,12,0,0));
//        reservation.setRoom(room);
//        reservation.setWithAdditionalBeds(0);
//        reservation.setUser(user);
//        reservation.setPrice(100.);
//        reservation.setBreakfast(true);
//        reservation.setDateCreate(LocalDateTime.now());
//
//        manager.merge(reservation);
//
//        reservation = new Reservation();
//
//        reservation.setId(1L);
//        reservation.setBegin(LocalDateTime.of(2023, 9, 30,12,0,0));
//        reservation.setEnd(LocalDateTime.of(2023, 10, 3,12,0,0));
//        reservation.setRoom(room);
//        reservation.setUser(user);
//        reservation.setWithAdditionalBeds(1);
//        reservation.setPrice(100.);
//        reservation.setBreakfast(true);
//        reservation.setDateCreate(LocalDateTime.now());
//
//        manager.merge(reservation);
//
//
//        reservation = new Reservation();
//
//        reservation.setId(3L);
//        reservation.setBegin(LocalDateTime.of(2023, 10, 3,12,0,0));
//        reservation.setEnd(LocalDateTime.of(2023, 10, 30,12,0,0));
//        reservation.setRoom(room);
//        reservation.setUser(user);
//        reservation.setWithAdditionalBeds(2);
//        reservation.setPrice(100.);
//        reservation.setBreakfast(false);
//        reservation.setDateCreate(LocalDateTime.now());
//
//        manager.merge(reservation);

        manager.getTransaction().commit();

    }
}
