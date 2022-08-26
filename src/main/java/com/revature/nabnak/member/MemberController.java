package com.revature.nabnak.member;

import com.revature.nabnak.member.dto.requests.EditMemberRequest;
import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import com.revature.nabnak.member.dto.response.MemberResponse;
import com.revature.nabnak.util.exceptions.ResourceNotFoundException;
import com.revature.nabnak.util.web.Secured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController // RestController is used so we don't have to repeat 1000 @ResponseBody infront of every return type.
// We can included the @ResponseBody and it'll work just fine.
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    @Secured(isAdmin = true)
    public List<MemberResponse> findAll(){
        return memberService.readAll();
    }

    @GetMapping("/{id}")
    public MemberResponse findById(@PathVariable String id){
        return memberService.findById(id);
    }

    @GetMapping("/query")
    public MemberResponse findByIdQuery(@RequestParam String id){
        return memberService.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberResponse register(@RequestBody @Valid NewRegistrationRequest newRegistrationRequest){
        return memberService.registerMember(newRegistrationRequest);
    }

    @PutMapping
    public String update(@RequestBody EditMemberRequest editMemberRequest){
        memberService.update(editMemberRequest);
        return "The update was applied to the member";
    }

}
