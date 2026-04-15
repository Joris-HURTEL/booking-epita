package dev._xdbe.booking.creelhouse.infrastructure.persistence;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

import dev._xdbe.booking.creelhouse.domain.entity.Booking;
import dev._xdbe.booking.creelhouse.infrastructure.persistence.CreditCardConverter;

@Entity
@Table(name = "BOOKING")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int nightStays;
    private String name;

    @Convert(converter = CreditCardConverter.class)
    private String creditCardNumber;

    protected BookingEntity() {
    }

    public BookingEntity(Booking booking) {
        this.name = booking.name();
        this.creditCardNumber = booking.creditCardNumber();
        this.checkInDate = booking.checkInDate();
        this.checkOutDate = booking.checkOutDate();
        this.nightStays = booking.nightStays();
    }

    public Booking toBooking() {
        return new Booking(
            this.id,
            this.checkInDate,
            this.checkOutDate,
            this.nightStays,
            this.name,
            this.creditCardNumber
        );
    }
}
