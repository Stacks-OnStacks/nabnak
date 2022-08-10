package com.revature.nabnak.util;

import com.revature.nabnak.daos.MemberDao;
import com.revature.nabnak.services.MemberService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    public AppState(){

        MemberDao memberDao = new MemberDao();
        MemberService memberService = new MemberService(memberDao);

    }




}
