package presenter;

import Model.Utilizator;
import Model.UtilizatorPersistenta;
import View.IAbonatView;
import View.IPersistentaUtilizator;

public class PPersistentaUtilizator {
    IPersistentaUtilizator abonat;
    UtilizatorPersistenta pUtilizator;

    public PPersistentaUtilizator(IPersistentaUtilizator view){
        abonat = view;
        pUtilizator = new UtilizatorPersistenta();
    }

    public void adaugareUtilizator(){
        String nume = abonat.getNume();
        String cont = abonat.getCont();
        String pass = abonat.getPass();
        String biblioteca = abonat.getBibloteca();
        String rol = abonat.getRolAdauga();

        Utilizator utilizator = pUtilizator.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")== false){
            abonat.setAdaugare("Utilizator deja existent!");
            System.out.println("utilizator inexistent");
            return;
        }
        else{
            boolean ok = pUtilizator.salvareUtilizator(new Utilizator(nume, biblioteca,rol,cont,pass));
            if (ok == true) {
                abonat.setAdaugare(rol + "ul " + nume + " a fost adaugat cu succes!");
                System.out.println("adaugat");
            }
            else{
                abonat.setAdaugare(rol+ "ul "+nume+" nu a putut fi adaugat!");
                System.out.println("neadaugat");
            }

        }
    }

    public void stergereUtilizator(){
        String nume = abonat.getNume();
        String cont = abonat.getCont();
        String pass = abonat.getPass();
        String biblioteca = abonat.getBibloteca();
        String rol = abonat.getRolAdauga();

        Utilizator utilizator = pUtilizator.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")== true){
            abonat.setAdaugare(rol+ "ul "+nume+" nu exista!");
            System.out.println("existent");
            return;
        }
        else{
            if (utilizator.getBiblioteca().equals(biblioteca)==false){
                abonat.setAdaugare( "Nu puteti sterge un "+rol+" din alta biblioteca!");
                System.out.println("alta biblioteca");
                return;
            }
            boolean ok = pUtilizator.stergereUtilizator(utilizator);
            if (ok == true) {
                abonat.setAdaugare(rol + "ul " + nume + " a fost sters cu succes!");
                System.out.println("sters");
            }
            else{
                abonat.setAdaugare(rol+ "ul "+nume+" nu a putut fi sters!");
                System.out.println("nesters");
            }

        }
    }

    public void actualizareUtilizator(){
        String nume = abonat.getNume();
        String cont = abonat.getCont();
        String pass = abonat.getPass();
        String biblioteca = abonat.getBibloteca();
        String rol = abonat.getRolAdauga();
        String numeNou = abonat.getNumeNou();
        String contNou = abonat.getContNou();
        String passNou = abonat.getPassNou();
        String bibliotecaActualizare = abonat.getBibliotecaActualizare();

        Utilizator utilizator = pUtilizator.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")== true){
            abonat.setActualizare(rol+ "ul "+nume+" nu exista!");
            System.out.println("inexistent");
            return;
        }
        else{
            if (utilizator.getBiblioteca().equals(biblioteca)==false){
                abonat.setActualizare( "Nu puteti sterge un "+rol+" din alta biblioteca!");
                System.out.println("alta biblioteca");
                return;
            }
            Utilizator nou = new Utilizator(numeNou,bibliotecaActualizare,rol,contNou,passNou);
            boolean ok = pUtilizator.actualizareUtilizator(nou,utilizator);
            if (ok == true){
                abonat.setActualizare(rol+ "ul "+nume+" a fost actualizat cu succes!");
                System.out.println("actualizat");
            }
            else{
                abonat.setActualizare(rol+ "ul "+nume+" nu a putut fi actualizat!");
                System.out.println("neactualizat");
            }
        }


    }

}
