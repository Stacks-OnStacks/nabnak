package com.revature.nabnak.menus;

import com.revature.nabnak.util.CustomLogger;
import com.revature.nabnak.util.MenuRouter;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Menu { // abstract classs
    protected String name; // this only allows any class within the package or sub-class (any extending Menu) to have access to this attribute
    protected String route;
    protected BufferedReader terminalReader; //Dependency Injection - as a requirement for the class to function that you want to inject at Instantiate
    protected MenuRouter menuRouter;
    public Menu(String name, String route, BufferedReader terminalReader, MenuRouter menuRouter){
        this.name = name;
        this.route = route;
        this.terminalReader = terminalReader;
        this.menuRouter = menuRouter;
    }

    public String getName(){
        return name;
    }

    public String getRoute(){
        return route;
    }

    // Adding abstract keyword requires that any class that inherits this Menu class MUST implement it
    public abstract void render() throws IOException;
}
