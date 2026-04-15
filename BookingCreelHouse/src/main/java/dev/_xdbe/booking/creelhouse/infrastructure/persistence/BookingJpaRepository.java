package dev._xdbe.booking.creelhouse.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

}
