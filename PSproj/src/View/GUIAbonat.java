package View;

import Model.Carte;

import View.GUIAdministrator;
import View.IAutentificareView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import presenter.PAbonat;
import presenter.PAutentificare;
import sample.Main;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.io.File;

public class GUIAbonat  implements IAbonatView, Initializable {

    @FXML
    protected Button dAbonat;
    @FXML
    protected Button veziBiblioteca;
    @FXML
    protected Button veziFiltru;
    @FXML
    protected Button cautaCarte;
    @FXML
    protected Button imprumutareCarte;
    @FXML
    protected Button pAbonat;
    @FXML
    protected Button returnareCarte;
    @FXML
    protected Button pCarte;
    @FXML
    protected Button grafic;
    @FXML
    protected Button pBibliotecar;
    @FXML
    protected TextField titluAles;
    @FXML
    protected Label mAbonat;
    @FXML
    protected ComboBox alegeBiblioteca;
    @FXML
    protected ComboBox alegeFiltru;
    @FXML
    protected ComboBox statistici;


    @Override
    public String getBibliotecaAleasa() {
        return alegeBiblioteca.getValue().toString();
    }

    @Override
    public String getFiltru() {
       return alegeFiltru.getValue().toString();

    }

    @Override
    public String getTitlu() {
        return titluAles.getText();
    }

    @Override
    public void setMessage(String text) {
        mAbonat.setText(text);
    }

    public void vCartiBiblioteca(){
        String titlu;
        String autor;
        String editura;
        String domeniu;
        String disponibilitate;
        int i = 0;
        PAbonat abonat = new PAbonat(this);
        List<Carte> carti= (List<Carte>) abonat.vizualizareCarti();
        if (carti.size()>0) {
            String[] columnNames = {"Titlu",
                    "Autor",
                    "Editura",
                    "Domeniu",
                    "Disponibilitate"};
            Object[][] data = new Object[carti.size()][];
            for (Carte carte: carti){
                titlu = carte.getTitlu();
                autor = carte.getAutor();
                editura = carte.getEditura();
                domeniu = carte.getDomeniu();
                disponibilitate = String.valueOf(carte.getDisponibil());
                data[i++] = new Object[]{titlu, autor, editura, domeniu, disponibilitate};
            }



            JTable table = new JTable(data, columnNames);
            Frame f = new JFrame();
            f.setTitle("Vizualizare Carti ");
            JScrollPane sp = new JScrollPane(table);
            f.add(sp);
            f.setSize(500, 200);
            f.setVisible(true);
        }

    }

    public void fCartiBiblioteca(){
        String titlu;
        String autor;
        String editura;
        String domeniu;
        String biblioteca;
        String disponibilitate;
        PAbonat abonat = new PAbonat(this);
        List<Carte> carti= (List<Carte>) abonat.filtrareCarti();
        if (carti.size()>0) {
            String[] columnNames = {"Titlu",
                    "Autor",
                    "Editura",
                    "Domeniu",
                    "Biblioteca",
                    "Disponibilitate"};
            Object[][] data = new Object[carti.size()][];
            for (int i = 0; i < carti.size(); i++) {
                titlu = carti.get(i).getTitlu();
                autor = carti.get(i).getAutor();
                editura = carti.get(i).getEditura();
                domeniu = carti.get(i).getDomeniu();
                biblioteca = carti.get(i).getBiblioteca();
                disponibilitate = String.valueOf(carti.get(i).getDisponibil());
                data[i] = new Object[]{titlu, autor, editura, domeniu, biblioteca, disponibilitate};
            }
            JTable table = new JTable(data, columnNames);
            Frame f = new JFrame();
            f.setTitle("Filtare Carti ");
            JScrollPane sp = new JScrollPane(table);
            f.add(sp);
            f.setSize(500, 200);
            f.setVisible(true);
        }

    }

    public void vCarte(){
        PAbonat abonat = new PAbonat(this);
        Carte carte = abonat.carteCautata();
        if (carte.getDomeniu().equals("")== false) {
            String m = "Cartea " + carte.getTitlu() + " scrisa de " + carte.getAutor() + ", editura " + carte.getEditura() + ", domeniul " + carte.getDomeniu() + " \neste disponibila in " + carte.getDisponibil() + " exemplare.";
            mAbonat.setText(m);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alegeBiblioteca.getItems().removeAll(alegeBiblioteca.getItems());
        alegeBiblioteca.getItems().addAll("Daicoviciu", "Observator", "Mecanica", "Marasti");
        alegeFiltru.getItems().removeAll(alegeFiltru.getItems());
        alegeFiltru.getItems().addAll("domeniu", "autor", "disponibilitate", "titlu");
        statistici.getItems().removeAll(statistici.getItems());
        statistici.getItems().addAll("autor", "domeniu", "editura", "disponibilitate");
    }

    public void veziBibliotecaOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) veziBiblioteca.getScene().getWindow();
        vCartiBiblioteca();
    }
    public void veziFiltruOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) veziFiltru.getScene().getWindow();
        fCartiBiblioteca();
        //cautaCarte.setVisible(false);
    }

    public void veziCarteOnAction(ActionEvent event) throws IOException {
        Stage stage =(Stage) cautaCarte.getScene().getWindow();
        vCarte();
    }
    public void anulareButtonOnAction(ActionEvent ev) throws IOException { //inchide aplicatia
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Autentificare.fxml"));
        Parent root =(Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Button button= new Button();
        Stage stage2 =(Stage) dAbonat.getScene().getWindow();
        stage2.close();
    }

    public void graph(){
        String s = statistici.getValue().toString();
        PAbonat abonat = new PAbonat(this);

        BarChart<String,Number> b;
        PieChart pieChart;
        Stage stage = new Stage();

        if (s.equals("domeniu")){
            b = abonat.createGraphicsDomenii();
            Scene scene  = new Scene(b,500,500);
            stage.setScene(scene);
            stage.show();
        }
        if (s.equals("editura")){
            b = abonat.createGraphicsEditura();
            Scene scene  = new Scene(b,500,500);
            stage.setScene(scene);
            stage.show();
        }
        if (s.equals("disponibilitate")){
            pieChart = abonat.createGraphicsDisp();
            Scene scene  = new Scene(pieChart,500,500);
            stage.setScene(scene);
            stage.show();
        }
        if (s.equals("autor")){
            pieChart = abonat.createGraphicsAutor();
            Scene scene  = new Scene(pieChart,500,500);
            stage.setScene(scene);
            stage.show();
        }
    }

}
