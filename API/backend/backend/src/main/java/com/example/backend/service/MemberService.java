package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
import com.example.backend.requests.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;



    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }




    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public void updateMember(UpdateMemberRequest request) {

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
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).get();
    }
}
