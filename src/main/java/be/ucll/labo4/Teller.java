package be.ucll.labo4;

public class Teller {
    private int waarde;
    private boolean isAan;

    public Teller() {
        waarde = 0;
        isAan = true;
    }

    public void add() {
        if (isAan) this.waarde++;
    }

    public void reset() {
        if (isAan) this.waarde = 0;
    }

    public int getWaarde() {
        return waarde;
    }

    public boolean isAan() {
        return isAan;
    }

    public void zetAan() {
        isAan = true;
    }

    public void zetUit() {
        isAan = false;
    }
}
