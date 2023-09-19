package io.migni.github.submodule.project;

import io.migni.github.submodule.project.dto.SaveMemberRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long save(final SaveMemberRequest request) {
        final Member member = new Member(request.name());

        return memberRepository.save(member).id();
    }

}
