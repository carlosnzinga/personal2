/*
package ch.coop.personal.personal;

import ch.coop.personal.personal.controller.PersonalController;
import ch.coop.personal.personal.dao.model.Personal;
import ch.coop.personal.personal.service.impl.PersonalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalControllerTest {

	@Mock
	private PersonalServiceImpl personalService;

	@InjectMocks
	private PersonalController personalController;

	@Mock
	private Model model;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize mocks

		String query = "Miguel";

		Personal person1 = new Personal();
		person1.setId(1);
		person1.setName("Miguel");

		Personal person2 = new Personal();
		person2.setId(2);
		person2.setName("Carckos");

		List<Personal> searchResults = Arrays.asList(
				person1,
				person2);

		Mockito.when(personalService.searchPersonal(query)).thenReturn(searchResults);
	}

	@Test
	public void testSearchPersonal() {
		String viewName = personalController.searchPersonal("Miguel", model);

		Personal person1 = new Personal();
		person1.setId(1);
		person1.setName("Miguel");

		Personal person2 = new Personal();
		person2.setId(2);
		person2.setName("Carckos");



		Mockito.verify(model).addAttribute("searchResults", Arrays.asList(
				person1,
				person2));


		assertEquals("searchResults", viewName);
	}
}
*/
