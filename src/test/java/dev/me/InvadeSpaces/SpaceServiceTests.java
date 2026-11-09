package dev.me.InvadeSpaces;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
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
			new Space("Test Space", 10),
            new Space("Yet another test space", 11)
		));

		// Act
        var spaces = spaceService.findAll();

		// Assert
        Assertions.assertNotNull(spaces);
        Assertions.assertEquals(2, spaces.size());
        Assertions.assertEquals(10, spaces.get(0).getCapacity());
        verify(spaceRepository).findAll();
	}
}
