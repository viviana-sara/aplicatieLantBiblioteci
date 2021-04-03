package presenter;

import Model.Carte;
import Model.CartePersistenta;
import Model.Utilizator;
import View.IPersistentaCarte;

public class PPersistenaCarte {
    IPersistentaCarte carte;
    CartePersistenta persistenta;

    public PPersistenaCarte(IPersistentaCarte carte){
        this.carte=carte;
        persistenta = new CartePersistenta();
    }

    private Carte getItem(String text){
        Carte carte1 = new Carte();
        if (text.equals("vechi")){
            return new Carte(carte.getTitlu(),carte.getAutor(),carte.getEditura(),carte.getDomeniu(),carte.getBiblioteca(),carte.getDisponibilitate());
        }
        if (text.equals("nou")){
            return new Carte(carte.getTitluNou(),carte.getAutorNou(),carte.getEdituraNoua(),carte.getDomeniuNou(),carte.getBibliotecaAleasa(),carte.getDisponibilitateNoua());
        }
        return carte1;
    }

    public void adaugareUtilizator(){
        Carte carte1 = getItem("vechi");
        Carte carte2 = persistenta.cautareCarte(carte1.getTitlu());

        if (carte2.getTitlu().equals("")== false){
            carte.setAdaugareMessagge("Carte deja existenta! Puteti actualiza cartea");
            return;
        }
        else{
            boolean ok = persistenta.salvareCarte(carte1);
            if (ok == true) {
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu()+ " a fost adaugata cu succes!");
               return;
            }
            else{
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu() + "  nu a putut fi adaugata!");
                return;
            }

        }
    }

    public void stergereUtilizator(){
        Carte carte1 = getItem("vechi");
        Carte carte2 = persistenta.cautareCarte(carte1.getTitlu());
        String biblioteca = carte.getBiblioteca();

        if (carte2.getTitlu().equals("")== true){
            carte.setAdaugareMessagge("Carte nu exista! Puteti adauga cartea!");
            return;
        }
        else{
            if (biblioteca.equals(carte1.getBiblioteca())==false){
                carte.setAdaugareMessagge("Nu puteti sterge o carte din alta biblioteca!");
                return;
            }
            boolean ok = persistenta.stergereCarte(carte1);
            if (ok == true) {
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu()+ " a fost stearsa cu succes!");
                return;
            }
            else{
                carte.setAdaugareMessagge("Cartea "+ carte1.getTitlu() + "  nu a putut fi stearsa!");
                return;
            }

        }
    }

    public void actualizareUtilizator(){
        Carte carteVechi = getItem("vechi");
        Carte carteNou = getItem("nou");
        Carte c = persistenta.cautareCarte(carteVechi.getTitlu());
        String biblioteca = carte.getBiblioteca();

        if (c.getTitlu().equals("")== true){
            carte.setActualizareMessage("Cartea nu exista!");
            return;
        }
        else{
            if (carteVechi.getBiblioteca().equals(biblioteca)==false){
                carte.setActualizareMessage("Nu puteti actualiza o carte din alta biblioteca!");
                return;
            }
            boolean ok = persistenta.actualizareCarte(carteVechi,carteNou);
            if (ok == true){
                carte.setActualizareMessage("Actualizarea s-a realizat cu succes!");
                return;            }
            else{
                carte.setActualizareMessage("Actualizarea nu s-a putut finisa!");
            }
        }


    }
}
