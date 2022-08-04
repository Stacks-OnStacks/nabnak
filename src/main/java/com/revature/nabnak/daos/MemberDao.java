package com.revature.nabnak.daos;

import com.revature.nabnak.models.Member;
import com.revature.nabnak.util.CustomCollections.LinkedList;
import com.revature.nabnak.util.CustomCollections.List;
import com.revature.nabnak.util.CustomLogger;

import java.io.*;

public class MemberDao implements Crudable<Member> {

    CustomLogger customLogger = CustomLogger.getLogger(true);

    @Override
    public Member create(Member newMember) {
        File memoryFile = new File("resources/data.txt");
        // This beaut, is a try-with-resources block. This allows for anything that extends AutoClosable to be automatically closed
        try (FileWriter fileWriter = new FileWriter(memoryFile, true);) {
            fileWriter.write(newMember.writeToFile());
            // TODO: LOGG THE INFO AS PERSISTED customerLogger.log(arguments)
            customLogger.log("Member has been persisted: " + newMember);
            return newMember;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new LinkedList<>();

        // try-with-resources automatically closes files for us
        try (
                FileReader fileReader = new FileReader("resources/data.txt");
                BufferedReader reader = new BufferedReader(fileReader);
        ) {

            String line = reader.readLine();

            while (line != null) {
                String[] info = line.split(",");
                Member member = new Member();
                member.setEmail(info[0]);
                member.setFullName(info[1]);
                member.setExperienceMonths(Integer.parseInt(info[2])); member.setRegistrationDate(info[3]);
                member.setPassword(info[4]);

                members.add(member);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //finally always executs
            System.out.println("Hello from the finally block");
        }
        return members;
    }

    @Override
    public Member findById(String id) {
        return null;
    }

    @Override
    public boolean update(Member updatedObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
    // 4 Primary operations for the DAO is Create, Read, Update & Delete (CRUD) operations

    public Member loginCredentialCheck(String email, String password){

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
}
