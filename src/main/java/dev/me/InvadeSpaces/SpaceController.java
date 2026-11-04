package dev.me.InvadeSpaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

	private final SpaceService spaceService;

	public record SpaceResponse(String message, Space space) {}

	public SpaceController(SpaceService spaceService) {
		this.spaceService = spaceService;
	}

	@GetMapping("")
	public ResponseEntity<List<Space>> findAll() {
		System.out.println(spaceService.findAll().toString());
		return ResponseEntity.ok(spaceService.findAll());
	}

    @GetMapping("/name")
    public ResponseEntity<List<Space>> findByName(@RequestParam("n") String name) {
        return ResponseEntity.ok(spaceService.findByName(name));
    }

	@GetMapping("/add")
	public ResponseEntity<SpaceResponse> addSpace(
		@RequestParam("n") String name,
		@RequestParam("c") int capacity) {

		Space newSpace = spaceService.addSpace(name, capacity);
		var response = new SpaceResponse("Given space was created", newSpace);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/delete")
	public ResponseEntity<SpaceResponse> deleteSpace(@RequestParam Long id) {
		Space deletedSpace = spaceService.deleteSpace(id);
		HttpStatus status;
		SpaceResponse response;

		if (deletedSpace == null) {
			response = new SpaceResponse("No space with the given id was found", null);
			status = HttpStatus.NOT_FOUND;
		} else {
			response = new SpaceResponse("Given space was deleted", deletedSpace);
			status = HttpStatus.OK;
		}

		return ResponseEntity.status(status).body(response);
	}
}
