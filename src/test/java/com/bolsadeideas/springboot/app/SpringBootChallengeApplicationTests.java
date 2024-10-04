package com.bolsadeideas.springboot.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=none", // Disabled the auto-configuration of JPA
		"spring.datasource.url=jdbc:h2:mem:testdb", // Use H2 in memory if necessary
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=password"
})
class SpringBootChallengeApplicationTests {

	@Test
	void contextLoads() {
	}

}
