package com.bolsadeideas.springboot.app.models.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolsadeideas.springboot.app.models.entity.Professor;
import com.bolsadeideas.springboot.app.models.entity.ProfessorDto;

@SpringBootTest
class ProfessorServiceImplTest {
	
	@Autowired
	private IProfessorService professorService;
	
	ProfessorDto p1;
	ProfessorDto p2;
	ProfessorDto p3;
	Professor t1;
	
	@BeforeEach
	void initMethodTest() {
		p1 = new ProfessorDto(3, 22467458, "Grant, Javier", true);
		p2 = new ProfessorDto(4, 18724375, "Cluster, Daniel", true);
		p3 = new ProfessorDto(5, 13598642, "Marchand, Susana", true);
		t1 = new Professor();
	}
	
	@Test
	@DisplayName("test get all professor active")
	void testProfessorActive() {
		
		List<ProfessorDto> professorsActive = professorService.findAllProfessorsActive();
		
		List<ProfessorDto> professorsExpected = new ArrayList<>();
		professorsExpected.add(p1);
		professorsExpected.add(p2);
		professorsExpected.add(p3);
		
		assertEquals(professorsExpected, professorsActive);
	}
	
	@Test
	@DisplayName("find a specific professor")
	void testFindProfessor() {
		t1 = professorService.findProfessor(3L);
		assertAll(
				() -> assertTrue(t1.getActive()),
				() -> assertNotNull(t1.getCardNumber()),
				() -> assertEquals("Grant", t1.getSurname()),
				() -> assertEquals("Javier", t1.getName())
		);
	}
	
}
