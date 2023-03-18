package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
import com.example.backend.requests.UpdateMemberRequest;
import com.example.backend.result.ActionResult;
import com.example.backend.result.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;



    public ActionResult deleteMember(Long id) {
        memberRepository.deleteById(id);
        return new ActionResult(true, "Member deleted successfully");
    }




    public DataResult<List<Member>> getMembers() {
        return new DataResult<>(true, "Members found successfully", memberRepository.findAll());
    }

    public ActionResult updateMember(UpdateMemberRequest request) {

        Member member = memberRepository.findById(request.getId()).get();

        if(request.getFirstName() != null){
            member.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null){
            member.setLastName(request.getLastName());
        }
        if(request.getEmail() != null){
            member.setEmail(request.getEmail());
        }
        if(request.getPassword() != null){
            member.setPassword(request.getPassword());
        }
        if(request.getRole() != null){
            member.setRole(request.getRole());
        }
        if(request.getPhoneNumber() != null){
            member.setPhoneNumber(request.getPhoneNumber());
        }
        memberRepository.save(member);
        return new ActionResult(true, "Member updated successfully");
    }

    public DataResult<Member> getMember(Long id) {
        return new DataResult<>(true, "Member found successfully", memberRepository.findById(id).get());
    }
}
