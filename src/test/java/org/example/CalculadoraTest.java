package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void soma(){
        Calculadora calc = new Calculadora();
        assertEquals(110,calc.soma(60, 50));
    }

    @Test
    void subt(){
        Calculadora calc = new Calculadora();
        assertEquals(0,calc.subt(10,10));
    }

    @Test
    void mult(){
        Calculadora calc = new Calculadora();
        assertEquals(100,calc.mult(10, 10));
    }

    @Test
    void divi(){
        Calculadora calc = new Calculadora();
        assertEquals(25,calc.divi(51, 2) , 1);
    }
}