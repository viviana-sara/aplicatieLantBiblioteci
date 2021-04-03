package View;

public interface IAutentificareView {
    String getRol();
    void setRol(String rol);
    String getCont();
    String getPass();
    void setMessage(String text);
    void setNextStep(int ok);
}
