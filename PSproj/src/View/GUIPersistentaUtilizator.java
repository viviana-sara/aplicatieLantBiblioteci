package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import presenter.PPersistentaUtilizator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIPersistentaUtilizator implements Initializable, IPersistentaUtilizator {

    @FXML
    protected TextField numeUt;
    @FXML
    protected TextField contUt;
    @FXML
    protected PasswordField passUt;
    @FXML
    protected TextField numeNouUt;
    @FXML
    protected TextField contNouUt;
    @FXML
    protected PasswordField passNouUt;
    @FXML
    protected ComboBox bibliotecaActualizare;
    @FXML
    protected Label mAdauga;
    @FXML
    protected Label mActualizare;
    @FXML
    protected Button stergeUt;
    @FXML
    protected Button adaugaUt;
    @FXML
    protected Button actualizareUt;
    @FXML
    protected Button inapoiPersistentaUt;

    protected String bibliotecaUtilizator;
    protected String rol;
    protected String rolUtilizator;

    public void setB(String text){
        bibliotecaUtilizator = text;
    }
    public void setR(String text){ rol = text; }
    public void setUtilizator(String text){rolUtilizator= text; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bibliotecaActualizare.getItems().removeAll(bibliotecaActualizare.getItems());
        bibliotecaActualizare.getItems().addAll("Daicoviciu", "Observator", "Mecanica", "Marasti");
    }

    @Override
    public String getNume() {
        return numeUt.getText();
    }

    @Override
    public String getCont() {
        return contUt.getText();
    }

    @Override
    public String getPass() {
        return passUt.getText();
    }

    @Override
    public String getNumeNou() {
        return numeNouUt.getText();
    }

    @Override
    public String getContNou() {
        return contNouUt.getText();
    }

    @Override
    public String getPassNou() {
        return passNouUt.getText();
    }

    @Override
    public void setActualizare(String text) {
        mActualizare.setText(text);
    }

    @Override
    public void setAdaugare(String text) {
        mAdauga.setText(text);
    }

    @Override
    public String getBibloteca() {
        return bibliotecaUtilizator;
    }

    @Override
    public String getRolAdauga() {
        return rol;
    }

    @Override
    public String getBibliotecaActualizare() {
        return bibliotecaActualizare.getValue().toString();
    }

    public void adaugareOnAction(ActionEvent event) throws IOException{
        Stage stage =(Stage)adaugaUt.getScene().getWindow();
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.adaugareUtilizator();
    }
    public void stergereOnAction(ActionEvent event) throws IOException{
        Stage stage =(Stage)adaugaUt.getScene().getWindow();
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.stergereUtilizator();
    }
    public void actualizareOnAction(ActionEvent event) throws IOException{
        Stage stage =(Stage)adaugaUt.getScene().getWindow();
        PPersistentaUtilizator persistenta = new PPersistentaUtilizator(this);
        persistenta.actualizareUtilizator();
    }

    public void inapoiOnAction(ActionEvent event) throws IOException {
        if (rolUtilizator.equals("bibliotecar")){
            FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIBibliotecar.fxml"));
            Parent root =(Parent) loader.load();
            GUIBibliotecar bibliotecar = loader.getController();
            bibliotecar.getBiblioteca(bibliotecaUtilizator);
            bibliotecar.getRolUtilizator("bibliotecar");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 =(Stage) inapoiPersistentaUt.getScene().getWindow();
            stage2.close();
        }
        if (rolUtilizator.equals("administrator")){
            FXMLLoader loader = new FXMLLoader(GUIBibliotecar.class.getResource("GUIAdministrator.fxml"));
            Parent root =(Parent) loader.load();
            GUIAdministrator administrator = loader.getController();
            administrator.getBiblioteca(bibliotecaUtilizator);
            administrator.getRolUtilizator("administrator");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage stage2 =(Stage) inapoiPersistentaUt.getScene().getWindow();
            stage2.close();
        }

    }
}
