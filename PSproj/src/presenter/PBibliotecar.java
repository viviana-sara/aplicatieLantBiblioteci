package presenter;

import Model.Carte;
import Model.CartePersistenta;
import Model.UtilizatorPersistenta;
import View.IAbonatView;
import netscape.javascript.JSObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PBibliotecar {
    IAbonatView abonat;
    CartePersistenta pCarte;
    UtilizatorPersistenta pUtilizator;

    public PBibliotecar(IAbonatView view){
        abonat = view;
        pCarte = new CartePersistenta();
        pUtilizator = new UtilizatorPersistenta();
    }

    public void imprumutareCarte(){
        boolean i;
        String titlu = abonat.getTitlu();
        Carte carte = pCarte.cautareCarte(titlu);
        int n = carte.getDisponibil()-1;
        Carte nou = new Carte(carte.getTitlu(),carte.getAutor(),carte.getEditura(),carte.getDomeniu(),carte.getBiblioteca(),n);
         i = pCarte.actualizareCarte(carte,nou);


        if (carte.getDomeniu().equals("")){
            abonat.setMessage("Cartea aleasa nu exista!");
            return;
        }
        // i = pCarte.imprumutare(carte);
        if (i == true) {
            abonat.setMessage("Cartea a fost imprumutata cu succes!");
        }
        else
            abonat.setMessage("Cartea nu mai este disponibila");

    }
    public void returnareCarte(){
        boolean i;
        String titlu = abonat.getTitlu();
        Carte carte = pCarte.cautareCarte(titlu);
        int n = carte.getDisponibil()+1;
        Carte nou = new Carte(carte.getTitlu(),carte.getAutor(),carte.getEditura(),carte.getDomeniu(),carte.getBiblioteca(),n);
        i = pCarte.actualizareCarte(carte,nou);


        if (carte.getDomeniu().equals("")){
            abonat.setMessage("Cartea aleasa nu exista!");
            return;
        }
        if (i == true) {
            abonat.setMessage("Cartea a fost returnata cu succes!");
        }
        else
            abonat.setMessage("Cartea nu poate fi returnata!");

    }

    public void generareCSV(){
        List<Carte> carti = pCarte.citire();
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        String FILE_HEADER = "titlu,autor,editura,domeniu,biblioteca,disponibilitate";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("/Users/mesaros.viviana/Desktop/PS/PSproj/src/Model/file.csv");

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            for (Carte carte : carti) {
                fileWriter.append(carte.getTitlu());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(carte.getAutor());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(carte.getEditura());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(carte.getDomeniu());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(carte.getBiblioteca());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(carte.getDisponibil()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }
}
