package dev.me.InvadeSpaces;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/spaces")
public class SpaceController {

	private final SpaceService spaceService;

	public SpaceController(SpaceService spaceService) {
		this.spaceService = spaceService;
	}

	@GetMapping("")
	public ResponseEntity<List<Space>> findAll() {
		return ResponseEntity.ok(spaceService.findAll());
	}
}
