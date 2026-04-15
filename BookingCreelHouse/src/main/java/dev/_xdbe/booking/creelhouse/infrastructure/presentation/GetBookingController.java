package dev._xdbe.booking.creelhouse.infrastructure.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GetBookingController {

	private static final Logger log = LoggerFactory.getLogger(GetBookingController.class);

	@GetMapping("/")
	public String getform(Model model) {
		log.info("Booking request");
		return "booking";
	}
}