package dev._xdbe.booking.creelhouse.infrastructure.presentation;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import dev._xdbe.booking.creelhouse.domain.entity.Booking;
import dev._xdbe.booking.creelhouse.usecase.ListBookingUsecase;

@Controller
public class DashboardController {

	private final ListBookingUsecase listBookingUsecase;

	public DashboardController(ListBookingUsecase listBookingUsecase) {
		this.listBookingUsecase = listBookingUsecase;
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
        List<Booking> bookingList = listBookingUsecase.execute();
        model.addAttribute("bookingList", bookingList);
		return "dashboard";
	}
}