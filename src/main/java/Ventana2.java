/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author artur
 */
public class Ventana2 extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button;
    private JTextField text;
	private JuegoAhorcado juego;

    public Ventana2() {


		juego = new JuegoAhorcado();
        panel = new JPanel();
        label1 = new JLabel(juego.getPalabra());
        label2 = new JLabel(juego.barraVida());
        label3 = new JLabel();
        label4 = new JLabel(juego.palabraOculta(""));
        button = new JButton("Comprobar");
        text = new JTextField("Texto");

        this.add(panel);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(text);
        panel.add(button);
        panel.add(label4);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.button.addActionListener(this);
    }

    public static void main(String[] args) {
        
    }
    
    public void jButton1ActionPerformed(ActionEvent e) {
    	boolean bl = juego.acertado(text.getText());

        if (bl) {
            label3.setText("Has acertado");
        }
        else {
            label3.setText("Has fallado");
        }

        label4.setText(juego.palabraOculta(text.getText()));
        label2.setText(juego.barraVida());
    }

    public void actionPerformed(ActionEvent e) {
    	boolean bl = juego.acertado(text.getText());

        if (bl) {
            label3.setText("Has acertado");
        }
        else {
            label3.setText("Has fallado");
        }

        label4.setText(juego.palabraOculta(text.getText()));
        label2.setText(juego.barraVida());
    }
    
    public void hasGanado() {
    	
    }

}
