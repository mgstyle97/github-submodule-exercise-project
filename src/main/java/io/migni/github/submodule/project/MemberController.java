package io.migni.github.submodule.project;

import io.migni.github.submodule.project.dto.SaveMemberRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody final SaveMemberRequest request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.memberService.save(request));
    }

}
