package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import presenter.PBibliotecar;

import java.io.IOException;

public class GUIBibliotecar extends GUIAbonat implements Initializable {

    protected String bilioteca;
    protected String rolUtilizator;


    public void getBiblioteca(String b){
        bilioteca = b;
    }
    public void getRolUtilizator(String r){rolUtilizator = r;}


    public void imprumutareCarteOnAction(ActionEvent event) throws IOException {
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.imprumutareCarte();
    }
    public void returnareOnAction(ActionEvent event) throws IOException {
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.returnareCarte();
    }

    public void pAbonatOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PersistentaAbonat.fxml"));
        Parent root =(Parent) loader.load();
        GUIPersistentaUtilizator persistenta = loader.getController();
        persistenta.setB(bilioteca);
        persistenta.setR("abonat");
        if (rolUtilizator.equals("bibliotecar")){
            persistenta.setUtilizator("bibliotecar");
        }
        if (rolUtilizator.equals("administrator")){
            persistenta.setUtilizator("administrator");
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 =(Stage) dAbonat.getScene().getWindow();
        stage2.close();
    }
    public void pCarteOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PersistentaCarti.fxml"));
        Parent root =(Parent) loader.load();
        GUICarte carte = loader.getController();
        carte.setB(bilioteca);
        if (rolUtilizator.equals("bibliotecar")){
            carte.setRolUtilizator("bibliotecar");
        }
        if (rolUtilizator.equals("administrator")){
            carte.setRolUtilizator("administrator");
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 =(Stage) dAbonat.getScene().getWindow();
        stage2.close();
    }

    public void salvareRapoarteOnAction(ActionEvent event){
        PBibliotecar bibliotecar = new PBibliotecar(this);
        bibliotecar.generareCSV();
    }

}
