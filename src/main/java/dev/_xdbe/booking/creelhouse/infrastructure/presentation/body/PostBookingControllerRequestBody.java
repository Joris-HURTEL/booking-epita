package dev._xdbe.booking.creelhouse.infrastructure.presentation.body;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record PostBookingControllerRequestBody(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate checkInDate,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate checkOutDate,
    int nightStays,
    String name,
    String creditCardNumber
) {}
