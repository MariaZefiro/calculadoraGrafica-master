package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args)
    {
        port(8080);

        JFrame frame = new JFrame("calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,  400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JTextField inputField1 = new JTextField();
        JTextField inputField2 = new JTextField();

        JTextField outputField = new JTextField();
        outputField.setEditable(false);

        inputField1.setFont((new Font("Arial", Font.PLAIN, 30)));
        inputField2.setFont((new Font("Arial", Font.PLAIN, 30)));
        outputField.setFont((new Font("Arial", Font.PLAIN, 30)));

        JLabel label1 = new JLabel("Número 1:");
        label1.setFont((new Font("Arial", Font.BOLD, 30)));
        JLabel label2 = new JLabel("Número 2:");
        label2.setFont((new Font("Arial", Font.BOLD, 30)));
        JLabel label3 = new JLabel("Resultado:");
        label3.setFont((new Font("Arial", Font.BOLD, 30)));

        panel.add(label1);
        panel.add(inputField1);
        panel.add(label2);
        panel.add(inputField2);
        panel.add(label3);
        panel.add(outputField);

        String[] buttonLabels = {"Somar", "Subtrair", "Multiplicar", "Dividir"};

        get("/calculadora", (req, res) ->{
            String num1 = req.queryParams("num1");
            String num2 = req.queryParams("num2");
            inputField1.setText(num1);
            inputField2.setText(num2);
            return "Número 1: " + num1 +" "+ "Número 2: " +num2;
        });

        get("/soma", (req, res) ->{
            double numero1 = Double.parseDouble(inputField1.getText());
            double numero2 = Double.parseDouble(inputField2.getText());
            double result = numero1+numero2;
            outputField.setText(Double.toString(result));
            return "Número 1: " + numero1 +" "+ "Número 2: " +numero2+ " "+"Resultado: "+result;
        });

        get("/subt", (req, res) ->{
            double numero1 = Double.parseDouble(inputField1.getText());
            double numero2 = Double.parseDouble(inputField2.getText());
            double result = numero1-numero2;
            outputField.setText(Double.toString(result));
            return "Número 1: " + numero1 +" "+ "Número 2: " +numero2+ " "+"Resultado: "+result;
        });

        get("/mult", (req, res) ->{
            double numero1 = Double.parseDouble(inputField1.getText());
            double numero2 = Double.parseDouble(inputField2.getText());
            double result = numero1*numero2;
            outputField.setText(Double.toString(result));
            return "Número 1: " + numero1 +" "+ "Número 2: " +numero2+ " "+"Resultado: "+result;
        });

        get("/div", (req, res) ->{
            double numero1 = Double.parseDouble(inputField1.getText());
            double numero2 = Double.parseDouble(inputField2.getText());
            double result = numero1/numero2;
            outputField.setText(Double.toString(result));
            return "Número 1: " + numero1 +" "+ "Número 2: " +numero2+ " "+"Resultado: "+result;
        });

        get("/json", (req, res) ->{
            double numero1 = Double.parseDouble(inputField1.getText());
            double numero2 = Double.parseDouble(inputField2.getText());
            String content = "{ \"Parâmetro 1\": \""+numero1+"\"  \"Parâmetro 2:\": \""+numero2+"\"}";
            return content;
        });

        for(String label: buttonLabels){
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            panel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double  numero1, numero2, result = 0.0;

                    try{
                        if (label.equals("Somar")){
                            numero1 = Double.parseDouble(inputField1.getText());
                            numero2 = Double.parseDouble(inputField2.getText());
                            result = numero1 + numero2;
                        }
                        else if (label.equals("Subtrair")){
                            numero1 = Double.parseDouble(inputField1.getText());
                            numero2 = Double.parseDouble(inputField2.getText());
                            result = numero1 - numero2;
                        }
                        else if (label.equals("Multiplicação")){
                            numero1 = Double.parseDouble(inputField1.getText());
                            numero2 = Double.parseDouble(inputField2.getText());
                            result = numero1 * numero2;
                        }
                        else if (label.equals("Dividir")){
                            numero1 = Double.parseDouble(inputField1.getText());
                            numero2 = Double.parseDouble(inputField2.getText());
                            if (numero2 !=0){
                                result = numero1 / numero2;
                            }
                           else {
                                outputField.setText("Erro: Divisão por zero");
                            }
                        }
                        outputField.setText(Double.toString(result));
                    }
                    catch (NumberFormatException ex){
                        outputField.setText("Erro: Entrada inválida");
                    }
                }
            });
        }
        frame.add(panel);
        frame.setVisible(true);


    }
}