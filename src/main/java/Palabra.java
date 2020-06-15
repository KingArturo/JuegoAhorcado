import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Palabra {
    private ArrayList<String> palabras;
    private String pl;
    private Random aleatorio;
    /**
     * Constructor de la clase Palabra
     */
    public Palabra() {
        palabras = new ArrayList<>();
        leeArchivo();
        aleatorio=new Random();
        getPalabraRandom();

    }

    public void getPalabraRandom() {
        pl = palabras.get(aleatorio.nextInt(palabras.size()));
    }

    /**
     *Mete las letras de la palabra en un arry de String
     * @return Array de String
     */

    public String[] convertirPalabra() {
        String[] letra = new String[pl.length()];
        int cont = 0;
        while(cont<letra.length){
            letra[cont] = Character.toString(pl.charAt(cont)).toLowerCase();
            cont++;
        }
        return letra;
    }
    /**
     * Metodo que devuelve la palabra y pone todos los caracteres en minusculas
     * @return Palabra en minuscula
     */
    public String toString() {
        return pl.toLowerCase();
    }

    /**
     * Metodo que lee el archivo donde esta las palabras que se pueden usar
     */
    public void leeArchivo() {
        Scanner s = null;
        try{
            String text = "palabras.txt";
            File fl = new File(text);
            s = new Scanner(fl);
            while(s.hasNextLine()) {
                String linea = s.nextLine();
                palabras.add(linea);
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
