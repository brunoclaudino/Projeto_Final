package com.example.projeto;

import java.sql.*;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/join")
public class JoinTeste {
	public static final String DBURL = "jdbc:mysql://localhost:3306/conta?serverTimezone=UTC";
    public static final String DBUSER = "root";
    public static final String DBPASS = "admin123";
    public static void main(String args[])
    {
        try
        {
             //Load the driver
             Class.forName("com.mysql.cj.jdbc.Driver");
             //Create the connection object
             Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
             //Query String
             String sql = "select * from conta e inner join cliente d on e.id_cliente = d.id_cliente";
             Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery(sql);
             System.out.println("EmpName  City   Department");
             System.out.println("**===========**==========**");
             while (result.next())
             {
                  System.out.println (
                       result.getString(1)+"   "+
                       result.getString(2)+"     "+
                       result.getString(3)+"   "+
                       result.getString(4)+"   "+
                       result.getString(5)+"   "+
                       result.getString(6)+"   ");
             }
             System.out.println("**===========**==========**\n");
        }
        catch(Exception ex)
        {
             ex.printStackTrace();
        }
   }

}
