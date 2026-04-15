package dev._xdbe.booking.creelhouse.infrastructure.presentation;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev._xdbe.booking.creelhouse.domain.entity.Booking;
import dev._xdbe.booking.creelhouse.domain.entity.BookingDraft;
import dev._xdbe.booking.creelhouse.usecase.CreateBookingUsecase;
import dev._xdbe.booking.creelhouse.infrastructure.presentation.body.PostBookingControllerRequestBody;

@Controller
public class PostBookingController {

    private static final Logger log = LoggerFactory.getLogger(PostBookingController.class);

    private final CreateBookingUsecase createBookingUsecase;

    public PostBookingController(
        CreateBookingUsecase createBookingUsecase
    ) {
        this.createBookingUsecase = createBookingUsecase;
    }

	@PostMapping("/")
    public String PostBooking(
        @ModelAttribute PostBookingControllerRequestBody body
    ) {
        
        BookingDraft bookingDraft = new BookingDraft(
            body.checkInDate(),
            body.checkOutDate(),
            body.nightStays(),
            body.name(),
            body.creditCardNumber()
        );
        createBookingUsecase.execute(bookingDraft);
        log.info("Booking successfully created for name={}", body.name());
        return "confirmation";
    }
}