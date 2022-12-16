import controllers.FCToga;

public class Teste {
    public static void main(String[] args) {
        FCToga instanciaPrograma = new FCToga();
        /*instanciaPrograma.criarUsuario(
                "12345678901",
                "password1234",
                "Fulano Ciclano Beltrano",
                "Promotor",
                "",
                "",
                ""
        );*/
        instanciaPrograma.buscarUsuario("12345678901");
    }
}
