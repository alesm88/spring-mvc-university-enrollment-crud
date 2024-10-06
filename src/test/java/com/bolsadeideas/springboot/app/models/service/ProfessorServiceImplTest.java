package com.bolsadeideas.springboot.app.models.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolsadeideas.springboot.app.models.dao.IProfessorDao;
import com.bolsadeideas.springboot.app.models.entity.Professor;
import com.bolsadeideas.springboot.app.models.entity.ProfessorDto;

@SpringBootTest
class ProfessorServiceImplTest {
	
	@Mock
	private IProfessorDao professorDao;
	
	@InjectMocks
	private ProfessorServiceImpl professorService;
	
	List<Professor> mockProfessor = new ArrayList<>();
	Professor t1Mock;
	Professor t1;
	
	@BeforeEach
	void initMethodTest() {
		MockitoAnnotations.openMocks(this);
		
		mockProfessor.add(new Professor(1, 22467458, "Javier", "Grant", true));
		mockProfessor.add(new Professor(2, 18724375, "Daniel", "Cluster", true));
		mockProfessor.add(new Professor(3, 13598642, "Susana", "Marchand", true));
		
		t1Mock = new Professor(1, 22467458, "Javier", "Grant", true);
	}
	
	@Test
	@DisplayName("test get all professor active")
	void testProfessorActive() {
		
		when(professorDao.findAllActive()).thenReturn(mockProfessor);
		
		List<ProfessorDto> professorsActive = professorService.findAllProfessorsActive();
		
		List<ProfessorDto> professorsExpected = new ArrayList<>();
		professorsExpected.add(new ProfessorDto(1, 22467458, "Grant, Javier", true));
		professorsExpected.add(new ProfessorDto(2, 18724375, "Cluster, Daniel", true));
		professorsExpected.add(new ProfessorDto(3, 13598642, "Marchand, Susana", true));
		
		assertEquals(professorsExpected, professorsActive);
	}
	
	@Test
	@DisplayName("find a specific professor")
	void testFindProfessor() {
		
		when(professorDao.findOne(1L)).thenReturn(t1Mock);
		
		t1 = professorService.findProfessor(1L);
		assertAll(
				() -> assertTrue(t1.getActive()),
				() -> assertNotNull(t1.getCardNumber()),
				() -> assertEquals("Grant", t1.getSurname()),
				() -> assertEquals("Javier", t1.getName())
		);
	}
	
}
