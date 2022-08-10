package com.revature.nabnak.util;

import com.revature.nabnak.member.MemberDao;
import com.revature.nabnak.member.MemberService;

public class AppState {

    public AppState(){

        MemberDao memberDao = new MemberDao();
        MemberService memberService = new MemberService(memberDao);

    }




}
