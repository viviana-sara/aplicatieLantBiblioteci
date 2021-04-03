package presenter;

import Model.Carte;
import Model.CartePersistenta;
import View.IAbonatView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

import javax.management.openmbean.ArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PAbonat {
    IAbonatView abonat;
    CartePersistenta persistenta;

    public PAbonat(IAbonatView view ){
        abonat = view;
        persistenta = new CartePersistenta();
    }

    public List<Carte> vizualizareCarti(){
        String bibliteca = abonat.getBibliotecaAleasa();
        List<Carte> carti = persistenta.cautareCartiBiblioteca(bibliteca);
        System.out.println(carti.size());
        if (carti.size()==0){
            abonat.setMessage("Nu exista nicio carte");
        }
        return carti;
    }

    public List<Carte> filtrareCarti(){
        String filtru = abonat.getFiltru();
        List<Carte> carti = persistenta.filtrareCarti(filtru);
        if (carti.size()== 0 )
            abonat.setMessage("Nu exista nicio carte");

        return carti;
    }

    public Carte carteCautata(){
        String titlu = abonat.getTitlu();
        Carte carte = persistenta.cautareCarte(titlu);
        if (carte.getTitlu().equals(""))
            abonat.setMessage("Cartea nu a fost gasita");

        return carte;
    }


    public BarChart<String,Number> createGraphicsDomenii(){
        List<Carte> carti = persistenta.citire();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Statistici dupa doemniu");
        xAxis.setLabel("Domeniu");
        yAxis.setLabel("Numar carti");

        List<String> lista = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getDomeniu())) {
                int n = domenii.get(c.getDomeniu());
                n += c.getDisponibil();
                domenii.replace(c.getDomeniu(), n);
            } else {
                domenii.put(c.getDomeniu(), c.getDisponibil());
                lista.add(c.getDomeniu());
            }
        }

        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i< lista.size(); i++){
            series1.getData().add(new XYChart.Data(lista.get(i), domenii.get(lista.get(i))));
        }

        bc.getData().add(series1);
        return bc;
    }

    public PieChart createGraphicsDisp(){
        List<Carte> carti = persistenta.citire();

        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        for (int i = 0; i< carti.size(); i++){
            pieChart.add(new PieChart.Data(carti.get(i).getTitlu(), carti.get(i).getDisponibil()));
        }

        PieChart chart = new PieChart(pieChart);

        return chart;
    }

    public BarChart<String,Number> createGraphicsEditura(){
        List<Carte> carti = persistenta.citire();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Statistici dupa editura");
        xAxis.setLabel("Editura");
        yAxis.setLabel("Numar carti");

        List<String> editura = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getEditura())) {
                int n = domenii.get(c.getEditura());
                n += c.getDisponibil();
                domenii.replace(c.getEditura(), n);
            } else {
                domenii.put(c.getEditura(), c.getDisponibil());
                editura.add(c.getEditura());
            }
        }

         XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i< editura.size(); i++){
            series1.getData().add(new XYChart.Data(editura.get(i), domenii.get(editura.get(i))));
        }
        bc.getData().add(series1);
        return bc;
    }


    public PieChart createGraphicsAutor(){
        List<Carte> carti = persistenta.citire();
        List<String> autor = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getAutor())) {
                int n = domenii.get(c.getAutor());
                n += c.getDisponibil();
                domenii.replace(c.getAutor(), n);
            } else {
                domenii.put(c.getAutor(), c.getDisponibil());
                autor.add(c.getAutor());
            }
        }

        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        for (int i = 0; i< autor.size(); i++){
            pieChart.add(new PieChart.Data(autor.get(i), domenii.get(autor.get(i))));
        }

        PieChart chart = new PieChart(pieChart);

        return chart;
    }

}
