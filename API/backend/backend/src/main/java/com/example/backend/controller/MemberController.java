package com.example.backend.controller;

import com.example.backend.domain.Member;
import com.example.backend.requests.UpdateMemberRequest;
import com.example.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @DeleteMapping
    public void deleteMember(@RequestParam Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    @PutMapping
    public void updateMember(UpdateMemberRequest request) {
        memberService.updateMember(request);
    }

    @GetMapping("/member")
    public Member getMember(@RequestParam Long id) {
        return memberService.getMember(id);
    }



}
