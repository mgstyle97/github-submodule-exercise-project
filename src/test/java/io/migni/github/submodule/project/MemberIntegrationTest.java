package io.migni.github.submodule.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.migni.github.submodule.project.dto.SaveMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class MemberIntegrationTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private ObjectMapper mapper;

    @DisplayName("테스트: 사용자 저장")
    @Test
    void save_member() throws Exception {
        // given
        final SaveMemberRequest request = new SaveMemberRequest("test");

        final String content = mapper.writeValueAsString(request);

        // when
        final ResultActions resultActions = client.perform(
            post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andDo(print());

        // then
        resultActions.andExpect(status().isCreated());
    }

}
