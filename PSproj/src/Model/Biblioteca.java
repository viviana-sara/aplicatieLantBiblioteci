package Model;

public class Biblioteca {
    private String nume;
    private Carti carti;
    private Persoane persoane;

    public  Biblioteca(){
        nume = "";
        carti = new Carti();
        persoane = new Persoane();
    }

    public Biblioteca(String nume, Carti carti, Persoane persoane){
        this.nume = nume;
        this.carti = carti;
        this.persoane = persoane;
    }

    public Biblioteca(Biblioteca biblioteca){
        this.nume = biblioteca.nume;
        this.carti = biblioteca.carti;
        this.persoane = biblioteca.persoane;
    }

    public String getNume() { return nume; }

    public void setNume(String nume) { this.nume = nume; }

    public Carti getCarti() { return carti; }

    public void setCarti(Carti carti) { this.carti = carti; }

    public Persoane getPersoane() { return persoane; }

    public void setPersoane(Persoane persoane) { this.persoane = persoane; }
}
