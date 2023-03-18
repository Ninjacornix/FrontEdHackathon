package com.example.backend.controller;

import com.example.backend.domain.Member;
import com.example.backend.request.UpdateMemberRequest;
import com.example.backend.result.ActionResult;
import com.example.backend.result.DataResult;
import com.example.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @DeleteMapping
    public ResponseEntity<ActionResult> deleteMember(@RequestParam Long id) {
        return memberService.deleteMember(id).intoResponseEntity();
    }

    @GetMapping
    public ResponseEntity<DataResult<List<Member>>> getMembers() {
        return memberService.getMembers().intoResponseEntity();
    }

    @PutMapping
    public ResponseEntity<ActionResult> updateMember(UpdateMemberRequest request) {
        return memberService.updateMember(request).intoResponseEntity();
    }

    @GetMapping("/member")
    public ResponseEntity<DataResult<Member>> getMember(@RequestParam Long id) {
        return memberService.getMember(id).intoResponseEntity();
    }



}
