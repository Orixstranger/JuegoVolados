/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovolados;

import javax.swing.JOptionPane;

/**
 *
 * @author lenovo-user
 */
public class JuegoVolados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int apuestaFija = 10;
        //Numero de veces que se van a ejecutar las corridas
        int totalVeces = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de corridas")),dineroApuesta = 0, meta = 0,vecesGanadas = 0, vecesPerdidas = 0; //Cantidad de veces ganadas o perdidas

        //Verificación de la cantidad minima para apostar
        boolean verificardineroInicial = true;
        while (verificardineroInicial) {
            dineroApuesta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dinero a apostar, cantidad miníma de 10 dolares"));

            if (dineroApuesta >= apuestaFija) {
                verificardineroInicial = false;
            } else {
                JOptionPane.showMessageDialog(null, "Error la cantidad ingresada, debe ser desde " + apuestaFija + " dolares en adelante.", "Error!", JOptionPane.ERROR_MESSAGE);
            }

        }

        //Verificación de la meta a conseguir
        boolean verificarMeta = true;
        while (verificarMeta) {
            meta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la meta deseada"));
            if (meta > dineroApuesta) {
                verificarMeta = false;
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad de la meta debe ser mayor a " + dineroApuesta + " dolares.", "Error!", JOptionPane.ERROR_MESSAGE);
            }

        }

        System.out.println("|# Corrida\t|Dinero Anterior\t|\tApuesta\t|\tNumero Aleatorio\t|\tGanó?\t|Cantidad Despues|\tMeta\t|");

        //Se verifica las corridas ingresadas
        for (int i = 0; i < totalVeces; i++) {
            //Creo auxiliares para poder llevar cada una de las cuentas
            int dineroActual = dineroApuesta, apuestaActual = apuestaFija;

            //Verificación de cada uno de los estados del juego, gana o píerde
            boolean estadoJuego = true;
            
            while (estadoJuego) {

                //Creo una variable para llevar los valores aleatorios dados, para posterior transformarlos en porcentaje
                double porcentajeAleatorio = Math.random();

                //Se crea un auxiliar, este lleva el conteo del dinero antes de ser apostado
                int dineroAnterior = dineroActual;
                int apuestaAnterior = apuestaActual;

                String estado = "";
                //Verificación de porcentajes
                if (porcentajeAleatorio < 0.5) {
                    //Aumentamos el dinero actual
                    dineroActual = dineroActual + apuestaActual;
                    estado = "Si";
                    //Regresa a la apuesta fija
                    apuestaActual = apuestaFija;
                } else {
                    //Se disminuye el dinero actual con la apuesta
                    dineroActual = dineroActual - apuestaActual;
                    estado = "No";
                    //Mientras el dinero sea mayor o igual la apuesta de duplicará
                    if (dineroActual >= 2 * apuestaActual) {
                        apuestaActual = apuestaActual * 2;
                    } else {
                        //Sin embargo, la apuesta se mantendrá con el valor del dinero que se tiene
                        apuestaActual = dineroActual;
                    }

                }
                
                //Comparamos con la meta
                //Mientras el dinero llegue a la meta ingresada se contará un juego ganado
                if (dineroActual >= meta) {
                    estadoJuego = false;
                    vecesGanadas++;
                    System.out.println("|\t" + (i + 1) + "\t|\t" + dineroAnterior + "\t|\t" + apuestaAnterior + "\t|\t" + porcentajeAleatorio + "\t|\t" + estado + "\t|\t" + dineroActual + "\t|\tSi\t|");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

                } else {
                    //Mientras el dinero esté en 0 ingresada se contará un juego perdido
                    if (dineroActual <= 0) {
                        estadoJuego = false;
                        vecesPerdidas++;
                        System.out.println("|\t" + (i + 1) +"\t|\t" + dineroAnterior + "\t|\t" + apuestaAnterior + "\t|\t" + porcentajeAleatorio + "\t|\t" + estado + "\t|\t" + dineroActual + "\t|\tNo\t|");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
                    }else{
                        //Se mantiene sin presentar la meta
                        System.out.println("|\t"+(i+1)+"\t|\t"+dineroAnterior+"\t|\t"+apuestaAnterior+"\t|\t"+porcentajeAleatorio+"\t|\t"+estado+"\t|\t"+dineroActual+"\t|\t-\t|");
                    }
                }
            }

        }
        
        System.out.println("De " + totalVeces+" corridas, usted ganó: " + vecesGanadas + ", mientras que perdió: " + vecesPerdidas);
        System.out.println("");
        System.out.println("Con un porcentaje de " + ((double)vecesGanadas/(double)totalVeces)*100 + "% de victorias y " +((double)vecesPerdidas/(double)totalVeces)*100 + "% de derrotas");
        JOptionPane.showMessageDialog(null, "Fin");
    }

}
