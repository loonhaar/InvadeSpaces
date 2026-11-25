package dev.me.InvadeSpaces.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.me.InvadeSpaces.entity.Reservation;
import dev.me.InvadeSpaces.service.ReservationService;

@RestController
@RequestMapping("/rsvs")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ResponseEntity<List<Reservation>> findAll() {
		return ResponseEntity.ok(reservationService.findAllWithSpaces());
	}

	@GetMapping("{id}")
	public ResponseEntity<Reservation> findById(@PathVariable Long id) {
		try {
            return ResponseEntity.ok(reservationService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
	}

	@PostMapping
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
		try {
			Reservation created = reservationService.addReservation(
				reservation.getId(),
				reservation.getStartTime(),
				reservation.getEndTime(),
				reservation.getSpace()
			);

            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Reservation> updateReservation(
		@PathVariable Long id,
		@RequestBody Reservation reservation) {
		try {
			Reservation updated = reservationService.updateReservation(
				id,
				reservation.getStartTime(),
				reservation.getEndTime(),
				reservation.getSpace()
			);

			return ResponseEntity.ok(updated);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(reservationService.deleteReservation(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
