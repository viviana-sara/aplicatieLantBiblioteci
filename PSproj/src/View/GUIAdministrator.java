package View;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GUIAdministrator extends GUIBibliotecar implements Initializable {

    public void pBibliotecarOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PersistentaAbonat.fxml"));
        Parent root =(Parent) loader.load();
        GUIPersistentaUtilizator persistenta = loader.getController();
        persistenta.setB(bilioteca);
        persistenta.setUtilizator(rolUtilizator);
        persistenta.setR("bibliotecar");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 =(Stage) dAbonat.getScene().getWindow();
        stage2.close();
    }

}
