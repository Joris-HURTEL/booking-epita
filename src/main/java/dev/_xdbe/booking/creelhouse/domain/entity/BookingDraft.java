package dev._xdbe.booking.creelhouse.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDraft(
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int nightStays,
        String name,
        String creditCardNumber) {}