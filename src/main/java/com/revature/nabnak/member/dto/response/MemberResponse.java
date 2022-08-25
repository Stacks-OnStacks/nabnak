package com.revature.nabnak.member.dto.response;

import com.revature.nabnak.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponse {

    private String memberId;
    private String fullName;
    private String email;
    private long experienceMonths;

    public MemberResponse(Member member){
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.fullName = member.getFullName();
        this.experienceMonths = member.getExperienceMonths();
    }

}
