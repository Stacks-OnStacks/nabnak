package com.revature.nabnak.menus;

import com.revature.nabnak.models.Member;
import com.revature.nabnak.services.MemberService;
import com.revature.nabnak.util.CustomLogger;
import com.revature.nabnak.util.MenuRouter;
import com.revature.nabnak.util.exceptions.UnauthorizedException;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginMenu extends Menu{

    private final MemberService memberService;
    CustomLogger customLogger = CustomLogger.getLogger(true);

    public LoginMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Login", "/login", terminalReader, menuRouter);
        this.memberService = memberService;
    }

    @Override
    public void render() throws IOException {
        System.out.print("Please enter email: \n>"); // \n is a new line character, aka return or enter
        String email = terminalReader.readLine();

        System.out.print("Please enter your password: \n>");
        String password = terminalReader.readLine();

        try {
            Member member = memberService.login(email, password);
            System.out.println(member);
            if (member == null) {
                throw new UnauthorizedException("Invalid information provided. Please try again");
            }
            menuRouter.transfer("/dashboard");
        } catch (UnauthorizedException e){
            customLogger.warn(e.getMessage());
            e.printStackTrace();
        }
    }
}
