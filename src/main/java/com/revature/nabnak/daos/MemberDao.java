package com.revature.nabnak.daos;

import com.revature.nabnak.models.Member;
import com.revature.nabnak.util.ConnectionFactory;
import com.revature.nabnak.util.CustomCollections.LinkedList;
import com.revature.nabnak.util.CustomCollections.List;
import com.revature.nabnak.util.CustomLogger;

import java.io.*;
import java.sql.*;

public class MemberDao implements Crudable<Member> {

    CustomLogger customLogger = CustomLogger.getLogger(true);


    @Override
    public Member create(Member newObject) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection();){

            System.out.println(conn); // quick connection check

            String sql = "select * from employee where department_id = ? and emp_position > ?";

            String tableName = "employee; drop table employee;";
            int depID = 2;



            // Statement statement = conn.createStatement(); // starting point for executing queries
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, depID);
            ps.setString(2, tableName);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                System.out.print(resultSet.getString("department_id") + " ");
                System.out.print(resultSet.getString("email") + " ");
                System.out.print(resultSet.getString("first_name") + " ");
                System.out.print(resultSet.getString("last_name") + " ");
                System.out.print(resultSet.getString("dob") + " ");
                System.out.print(resultSet.getString("emp_position") + " ");
                System.out.println(resultSet.getString("salary"));

            }

            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
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

    public Member loginCredentialCheck(String email, String password) {
        return null;
    }
}
