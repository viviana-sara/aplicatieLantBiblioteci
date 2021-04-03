package View;


import View.GUIAdministrator;
import View.IAutentificareView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import presenter.PAutentificare;
import sample.Main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;


public class Autentificare implements  Initializable, IAutentificareView {

        @FXML
        private Button autentificare;
        @FXML
        private TextField cont;
        @FXML
        private PasswordField pass;
        @FXML
        private Label mAutentificare;
        @FXML
        private ComboBox selRol;

        private String rolUtilizator;

        private int next;


        public void autentificareOnAction(ActionEvent event) throws IOException {
                Stage stage =(Stage) autentificare.getScene().getWindow();
                System.out.println(cont.getText()+"   "+ pass.getText());
                PAutentificare p = new PAutentificare(this);
                p.autentificare();
                nextGUI();
        }


        @Override
        public String getRol() {
                System.out.println(selRol.getValue().toString());
                return selRol.getValue().toString();


        }

        @Override
        public void setRol(String rol) {
                rolUtilizator = rol;
        }

        @Override
        public String getCont() {
                return cont.getText();
        }

        @Override
        public String getPass() {
                return pass.getText();
        }

        @Override
        public void setMessage(String text) {
                mAutentificare.setText(text);
        }

        @Override
        public void setNextStep(int ok) {
                next = ok;
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                selRol.getItems().removeAll(selRol.getItems());
                selRol.getItems().addAll("administrator", "bibliotecar", "abonat");
                selRol.getSelectionModel().select("abonat");
        }

        private void nextGUI() throws IOException{
                if (next == 1){
                        if (rolUtilizator.equals("administrator")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIAdministrator.fxml"));
                                Parent root =(Parent) loader.load();
                                GUIAdministrator administrator = loader.getController();
                                PAutentificare p = new PAutentificare(this);
                                administrator.getBiblioteca(p.getB());
                                administrator.getRolUtilizator("administrator");
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                                Stage stage2 =(Stage) autentificare.getScene().getWindow();
                                stage2.close();
                        }
                        if (rolUtilizator.equals("abonat")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIAbonat.fxml"));
                                Parent root =(Parent) loader.load();
                                GUIAbonat abonat = loader.getController();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                                Stage stage2 =(Stage) autentificare.getScene().getWindow();
                                stage2.close();
                        }
                        if (rolUtilizator.equals("bibliotecar")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIBibliotecar.fxml"));
                                Parent root =(Parent) loader.load();
                                GUIBibliotecar bibliotecar = loader.getController();
                                PAutentificare p = new PAutentificare(this);
                                bibliotecar.getBiblioteca(p.getB());
                                bibliotecar.getRolUtilizator("bibliotecar");
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                                Stage stage2 =(Stage) autentificare.getScene().getWindow();
                                stage2.close();
                        }
                }
        }

}
