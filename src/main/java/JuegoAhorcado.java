import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;

public class JuegoAhorcado {
    private Palabra palabra;
    private int vida;
    private String nombre;
    private String[] letrasAcertadas;
    private boolean hasGanado;
    private int score;
    private Scanner sc;

    /**
     * Construntor de la clase JuegoAhoracado
     */
    public JuegoAhorcado() {
        sc = new Scanner(System.in);
        vida = 1;
        System.out.println("Introduce tu nombre: ");
        nombre = sc.nextLine();
        score = 0;
    }

    public static void main(String[] args) {
        JuegoAhorcado juego = new JuegoAhorcado();
        juego.comenzarJuego(true);
    }

    /**
     * Metodo que permite que comienze el juego
     */
    public void comenzarJuego(boolean godMode) {
        while (vida>0) {
            palabra = new Palabra();
            letrasAcertadas = new String[palabra.convertirPalabra().length];
            vida = palabra.convertirPalabra().length;
            letrasAcertadas = palabraConGuiones();
            hasGanado = false;

            String[] letras = palabra.convertirPalabra();
            ArrayList<String> listLetras = new ArrayList<>(Arrays.asList(letras));
            System.out.println(palabraOculta(""));
            System.out.println("\033[35m"+"<===================>"+"\u001B[0m");

            while(!hasGanado && vida>0) {
                ArrayList<String> acertadas = new ArrayList<>(Arrays.asList(letrasAcertadas));
                barraVida();
                System.out.println("\033[35m"+"<===================>"+"\u001B[0m");
                System.out.println("Introduce una letra o la palabra");
                if (godMode) {
                    System.out.println("\u001B[33m"+"La palabra es: " + palabra+"\u001B[0m");
                }
                String letra = sc.nextLine().toLowerCase();
                System.out.println(palabraOculta(letra));
                System.out.println("\033[35m"+"<===================>"+"\u001B[0m");

                if (letra.length() <= 1) {
                    if (acertadas.contains(letra)) {
                        vida--;
                        System.out.println("\u001B[31m"+"Esta letra ya la has dicho"+"\u001B[0m");
                    }
                    else if(listLetras.contains(letra)) {
                        System.out.println("\u001B[33m"+"Bien hecho " + nombre+"\u001B[0m");
                        palabraAcertada();
                    }
                    else{
                        vida--;
                        System.out.println("\u001B[31m"+"Has fallado " + nombre+"\u001B[0m");
                    }
                } else {
                    if (letra.equals(palabra.toString())) {
                        letrasAcertadas = palabra.convertirPalabra();
                        palabraAcertada();
                    } else {
                        vida -= 2;
                    }
                }
            }
            if (vida>0) {
                System.out.println("\u001B[33m"+"Has procedido a la ganacion"+"\u001B[0m");
                System.out.println("\033[35m"+"<===================>"+"\u001B[0m");
                System.out.println("\u001B[33m"+"La palabra es: " + palabra+"\u001B[0m");
                score += (palabra.convertirPalabra().length-(palabra.convertirPalabra().length - vida));
                System.out.println("\033[35m"+"<===================>"+"\u001B[0m");
                System.out.println("Tu puntuacion es: " +score+ " Orens");
                System.out.println("\033[35m"+"<===================>"+"\u001B[0m");
                System.out.println("\u001B[33m"+"Vuelves a jugar"+"\u001B[0m");
            }
            else {
                System.out.println("\u001B[31m"+"Eres un manco"+"\u001B[0m");
                System.out.println("\u001B[31m"+"La palabra era " + palabra+"\u001B[0m");
                System.out.println("Tu puntuacion ha sido de: " +score+ " Orens");
                getAhorcado();
            }
        }


    }

    /**
     * Metodo que genera la barra de vida del jugador
     */
    public void barraVida() {
        String puntosVida = "";
        for(int i=0; i<vida; i++) {
            puntosVida+="*";
        }
        System.out.println("Tu vida es: " + "\033[36m"+puntosVida+"\u001B[0m");
    }

    /**
     * Metodo que sustituye los guiones por la letra que el jugador a acertado
     * Se necesita que se le pase por parametro un String con la letra
     * @param letra a sustituir
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
     * Metodo que devuelve verdadero si la palabra ha sido acertada por el jugador
     */
    public void palabraAcertada() {
        ArrayList<String> acertado = new ArrayList<>(Arrays.asList(letrasAcertadas));
        if (!acertado.contains("-")) {
            hasGanado=true;
        }
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
     * Metodo que muestra una imagen del ahoracado en ASCII, desde un archivo externo
     */

    public void getAhorcado() {
        Scanner s = null;
        try{
            String text = "ahorcado.txt";
            File fl = new File(text);
            s = new Scanner(fl);
            while(s.hasNextLine()) {
                System.out.println( "\033[36m"+s.nextLine()+"\u001B[0m");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            if(s != null) {
                s.close();
            }
        }
    }
}
