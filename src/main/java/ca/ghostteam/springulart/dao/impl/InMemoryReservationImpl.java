//package ca.ghostteam.springulart.dao.impl;
//
//import ca.ghostteam.springulart.model.ReservationModel;
//import ca.ghostteam.springulart.dao.ReservationDao;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
///**
// * @author Josue Lubaki
// * @version 1.0
// * @since 2022-03-27
// */
//@Repository("fake-repository-reservations")
//public class InMemoryReservationImpl implements ReservationDao {
//
//    private final List<ReservationModel> LIST_RESERVATIONS = new ArrayList<>();
//
//    @Override
//    public List<ReservationModel> findAll() {
//        return LIST_RESERVATIONS;
//    }
//
//    @Override
//    public Optional<ReservationModel> findById(String id) {
//        return LIST_RESERVATIONS
//                .stream()
//                .filter(reservation -> reservation.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public Optional<ReservationModel> save(ReservationModel reservation) {
//        reservation.setId(UUID.randomUUID().toString());
//        LIST_RESERVATIONS.add(reservation);
//        return Optional.of(reservation);
//    }
//
//    @Override
//    public Optional<ReservationModel> update(String id, ReservationModel reservation) {
//        // get old reservation information
//        Optional<ReservationModel> oldReservation = findById(id);
//
//        // get index of oldReservation
//        int index = LIST_RESERVATIONS.indexOf(oldReservation.get());
//
//        // keep a same ID
//        reservation.setId(oldReservation.get().getId());
//
//        // set a new one
//        LIST_RESERVATIONS.set(index, reservation);
//
//        return Optional.of(LIST_RESERVATIONS.get(index));
//    }
//
//    @Override
//    public void deleteById(String id) {
//        Optional<ReservationModel> reservationModel = findById(id);
//        LIST_RESERVATIONS.remove(reservationModel.get());
//    }
//}
