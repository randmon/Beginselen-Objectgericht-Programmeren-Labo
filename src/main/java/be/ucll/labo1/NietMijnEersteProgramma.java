package be.ucll.labo1;

public class NietMijnEersteProgramma {
    public static void main(String[] args) {
        String mijnVoornaam = "Cristina";
        String mijnAchternaam = "Marques";
        String naam = mijnVoornaam + " " + mijnAchternaam;
        int lessenVandaag = 3;
        //System.out.println("Goeiedag " + naam + "!\nEr zijn " + lessenVandaag + " lessen vandaag.");
        System.out.printf("Goeiedag %s!%nEr zijn %d lessen vandaag.", naam, lessenVandaag);


    }
}
