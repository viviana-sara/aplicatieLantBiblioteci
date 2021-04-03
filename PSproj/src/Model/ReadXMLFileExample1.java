package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import presenter.PPersistenaCarte;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadXMLFileExample1
{
    public static void main(String argv[]) {
        String nume;
        String rol;
        String cont;
        String parola;
        int nrLegitimatie;

        CartePersistenta persistenta = new CartePersistenta();
        List<Carte> carti = persistenta.citire();
        List<String> lista = new ArrayList<>();

        HashMap<String, Integer> domenii = new HashMap<>();
        for (Carte c : carti) {
            if (domenii.containsKey(c.getDomeniu())) {
                int n = domenii.get(c.getDomeniu());
                n++;
                domenii.replace(c.getDomeniu(), n);
            } else {
                domenii.put(c.getDomeniu(), 1);
                lista.add(c.getDomeniu());
            }
        }

        for (int i= 0 ; i<domenii.size(); i++){
            System.out.println("Doemniul " + lista.get(i));
        }
    }
}  