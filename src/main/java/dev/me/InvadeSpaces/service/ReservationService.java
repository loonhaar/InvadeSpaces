package dev.me.InvadeSpaces.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.me.InvadeSpaces.entity.Reservation;
import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.repository.ReservationRepository;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;

    private final ZoneId TIME_ZONE = ZoneId.of("Europe/Stockholm");
    private final Long MIN_HOURS_AHEAD = 2L;
    private final Long MIN_DURATION = 1L;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public List<Reservation> findAllWithSpaces() {
		return reservationRepository.findAllSpaceEmbedding();
	}

	public Reservation findById(Long id) {
		return reservationRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No reservation found with given id"));
	}

	public Reservation addReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Space space) {
        validateReservationTimes(startTime, endTime);

		Reservation newReservation = new Reservation(id, startTime, endTime, space);
		reservationRepository.save(newReservation);
		return newReservation;
	}

	public Reservation updateReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Space space) {
        validateReservationTimes(startTime, endTime);

		Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No reservation found with given id"));

		reservationToUpdate.setStartTime(startTime);
		reservationToUpdate.setEndTime(endTime);
		reservationToUpdate.setSpace(space);
		reservationRepository.save(reservationToUpdate);

		return reservationToUpdate;
	}

	public Reservation deleteReservation(Long id) {
		Reservation reservationToDelete = reservationRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No reservation found with given id"));
		reservationRepository.deleteById(id);
		return reservationToDelete;
	}

    private void validateReservationTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Both start time and end time must not be null");
        }

        var minAllowedStart = ZonedDateTime.now(TIME_ZONE).plusHours(MIN_HOURS_AHEAD).toLocalDateTime();

        if (!startTime.isAfter(minAllowedStart)) {
            throw new IllegalArgumentException("Reservation must be at least " + MIN_HOURS_AHEAD + " hours form now");
        }

        if (!endTime.isAfter(startTime.plusHours(MIN_DURATION))) {
            throw new IllegalArgumentException(
                "End time of a reservation must be at least " + MIN_DURATION + " hours ahead of the start time"
            );
        }
    }
}
