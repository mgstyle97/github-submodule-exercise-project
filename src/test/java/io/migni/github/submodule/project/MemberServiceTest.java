package io.migni.github.submodule.project;

import io.migni.github.submodule.project.dto.SaveMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private final Member member = mock();

    @DisplayName("테스트: 사용자 생성")
    @Test
    void save_member() {
        // given
        final SaveMemberRequest request = new SaveMemberRequest("test");

        given(memberRepository.save(any(Member.class))).willReturn(member);
        given(member.id()).willReturn(1L);

        // when
        final var result = memberService.save(request);

        // then
        assertThat(result).isEqualTo(1L);
    }

}