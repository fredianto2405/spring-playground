package com.freddxant.spring.playground;

import com.freddxant.spring.playground.model.dto.GithubUserDto;
import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.model.entity.Position;
import com.freddxant.spring.playground.model.entity.User;
import com.freddxant.spring.playground.repository.PositionRepository;
import com.freddxant.spring.playground.repository.UserRepository;
import com.freddxant.spring.playground.service.EmployeeService;
import com.freddxant.spring.playground.service.PositionService;
import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class SpringPlaygroundApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PositionService positionService;

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
	void testCreatePosition() {
		List<String> positionList = new ArrayList<>();
		positionList.add("Junior Software Engineer Grade I");
		positionList.add("Junior Software Engineer Grade II");
		positionList.add("Junior Software Engineer Grade III");
		positionList.add("Middle Software Engineer Grade I");
		positionList.add("Middle Software Engineer Grade II");
		positionList.add("Middle Software Engineer Grade III");
		positionList.add("Senior Software Engineer Grade I");
		positionList.add("Senior Software Engineer Grade II");
		positionList.add("Senior Software Engineer Grade III");
		positionList.add("Junior Quality Assurance Grade I");
		positionList.add("Junior Quality Assurance Grade II");
		positionList.add("Junior Quality Assurance Grade III");
		positionList.add("Middle Quality Assurance Grade I");
		positionList.add("Middle Quality Assurance Grade II");
		positionList.add("Middle Quality Assurance Grade III");
		positionList.add("Senior Quality Assurance Grade I");
		positionList.add("Senior Quality Assurance Grade II");
		positionList.add("Senior Quality Assurance Grade III");
		positionList.add("Junior Business Analyst Grade I");
		positionList.add("Junior Business Analyst Grade II");
		positionList.add("Junior Business Analyst Grade III");
		positionList.add("Middle Business Analyst Grade I");
		positionList.add("Middle Business Analyst Grade II");
		positionList.add("Middle Business Analyst Grade III");
		positionList.add("Senior Business Analyst Grade I");
		positionList.add("Senior Business Analyst Grade II");
		positionList.add("Senior Business Analyst Grade III");
		positionList.add("Technical Writer Grade I");
		positionList.add("Technical Writer Grade II");
		positionList.add("Technical Writer Grade III");

		for (String pl : positionList) {
			Position position = new Position();
			position.setPositionName(pl);

			try {
				positionRepository.save(position);
				System.out.println("Success insert position: " + pl);
			} catch (Exception e) {
				System.out.println("Error insert position: " + e.getMessage());
			}
		}
	}

	@Test
	void testFindAllPosition() {
		ResponseDto responseDto = positionService.findAllPosition();
		System.out.println(responseDto.toString());
	}

	@Test
	void testGson() {
		Gson gson = new Gson();

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
