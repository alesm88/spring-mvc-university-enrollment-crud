package com.bolsadeideas.springboot.app.models.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolsadeideas.springboot.app.models.entity.Professor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@SpringBootTest
public class ProfessorDaoImplTest {

	@Mock
	private EntityManager em;
	
	@Mock
	private TypedQuery<Professor> query;
	
	@InjectMocks
	private ProfessorDaoImpl professorDao;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
    public void testFindAll() {
        // Arrange
        List<Professor> expectedProfessors = Arrays.asList(
        		new Professor(1, 33333, "Pepe", "Fonewman", true),
        		new Professor(2, 44444, "Max", "Power", false));
        
        when(em.createQuery("from Professor", Professor.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedProfessors);

        // Act
        List<Professor> result = professorDao.findAll();

        // Assert
        assertEquals(expectedProfessors, result);
        verify(em).createQuery("from Professor", Professor.class);
        verify(query).getResultList();
    }
	
	@Test
    public void testFindAllActive() {
        // Arrange
        List<Professor> expectedProfessors = Arrays.asList(
        		new Professor(1, 33333, "Pepe", "Fonewman", true),
        		new Professor(2, 44444, "Max", "Power", false));
        when(em.createQuery("select p from Professor p where p.active=true", Professor.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedProfessors);

        // Act
        List<Professor> result = professorDao.findAllActive();

        // Assert
        assertEquals(expectedProfessors, result);
        verify(em).createQuery("select p from Professor p where p.active=true", Professor.class);
        verify(query).getResultList();
    }

    @Test
    public void testSave_Insert() {
        // Arrange
        Professor professor = new Professor();
        professor.setId(null); // Simulate a new entity

        // Act
        professorDao.save(professor);

        // Assert
        verify(em).persist(professor);
    }

    @Test
    public void testSave_Update() {
        // Arrange
        Professor professor = new Professor();
        professor.setId(1); // Simulate an existing entity

        // Act
        professorDao.save(professor);

        // Assert
        verify(em).merge(professor);
    }

    @Test
    public void testFindOne() {
        // Arrange
        Long professorId = 1L;
        Professor expectedProfessor = new Professor(2, 44444, "Max", "Power", false);
        when(em.find(Professor.class, professorId)).thenReturn(expectedProfessor);

        // Act
        Professor result = professorDao.findOne(professorId);

        // Assert
        assertEquals(expectedProfessor, result);
        verify(em).find(Professor.class, professorId);
    }

    @Test
    public void testDelete() {
        // Arrange
        Long professorId = 1L;
        Professor professor = new Professor();
        when(em.find(Professor.class, professorId)).thenReturn(professor);

        // Act
        professorDao.delete(professorId);

        // Assert
        verify(em).remove(professor);
        verify(em).find(Professor.class, professorId);
    }
}
