package com.revature.nabnak.util.CustomCollections;

import com.revature.nabnak.models.Member;

import java.time.LocalDateTime;

public class TempDriver {

    public static void main(String[] args) {
        LinkedList<Member> members = new LinkedList<>();

        members.add(new Member());
        members.add(new Member());
        members.add(new Member("ply@mail.com", "ply wood", 12, LocalDateTime.now().toString()));
        members.add(new Member());

        System.out.println(members.size());
        System.out.println(members.get(2).getEmail());

        LinkedList<Integer> numList = new LinkedList<>();

        numList.add(12);
        numList.add(13);
        numList.add(14);
        numList.add(91);
        numList.add(1);
        numList.add(12);

        System.out.println("The number list before we removed the 91, does it contain it? " + numList.contains(91));
        numList.remove(12);
        System.out.println("The number list after we removed the 91, does it contain it? " + numList.contains(12));
        numList.add(13);
        System.out.println(numList.get(5));



    }
}
