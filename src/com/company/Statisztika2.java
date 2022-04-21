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

public class Statisztika2 {
    Connection conn;

    public Statisztika2() throws SQLException, ClassNotFoundException {
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

    Scanner sc = new Scanner(System.in);

    Statisztika db = new Statisztika();
    int darab =0;
    int kitiltottTag =0;
    int k =0;
    int max =0;
    LocalDate maxd;
    String maxnev = "";
    int ferfi =0;
    int no =0;
    String kitiltva="";
    LocalDate date = LocalDate.now();
    //LocalDate date = LocalDate.parse("2022-04-22");


    ArrayList<Member> lista = db.ListaFeltolt();
    //ArrayList<String> kitiltottak = new ArrayList<>();
    ArrayList<String> kitiltottak = new ArrayList<>();
    ArrayList<Integer> kitiltottakdb = new ArrayList<>();
    public void kiiratas(){
        for (Member e : lista) {
            if(e.getBanned()==true){kitiltottTag++;
                if(!kitiltottak.contains(e.getName())){kitiltottak.add(e.getName());}}
            LocalDate dated =e.getBirth_date().toLocalDate();
            if((date.compareTo(dated) < 18)){k++;}
            if((date.compareTo(dated) > max)){maxd =dated; max = date.compareTo(dated);maxnev = e.getName();}
            if(e.getGender().equals("M")){ferfi++;}
            else{no++;};
        }
        for (String a : kitiltottak) {
            for (Member e : lista) {
                if(e.getName().equals(a)){darab++;}
            }
            kitiltottakdb.add(darab);
            darab =0;
            //System.out.println(a);
        }

        System.out.print("Adjon meg egy nevet:");
        String nev = sc.nextLine();
        for (Member i : lista){if(nev.equals(i.getName()))

            for (String z : kitiltottak){
                if(!kitiltottak.contains(nev))
                {kitiltva = "kitiltva";}
                else{kitiltva = "nincs kitiltva";};}
        else {kitiltva = "Nincs ilyen tagja a klubnak";};}
        System.out.println("A kitiltott tagok száma: "+kitiltottTag+"\nA legidősebb klubtag: " +maxnev+"\n\t"+"Férfiak száma: "
                +ferfi+", Nők száma: "+no
                +"\nAdjon meg egy nevet: "+nev+". A(z) megadott nevvel "+kitiltva+"kitiltva");
    }


}
