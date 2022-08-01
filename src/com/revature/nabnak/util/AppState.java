package com.revature.nabnak.util;

import com.revature.nabnak.daos.MemberDao;
import com.revature.nabnak.menus.DashboardMenu;
import com.revature.nabnak.menus.LoginMenu;
import com.revature.nabnak.menus.RegisterMenu;
import com.revature.nabnak.menus.WelcomeMenu;
import com.revature.nabnak.services.MemberService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    private final MenuRouter menuRouter;
    private final CustomLogger customLogger = CustomLogger.getLogger(true);

    public AppState(){
        isRunning = true;
        menuRouter = new MenuRouter();

        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        MemberDao memberDao = new MemberDao();
        MemberService memberService = new MemberService(memberDao);

        WelcomeMenu welcomeMenu = new WelcomeMenu(terminalReader, menuRouter, memberService);
        RegisterMenu registerMenu = new RegisterMenu(terminalReader, menuRouter, memberService);
        DashboardMenu dashboardMenu = new DashboardMenu(terminalReader, menuRouter, memberService);
        LoginMenu loginMenu = new LoginMenu(terminalReader, menuRouter, memberService);

        menuRouter.addMenu(welcomeMenu);
        menuRouter.addMenu(registerMenu);
        menuRouter.addMenu(dashboardMenu);
        menuRouter.addMenu(loginMenu);

        customLogger.info("Application initialized");

    }

    public void startup(){
        while (isRunning) {
            customLogger.log("Routing to welcome meu...");
            menuRouter.transfer("/welcome");
        }
    }

    public static void shutdown(){

        isRunning = false;

    }
}
