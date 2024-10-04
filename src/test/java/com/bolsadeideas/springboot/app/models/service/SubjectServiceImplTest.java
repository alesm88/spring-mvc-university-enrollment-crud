package com.bolsadeideas.springboot.app.models.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolsadeideas.springboot.app.models.dao.ISubjectDao;
import com.bolsadeideas.springboot.app.models.entity.Subject;

@SpringBootTest
class SubjectServiceImplTest {

	@Autowired
	private ISubjectDao subjectDao;
	
	List<Subject> subjects;
	
	@BeforeEach
	void initMethodTest() {
		subjects = new ArrayList<>();
	}
	
	@Test
	@DisplayName("test get all subjects ordered")
	void testCheckSubjectsOrdered() {
		subjects = subjectDao.findAllOrdered();
		
		List<String> subjectNames = subjects.stream()
											.map(Subject::getName)
											.toList(); // Get all the names
		List<String> namesOrdered = subjectNames.stream()
											.sorted(Comparator.naturalOrder())
											.toList(); // New list ordered
		assertThat(subjectNames).isEqualTo(namesOrdered);
		// assertEquals(subjectNames, namesOrdered); // I can use also this instead of assertThat & isEqualTo
		// assertThat allows multiple checks and comparisons to be chained together in a very fluid manner, making it more flexible for more complex expressions
	}
	
	@Test
	@DisplayName("test verify subjects have quota")
	void testSubjectsHasQuota() {
		subjects = subjectDao.subjectsByStudentNotEnrolAndQuota(2);
		assertThat(subjects)
        	.allSatisfy(subject -> assertThat(subject.getMaxQuota()).isGreaterThan(0));
		/*for (Subject subject : subjects) {
            assertTrue(subject.getMaxQuota() > 0);
        }*/
	}
	
}
