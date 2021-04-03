package presenter;

import Model.Utilizator;
import Model.UtilizatorPersistenta;
import View.IAutentificareView;

public class PAutentificare {
    private IAutentificareView login;
    private UtilizatorPersistenta persistenta;

    public PAutentificare(IAutentificareView view){
        this.login = view;
        persistenta = new UtilizatorPersistenta();
    }

    public void autentificare(){
        String cont = login.getCont();
        String pass = login.getPass();
        String rol = login.getRol();

        Utilizator utilizator = persistenta.cautareUtilizator(cont,pass);
        if (utilizator.getNume().equals("")) {
            login.setMessage("Utilizator inexistent");
            return;
        }
        if (utilizator.getRol().equals(rol)== false){
            System.out.println("Roluri: " + utilizator.getRol() + "      " + rol);
            login.setMessage("Utilizatorul nu are acelasi rol cu cel selectat");
            return;
        }
        else{
            login.setMessage("autentificare reusita");
            login.setNextStep(1);
            login.setRol(rol);
        }


    }

    public String getB(){
        String cont = login.getCont();
        String pass = login.getPass();

        Utilizator utilizator = persistenta.cautareUtilizator(cont,pass);
        return utilizator.getBiblioteca();

    }
}
