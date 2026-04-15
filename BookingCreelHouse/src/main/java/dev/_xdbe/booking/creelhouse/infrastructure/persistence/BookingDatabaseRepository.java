package dev._xdbe.booking.creelhouse.infrastructure.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import dev._xdbe.booking.creelhouse.domain.entity.Booking;
import dev._xdbe.booking.creelhouse.domain.port.BookingRepository;


@Repository
public class BookingDatabaseRepository implements BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;

    public BookingDatabaseRepository(BookingJpaRepository bookingJpaRepository) {
        this.bookingJpaRepository = bookingJpaRepository;
    }

    @Override
    public List<Booking> findAll() {
        List<BookingEntity> bookingList = bookingJpaRepository.findAll();
        return bookingList.stream()
            .map(BookingEntity::toBooking)
            .collect(Collectors.toList());
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity bookingEntity = new BookingEntity(booking);
        bookingJpaRepository.save(bookingEntity);
        return booking;
    }

}
