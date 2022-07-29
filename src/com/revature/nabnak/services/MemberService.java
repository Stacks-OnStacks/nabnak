package com.revature.nabnak.services;

import com.revature.nabnak.models.Member;
import com.revature.nabnak.util.CustomLogger;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;

import java.io.*;

public class MemberService {
    CustomLogger customLogger = CustomLogger.getLogger(true);


    public Member registerMember(Member newMember) {
        try {

            if (!isMemberValid(newMember)) {
                throw new InvalidUserInputException("User input was invalid");
            }

            if(!isEmailAvailable(newMember.getEmail())){
                throw new ResourcePersistanceException("Email is already registered please try logging in.");
            }

            File memoryFile = new File("resources/data.txt");
            // This beaut, is a try-with-resources block. This allows for anything that extends AutoClosable to be automatically closed
            try (FileWriter fileWriter = new FileWriter(memoryFile, true);) {
                fileWriter.write(newMember.writeToFile());
                // TODO: LOGG THE INFO AS PERSISTED customerLogger.log(arguments)
                customLogger.log("Member has been persisted: " + newMember);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newMember;

        } catch (InvalidUserInputException e) {
            // TODO: NEW READ ME (Lines 38-41)
            customLogger.warn(e.getMessage());
            return null;
        }
    }
    // TODO: NEW READ ME (Lines 43-73)
    public Member login(String email, String password){

        // try-with-resources automatically closes files for us
        try (
                FileReader fileReader = new FileReader("resources/data.txt");
                BufferedReader reader = new BufferedReader(fileReader);
        ) {

            String line = reader.readLine();

            while (line != null) {
                String[] info = line.split(",");
                System.out.println(info[0]);
                if (info[0].equals(email) && info[4].equals(password)) {
                    Member member = new Member();
                    member.setEmail(info[0]);
                    member.setFullName(info[1]);
                    member.setExperienceMonths(Integer.parseInt(info[2]));
                    member.setRegistrationDate(info[3]);
                    member.setPassword(info[4]);

                    return member;
                }
                line = reader.readLine();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally { //finally always executs
            System.out.println("Hello from the finally block");
        }
    }

    // TODO: NEW READ ME (Lines 76 - 105)
    public Member[] readAll(){
        Member[] members = new Member[30];

        // try-with-resources automatically closes files for us
        try (
                FileReader fileReader = new FileReader("resources/data.txt");
                BufferedReader reader = new BufferedReader(fileReader);
        ) {

            String line = reader.readLine();
            int index = 0;

            while (line != null) {
                String[] info = line.split(",");
                Member member = new Member();
                member.setEmail(info[0]);
                member.setFullName(info[1]);
                member.setExperienceMonths(Integer.parseInt(info[2])); member.setRegistrationDate(info[3]);
                member.setPassword(info[4]);
                members[index] = member;
                index++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //finally always executs
            System.out.println("Hello from the finally block");
        }

        return members;
    }

    public boolean isMemberValid(Member newMember){
        if(newMember == null) return false;
        // this || is the expression to signify to the conditional that if either of these are true then perform the action
        if(newMember.getEmail() == null || newMember.getEmail().trim().equals("")) return false;
        if(newMember.getFullName() == null || newMember.getFullName().trim().equals("")) return false;
        if(newMember.getExperienceMonths() < 0 ) return false;
        if(newMember.getRegistrationDate() == null || newMember.getRegistrationDate().trim().equals("")) return false;
        if(newMember.getPassword() == null || newMember.getPassword().trim().equals("")) return false;
        return true;
    }

    // TODO: IMPLEMENT MEEEEEEE!!!!!!!
    public boolean isEmailAvailable(String email){
        Member[] members = readAll();
        for(Member member: members){
            if(member == null) break;
            if(member.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

}