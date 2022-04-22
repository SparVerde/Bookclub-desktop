package com.company;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

//˗ Határozza meg a kitiltott klubtagok számát.
//˗ Döntse el, hogy szerepel-e az adatok között 18 évnél fiatalabb személy.
//˗ Határozza meg és írja ki a legidősebb klubtag nevét és születésnapját.
//˗ Határozza meg és írja ki nemenként csoportosítva a tagok számát.
//˗ Kérjen be a konzolról egy nevet. Határozza meg, hogy az adott személy ki van-e tiltva a klubból.
// Ha a megadott névvel nem szerepel klubtag, akkor „Nincs ilyen tagja a klubnak” üzenet jelenjen meg.
//https://static.javatpoint.com/src/jdbc/mysql-connector.jar

public class Statisztika {
    Connection conn;

    public Statisztika() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vizsga-konyvklub", "root", "");
    }
    public ArrayList<Member> ListaFeltolt() throws SQLException, ClassNotFoundException {
        ArrayList<Member> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM members;";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String gender = result.getString("gender");
            Date birth_date = result.getDate("birth_date");
            Boolean banned = result.getBoolean("banned");

            Member elem = new Member(id, name, gender, birth_date, banned);
            lista.add(elem);
        }
        return lista;
    }

}
