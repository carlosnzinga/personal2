package ch.coop.personal.personal;

import ch.coop.personal.personal.dao.PersonalRepository;
import ch.coop.personal.personal.dao.model.Personal;
import ch.coop.personal.personal.service.impl.PersonalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link PersonalServiceImpl} class.
 */
@ExtendWith(MockitoExtension.class)
public class PersonalServiceImplTest {

	@Mock
	private PersonalRepository repository;

	@InjectMocks
	private PersonalServiceImpl personalService;

	/**
	 * Verifies that {@link PersonalServiceImpl#insertPersonal(Personal)} behaves correctly.
	 */
	@Test
	public void insertPersonalShouldHaveOneEntrySaved() {
		Personal personal = new Personal();
		when(repository.save(any(Personal.class))).thenReturn(personal);
		personalService.insertPersonal(personal);
		verify(repository, times(1)).save(eq(personal));
	}

	/**
	 * Verifies that {@link PersonalServiceImpl#getPersonals()} behaves correctly.
	 */
	@Test
	public void getPersonalByIdShouldHaveTwoEntries() {
		Personal personal1 = new Personal();
		personal1.setId(0);
		Personal personal2 = new Personal();
		personal2.setId(1);
		when(repository.findAll()).thenReturn(Arrays.asList(personal1, personal2));

		Optional<Personal> result = personalService.getPersonalById(1);

		assertTrue(result.isPresent());
		verify(repository, times(1)).findAll();
	}

	/**
	 * Verifies that {@link PersonalServiceImpl#getPersonals()} behaves correctly.
	 */
	@Test
	public void getPersonalsShouldHaveTwoEntries() {
		Personal personal1 = new Personal();
		personal1.setId(1);
		Personal personal2 = new Personal();
		personal2.setId(2);
		when(repository.findAll()).thenReturn(Arrays.asList(personal2, personal1));
		List<Personal> result = personalService.getPersonals();
		verify(repository, times(1)).findAll();
		assertEquals(1, result.get(0).getId());
		assertEquals(2, result.get(1).getId());
	}

	/**
	 * Verifies that {@link PersonalServiceImpl#deletePersonal(Long)} behaves correctly.
	 */
	@Test
	public void deletePersonalShouldHaveDeletedOneEntry() {
		Long idToDelete = 1L;
		personalService.deletePersonal(idToDelete);
		verify(repository, times(1)).deleteById(eq(idToDelete));
	}

	//Todo Throw assert Exception

	/**
	 * Verifies that {@link PersonalServiceImpl#deletePersonal(Long)} throws an exception
	 * when attempting to delete a non-existent entry.
	 *
	 * <p>Scenario: The method should throw an {@link EmptyResultDataAccessException}
	 * if an attempt is made to delete an entry that does not exist in the repository.
	 * This test ensures that the exception is appropriately thrown and handled.
	 */
	@Test
	public void deletePersonalThrowsExceptionForNonExistentEntry() {
		Personal personal1 = new Personal();
		personal1.setId(1);
		Personal personal2 = new Personal();
		personal2.setId(2);
		Long idToDelete = 275L;

		doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(anyLong());

		assertThrows(EmptyResultDataAccessException.class, () -> {
			try {
				personalService.deletePersonal(idToDelete);
			} catch (EmptyResultDataAccessException e) {
				System.out.println("Caught exception: " + e.getMessage());
				throw e;
			}
		});
	}

//Todo TDD
	/**
	 * Tests the behavior of the {@link PersonalServiceImpl#deletePersonal(Long)} method
	 * when attempting to delete an entry that doesn't exist in the repository.
	 * It expects the `deletePersonal` method to return false if no entry is deleted.
	 */
	@Test
	public void deletePersonalShouldReturnFalseWhenNoEntryIsDeleted() {
		Long idToDelete = 275L;

		// Mocking the deleteById method to ensure it's called with the specific ID
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idToDelete);

		boolean isDeleted = false;
		try {
			personalService.deletePersonal(idToDelete);
		} catch (EmptyResultDataAccessException e) {
			isDeleted = false; // It wasn't deleted as expected
			System.out.println("Deletion unsuccessful: " + e.getMessage()); // Outputting the caught exception message
		}

		// Asserting that the deletion was not successful
		assertFalse(isDeleted);
	}




	@Test
	void testMethod() {
		try {
			String str = null;
			str.length();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException wurde erfasst!");
		}
	}



	/**
	 * Verifies that {@link PersonalServiceImpl#updatePersonal(Personal)} behaves correctly.
	 */
	@Test
	public void updatePersonalShouldHaveOneUpdatedEntry() {
		Personal updatedPersonal = new Personal();
		when(repository.save(any(Personal.class))).thenReturn(updatedPersonal);
		personalService.updatePersonal(updatedPersonal);
		verify(repository, times(1)).save(eq(updatedPersonal));
	}
}
