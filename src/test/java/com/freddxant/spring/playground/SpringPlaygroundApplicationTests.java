package com.freddxant.spring.playground;

import com.freddxant.spring.playground.model.dto.EmployeeDto;
import com.freddxant.spring.playground.model.dto.GithubUserDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Employee;
import com.freddxant.spring.playground.model.entity.User;
import com.freddxant.spring.playground.repository.UserRepository;
import com.freddxant.spring.playground.service.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
		user.setEmail("user@email.com");
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

	@Test
	void testGson() {
		// create new object
		EmployeeDto dto = new EmployeeDto();
		dto.setFirstName("Freddy");
		dto.setLastName("");
		dto.setAddress("Tangerang, Banten");
		dto.setGender("Male");
		dto.setPosition("Middle Software Engineer");

		// convert object to string json
		Gson gson = new Gson();
		String jsonEmployee = gson.toJson(dto);
		System.out.println("jsonEmployee: " + jsonEmployee);

		// convert string json to object
		Gson gsonBuilder = new GsonBuilder().create();
		EmployeeDto employee = gsonBuilder.fromJson(jsonEmployee, EmployeeDto.class);
		System.out.println(employee.toString());

		// deserialize data json from webservice
		try {
			String jsonWeb = getJson("https://api.github.com/users/petanikode");
			GithubUserDto githubUserDto = gson.fromJson(jsonWeb, GithubUserDto.class);

			System.out.println("Deserialize");
			System.out.println(githubUserDto.toString());
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	private String getJson(String url) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		request.addHeader("User-Agent", "Mozilla/5.0");
		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL: " + url);
		System.out.println("Response Code: "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

}
