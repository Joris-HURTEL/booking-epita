package dev._xdbe.booking.creelhouse.usecase;

import org.springframework.stereotype.Service;
import java.util.UUID;

import dev._xdbe.booking.creelhouse.domain.entity.Booking;
import dev._xdbe.booking.creelhouse.domain.entity.BookingDraft;
import dev._xdbe.booking.creelhouse.domain.port.BookingRepository;

@Service
public class CreateBookingUsecase {

    private final BookingRepository bookingRepository;

    public CreateBookingUsecase(
        BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking execute(BookingDraft bookingDraft) {
        Booking booking = new Booking(
            UUID.randomUUID(),
            bookingDraft.checkInDate(),
            bookingDraft.checkOutDate(),
            bookingDraft.nightStays(),
            bookingDraft.name(),
            bookingDraft.creditCardNumber()
        );
        bookingRepository.save(booking);
        return booking;
    }

}