package dev._xdbe.booking.creelhouse.usecase;

import java.util.List;

import org.springframework.stereotype.Service;
import dev._xdbe.booking.creelhouse.domain.entity.Booking;
import dev._xdbe.booking.creelhouse.domain.port.BookingRepository;

@Service
public class ListBookingUsecase {

    private final BookingRepository bookingRepository;

    public ListBookingUsecase(
        BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> execute() {
        return bookingRepository.findAll();
    }

}