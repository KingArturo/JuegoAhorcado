import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class JuegoAhorcado {
    private Palabra palabra;
    private int vida;
    private String nombre;
    private String[] letrasAcertadas;
    private int puntos;

    /**
     * Construntor de la clase JuegoAhoracado
     */
    public JuegoAhorcado() {
        puntos = 0;
        palabra = new Palabra();
        vida = palabra.convertirPalabra().length;
        letrasAcertadas = new String[palabra.convertirPalabra().length];
        letrasAcertadas = palabraConGuiones();
    }

    public static void main(String[] args) {

    }



    public boolean acertado(String letra) {
        boolean bl = false;

        String[] letras = palabra.convertirPalabra();
        ArrayList<String> listLetras = new ArrayList<>(Arrays.asList(letras));
        ArrayList<String> acertadas = new ArrayList<>(Arrays.asList(letrasAcertadas));

        if (letra.length() <= 1) {
            if (acertadas.contains(letra)) {
                vida--;
            }
            else if(listLetras.contains(letra)) {
                mostrarPalabraOculta(letra);
                bl = true;
            }
            else{
                vida--;
            }
        } else {
            if (letra.equals(palabra.toString())) {
                letrasAcertadas = palabra.convertirPalabra();
                mostrarPalabraOculta(letra);
                puntos = palabra.convertirPalabra().length;
                bl = true;
            } else {
                vida -= 2;
            }
        }
        return bl;
    }

    /**
     * Devuelve true si el jugador a acertado todas las letras de la palabra
     * @return
     */
    public boolean win() {
        boolean win = false;
        if(puntos == palabra.convertirPalabra().length) {
            win = true;
        }
        return win;
    }

    /**
     * Devuelve true si la vida del jugador el 0
     * @return
     */
    public boolean loose() {
        boolean loose = false;
        if(vida <= 0) {
            loose = true;
        }
        return loose;
    }

    /**
     * Metodo que genera la barra de vida del jugador
     */
    public String barraVida() {
        String puntosVida = "";
        for(int i=0; i<vida; i++) {
            puntosVida+="*";
        }
        return   puntosVida;
    }

    public void mostrarPalabraOculta(String letra) {
        for(int i=0; i<letrasAcertadas.length; i++) {
            if (palabra.convertirPalabra()[i].equals(letra)) {
                letrasAcertadas[i]=letra;
                puntos++;
            }

        }
    }

    /**
     * Metodo que sustituye los guiones por la letra que el jugador a acertado
     * Se necesita que se le pase por parametro un String con la letra
     * @param letraComparar a sustituir
     * @return devuelve la palabra con la letra en su posicion
     */
    public String palabraOculta(String letraComparar) {
        String guiones = "";
        for(int i=0; i<letrasAcertadas.length; i++) {
            if (palabra.convertirPalabra()[i].equals(letraComparar)) {
                letrasAcertadas[i]=letraComparar;
            }
            guiones+=letrasAcertadas[i];

        }
        return guiones;
    }

    /**
     * Metodo que devuelve un array de String lleno de guiones y con la
     * longitud de la palabra a acertar
     * @return array de String lleno de guinoes
     */
    public String[] palabraConGuiones() {
        String[] guiones = new String[palabra.convertirPalabra().length];
        for (int i = 0; i < guiones.length; i++) {
            guiones[i] = "-";
        }
        return guiones;
    }

    /**
     * Devuelve un estring con la omages de un ahorcado
     * @return
     */
    public String getAhorcado() {
        Scanner s = null;
        String ahorcado = "";
        try{
            String text = "ahorcado.txt";
            File fl = new File(text);
            s = new Scanner(fl);
            while(s.hasNextLine()) {
                ahorcado += s.nextLine() + "\n";
            }

        }
        catch(Exception e){
            ahorcado = "Erro";
        }
        finally {
            if(s != null) {
                s.close();
            }
        }
        return ahorcado;
    }


    /**
     * Metodo que devuelve la palabra  a adivinar
     * @return
     */
    public String getPalabra() {
        return palabra.toString();
    }
}
