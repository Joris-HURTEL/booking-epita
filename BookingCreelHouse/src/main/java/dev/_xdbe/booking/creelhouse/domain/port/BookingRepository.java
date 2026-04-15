package dev._xdbe.booking.creelhouse.domain.port;

import java.util.List;

import dev._xdbe.booking.creelhouse.domain.entity.Booking;

public interface BookingRepository {

  List<Booking> findAll();
  Booking save(Booking booking);
  
}