package io.migni.github.submodule.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.migni.github.submodule.project.dto.SaveMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MemberService memberService;

    @DisplayName("테스트: 사용자 저장")
    @Test
    void save_member() throws Exception {
        // given
        final SaveMemberRequest request = new SaveMemberRequest("test");

        final String content = mapper.writeValueAsString(request);

        given(memberService.save(any(SaveMemberRequest.class))).willReturn(1L);

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