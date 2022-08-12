package com.revature.nabnak.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import com.revature.nabnak.util.interfaces.Authable;
import com.revature.nabnak.util.web.DTO.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

//@WebServlet("/member")
public class MemberServlet extends HttpServlet implements Authable {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    // Because we are perfoming dependency injection, we can no longer use the @WebServlet above. DONT FORGET
    public MemberServlet(MemberService memberService, ObjectMapper objectMapper){
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO: Issue of there being more than just a doGet for reading all, things like find a member by ID
        String email = req.getParameter("email"); // every parameter is read as a STRING
        Member authMember = (Member) req.getSession().getAttribute("authMember"); // cast the returned object to a member

        if(authMember != null) {
            System.out.println(authMember.getEmail() + " " + authMember.getFullName());
        } else {
            resp.setStatus(403); // hello status codes
        }

        // qwhen using query params and specifying endpoints to hit parituclar crud operations, make sure you go from most specific to least
//        if (email != null && password != null){ // this has security risks with ap assword in a url
//            Member member = memberService.login(email, password);
//            String payload = objectMapper.writeValueAsString(member);
//            resp.getWriter().write(payload);
//        } else
        if(email != null) {

            Member member = memberService.findById(email);

            String payloadID = objectMapper.writeValueAsString(member);

            resp.getWriter().write(payloadID);
        } else {
           if(!checkAuth(req,resp)) return;
            List<Member> members = memberService.readAll();

            String payload = objectMapper.writeValueAsString(members); // mapper parsing from Java Object to JSON

            resp.getWriter().write(payload);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DO NOT DO LOGIN INSIDE OF YOUR MEMBER SERVLET
//        LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class); // this provides the body from the request as a JSON, leveraging Reflections
//
//        Member member = memberService.login(loginCreds.getEmail(), loginCreds.getPassword());
//
//        resp.getWriter().write("Welcome back to nabnak " + member.getFullName());
        Member member = objectMapper.readValue(req.getInputStream(), Member.class);

        // what can we leverage to record our current date
        member.setRegistrationDate(new Date(System.currentTimeMillis()));

        try {
            Member newMember = memberService.registerMember(member);

            String payload = objectMapper.writeValueAsString(newMember);

            resp.getWriter().write(payload);
            resp.setStatus(201);
        } catch (InvalidUserInputException | ResourcePersistanceException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = objectMapper.readValue(req.getInputStream(), Member.class);

        try {
            memberService.update(member);
            resp.getWriter().write("Member has been successfully updated");
        } catch (InvalidUserInputException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp)) return;
        String email = req.getParameter("email");
        if(email != null){
            memberService.remove(email);
            resp.getWriter().write("Member with " + email + " has been deleted");
        } else {
            resp.getWriter().write("This request requires an email parameter in the path ?email=example@mail.com");
            resp.setStatus(400);
        }
    }


}
