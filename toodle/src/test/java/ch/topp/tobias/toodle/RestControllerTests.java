package ch.topp.tobias.toodle;

import ch.topp.tobias.toodle.Tag.Tag;
import ch.topp.tobias.toodle.Tag.TagRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TagRepository tagRepository;

    @BeforeAll
    void setup() {
        Tag tag1 = new Tag("Tag A", "Description for Tag A");
        Tag tag2 = new Tag("Tag B", "Description for Tag B");
        tagRepository.save(tag1);
        tagRepository.save(tag2);
    }

    @Test
    @Order(1)
    void testGetTag() throws Exception {
        String accessToken = obtainAccessToken();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tags")
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Tag A")));
    }

    private String obtainAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "client_id=toodle&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=1234";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(response.getBody()).get("access_token").toString();
    }
}
