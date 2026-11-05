package dev.me.InvadeSpaces;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.me.InvadeSpaces.entity.Space;
import dev.me.InvadeSpaces.repository.SpaceRepository;
import dev.me.InvadeSpaces.service.SpaceService;

@ExtendWith(MockitoExtension.class)
public class SpaceServiceTests {

	@Mock
	private SpaceRepository spaceRepository;

	@InjectMocks
	private SpaceService spaceService;

	@Test
	public void testFindAll() {
		// Arrange
		when(spaceRepository.findAll()).thenReturn(List.of(
			new Space("Test Space", 10)
		));

		// Act


		// Assert
	}
}
