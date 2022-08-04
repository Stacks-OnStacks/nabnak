package com.revature.nabnak.menus;

import com.revature.nabnak.models.Member;
import com.revature.nabnak.services.MemberService;
import com.revature.nabnak.util.CustomLogger;
import com.revature.nabnak.util.MenuRouter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class RegisterMenu extends Menu{
    CustomLogger customLogger = CustomLogger.getLogger(true);
    private final MemberService memberService;

    public RegisterMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Register", "/register", terminalReader, menuRouter);
        this.memberService = memberService;
    }

    @Override
    public void render() throws IOException{
        System.out.print("Please enter email: \n>"); // \n is a new line character, aka return or enter
        String email = terminalReader.readLine();

        System.out.print("Please enter your first and last name: \n>");
        String fullName = terminalReader.readLine();

        System.out.print("Please enter your experience in months: \n>");
        int experienceMonths = 0;
        try {
            experienceMonths = Integer.parseInt(terminalReader.readLine());
        } catch (NumberFormatException e){
            customLogger.warn("Invalid number enter please try the registration again");
            menuRouter.transfer("/register");
        }
        System.out.print("Please enter your password: \n>");
        String password = terminalReader.readLine();


        // TODO: What on god's green earth is LocalDateTime?
        java.sql.Date registrationDate = new java.sql.Date(new Date().getTime());
        System.out.println(registrationDate);

        // This is a form of logging
        // System.out.printf("New user has registerd under \n User:%s,%s,%s,%s", email, fullName, experienceMonths, registrationDate).println(); //printf is a formatter

        Member newMember = new Member(email, fullName, experienceMonths, registrationDate, password);
        //TODO: LOGG INFO AS ENTER customerLogger.log(arguments)
        Member member = memberService.registerMember(newMember);
        // What value is our session? null
        if(member == null){
            System.out.println("Registration failed, please try again");
            menuRouter.transfer("/register");
        } else {
            memberService.login(member.getEmail(), member.getPassword());
            customLogger.info("Navigating to dashboard for " + newMember.getEmail());
            menuRouter.transfer("/dashboard");
        }
    }
}
