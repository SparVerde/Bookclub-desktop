package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //Statisztika2 sz = new Statisztika2();
            //sz.kiiratas();

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
            String kitiltva="nincs kitiltva";
            int van=0;
            LocalDate date = LocalDate.now();
            //LocalDate date = LocalDate.parse("2022-04-22");


            ArrayList<Member> lista = db.ListaFeltolt();
            //ArrayList<String> kitiltottak = new ArrayList<>();
            ArrayList<String> kitiltottak = new ArrayList<>();
            ArrayList<Integer> kitiltottakdb = new ArrayList<>();

                for (Member e : lista) {
                    if(e.getBanned()==true){kitiltottTag++;
                        if(!kitiltottak.contains(e.getName())){kitiltottak.add(e.getName());}}
                    LocalDate dated =e.getBirth_date().toLocalDate();
                    if((date.compareTo(dated) < 18)){k++;}
                    if((date.compareTo(dated) > max)){maxd =dated; max = date.compareTo(dated);maxnev = e.getName();}
                    if(e.getGender().equals("M")){ferfi++;}
                    else{no++;};
                }

                System.out.print("Adjon meg egy nevet:");
                String nev =  sc.nextLine();
                for (Member i : lista)
                {if(nev.equals(i.getName())){ van ++;
                    for (String z : kitiltottak){
                        if(kitiltottak.contains(nev))
                        {kitiltva = "kitiltva";}}}}
                if (van==0) {kitiltva = "Nincs ilyen tagja a klubnak";}
                System.out.println("A kitiltott tagok száma: "+kitiltottTag+"\nA legidősebb klubtag: " +maxnev+"\n\t"+"Férfiak száma: "
                        +ferfi+", Nők száma: "+no
                        +"\nAdjon meg egy nevet: "+nev+". A(z) megadott nevvel "+nev+" "+kitiltva);


        }catch (Exception e) {
            System.out.println(e);
        }

    }
}

