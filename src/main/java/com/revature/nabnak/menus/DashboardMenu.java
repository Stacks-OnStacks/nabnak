package com.revature.nabnak.menus;

import com.revature.nabnak.services.MemberService;
import com.revature.nabnak.util.CustomLogger;
import com.revature.nabnak.util.MenuRouter;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.nabnak.util.AppState.shutdown;

public class DashboardMenu extends Menu{

    private final MemberService memberService;

    public DashboardMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Dashboard", "/dashboard", terminalReader, menuRouter);
        this.memberService = memberService;
    }

    @Override
    public void render() throws IOException {
        System.out.println("Welcome back " + memberService.getSessionMember().getFullName() +  " Here is your dashboard!\n 1) Make a card \n 2) Logout" );

        String userInput = terminalReader.readLine();

        switch (userInput){
            case "1":
                System.out.println("Making a kanban card..");
                break;
            case "2":
                System.out.println("User has selected to logout. Hope to see you soon");
                // What do you think we should do ehere?
                memberService.logout();
                menuRouter.transfer("/welcome");
                break;
            default:
                System.out.println("Invalid selection please try again");
        }

    }
}
