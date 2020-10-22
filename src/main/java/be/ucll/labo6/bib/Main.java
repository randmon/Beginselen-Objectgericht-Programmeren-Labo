package be.ucll.labo6.bib;

public class Main {
    public static void main(String[] args) {
        Lid peter = new Lid("Peter", "Maartens", 1965, 2, 1);

        Boek chickenEscape = new Boek("The escape of the chicken", "Cristina Marques", 400, 12);

        System.out.println(peter);
        System.out.println(chickenEscape);
    }
}
