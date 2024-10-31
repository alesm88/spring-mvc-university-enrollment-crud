package com.bolsadeideas.springboot.app.models.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.bolsadeideas.springboot.app.models.dao.ISubjectDao;
import com.bolsadeideas.springboot.app.models.entity.Subject;

@SpringBootTest
class SubjectServiceImplTest {

	@Mock
	private ISubjectDao subjectDao;
	
	@InjectMocks
	private SubjectServiceImpl subjectService;
	
	List<Subject> subjectsMock = new ArrayList<>();
	List<Subject> subjects;
	
	@BeforeEach
	void initMethodTest() {
		MockitoAnnotations.openMocks(this);
		
		subjectsMock.add(new Subject(1, "Programming", "Basic coding", "Mon 08:00", 0, 10, null));
		subjectsMock.add(new Subject(2, "Mathematics", "Trigonometry & Logarithms", "Fri 08:30", 0, 15, null));
		subjectsMock.add(new Subject(3, "English", "Communication in english", "Tue 10:00", 0, 5, null));
	}
	
	@Test
	@DisplayName("test verify subjects have quota")
	void testSubjectsHasQuota() {
		
		when(subjectDao.subjectsByStudentNotEnrolAndQuota(1)).thenReturn(subjectsMock);
		
		subjects = subjectDao.subjectsByStudentNotEnrolAndQuota(1);
		assertThat(subjects)
        	.allSatisfy(subject -> assertThat(subject.getMaxQuota() - subject.getEnrolled()).isGreaterThan(0));
		/*for (Subject subject : subjects) {
            assertTrue((subject.getMaxQuota() - subject.getEnrolled()) > 0);
        }*/
	}
	
}
