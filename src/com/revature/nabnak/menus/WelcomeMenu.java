package com.revature.nabnak.menus;

import com.revature.nabnak.models.Member;
import com.revature.nabnak.services.MemberService;
import com.revature.nabnak.util.CustomCollections.List;
import com.revature.nabnak.util.CustomLogger;
import com.revature.nabnak.util.MenuRouter;

import java.io.*;
import java.time.LocalDateTime;

import static com.revature.nabnak.util.AppState.shutdown;

public class WelcomeMenu extends Menu {
    CustomLogger customLogger = CustomLogger.getLogger(true);
    private final MemberService memberService; // declaration, technically null

    public WelcomeMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Welcome", "/welcome", terminalReader, menuRouter);
        this.memberService = memberService; // initialize the value of the memberService
    }

    @Override // this indicates we are overriding the method we are inheriting
    public void render() throws IOException {

        String[] welcomeMessages = {"Welcome to Nabnak", "1) Login", "2) Register", "3) View members", "4) Exit application"};

        System.out.println(welcomeMessages[0]);
        System.out.println(welcomeMessages[1]);
        System.out.println(welcomeMessages[2]);
        System.out.println(welcomeMessages[3]);
        System.out.println(welcomeMessages[4]);

        String firstInput = terminalReader.readLine(); // it throws an IOException, this MUST be handled before compile time (checked)

        // if your switch cases don't handle control flow it will result in fall-through
        // can be advantageous
        switch (firstInput) {
            case "1": // if firstInput.equals("1") then this case will execute
                System.out.println("User has selected login....");
                menuRouter.transfer("/login");
                break; // the keyword break prevents any fall-through
            case "2":
                System.out.println("User has selected register....");
                customLogger.info("Now routing user to registration page");
                menuRouter.transfer("/register");
                break;
            // case "i": // example of fall through
            case "3":
                System.out.println("User wishes to view other members");
                //TODO: NEW READ ME(Lines 44-49)
                List<Member> members = memberService.readAll();
                for(int i = 0; i < members.size(); i++){
                    System.out.println(members.get(i));
                }
                break;
            case "4":
                System.out.println("User is now exiting. Hope to see you soon!");
                shutdown();
                break;

            default:
                System.out.println("User has not selected valid input....");

        }
    }

}
