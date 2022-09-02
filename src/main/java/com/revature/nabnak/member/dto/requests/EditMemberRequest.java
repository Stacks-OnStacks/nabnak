package com.revature.nabnak.member.dto.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.revature.nabnak.util.web.auth.DTO.request.EditResourceRequests;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditMemberRequest extends EditResourceRequests {

    private String fullName;
    @JsonAlias({"eMaIl","EMAIL", "e-mail"})
    private String email; //pulling the id from the parent class
    private String password;

}
