package com.freddxant.spring.playground;

import com.freddxant.spring.playground.model.dto.EmployeeDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.model.entity.User;
import com.freddxant.spring.playground.repository.UserRepository;
import com.freddxant.spring.playground.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class SpringPlaygroundApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ModelMapper modelMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("12345678");

		User user = new User();
		user.setEmail("testing@email.com");
		user.setPassword(password);

		User savedUser = userRepository.save(user);

		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	void testSaveEmployeeJdbcTemplate() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setFirstName("Rezki Izzati");
		employeeDto.setLastName("Afiah Rahman");
		employeeDto.setAddress("Makassar, Sulsel");
		employeeDto.setGender("Female");
		employeeDto.setPosition("Middle Software Engineer");

		Employee employee = modelMapper.map(employeeDto, Employee.class);
		ResponseDto responseDto = employeeService.saveEmployeeJdbcTemplate(employee);
		log.info("success[{}], code[{}], message[{}], data[{}]",
				responseDto.getSuccess(),
				responseDto.getCode(),
				responseDto.getMessage(),
				responseDto.getData().toString()
		);
	}

}
