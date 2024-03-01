package com.tech.lab4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.lab4.payload.requests.LoginRequest;
import com.tech.lab4.payload.requests.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @LocalServerPort
    private int port;

    @Test
    public void testRegisterUser() throws Exception {
        // Create a SignupRequest with valid data
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("pus");
        signupRequest.setBirthdate(LocalDate.of(1990, 1, 1));
        signupRequest.setPassword("password");
        Set<String> roles = new HashSet<>(Collections.singletonList("admin"));
        signupRequest.setRole(roles);

        // Convert SignupRequest to JSON
        String jsonRequest = objectMapper.writeValueAsString(signupRequest);

        // Perform the POST request
        ResultActions result = mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenticateUser() {
        // Замените значения username и password на ваши тестовые данные
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("pussy");
        loginRequest.setPassword("$2a$10$DkpMhuXx1GvJnqMm01MnOeyRWjS9uYPiQ9a2FyJn7u1fCZ77LdEsm");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/auth/signin",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Проверьте, что HTTP-код ответа соответствует ожидаемому
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Дополнительные проверки могут быть добавлены в зависимости от вашего функционала
        // Например, можно проверить токен в ответе или другие детали

        // Пример: Проверка наличия токена в ответе
        String responseBody = responseEntity.getBody();
        assertTrue(responseBody.contains("token"));
    }
}

