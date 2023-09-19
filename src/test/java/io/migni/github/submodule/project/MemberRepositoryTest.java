package io.migni.github.submodule.project;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("테스트: 사용자 저장")
    @Test
    @Transactional(propagation = Propagation.NEVER)
    void save_member() {
        // given
        final Member member = new Member("test");

        // when
        final var result = memberRepository.save(member);
        final var find = memberRepository.findById(member.id())
            .orElseThrow(EntityNotFoundException::new);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(find).isNotNull();
            softAssertions.assertThat(find.id()).isEqualTo(member.id());
        });
    }

}