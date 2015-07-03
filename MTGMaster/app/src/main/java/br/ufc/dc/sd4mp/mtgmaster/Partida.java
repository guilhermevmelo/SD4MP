package br.ufc.dc.sd4mp.mtgmaster;

public class Partida {
    private int player1;
    private int player2;

    private int Id;
    private String creation_time;

    public Partida() {
        player1 = 20;
        player2 = 20;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public void p1Plus() {
        player1++;
    }

    public void p1Minus() {
        player1--;
    }

    public void p2Plus() {
        player2++;
    }

    public void p2Minus() {
        player2--;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public int getVencedor() {
        return player1 > player2 ? 1 : 2;
    }

    public String toString() {
        return Id + "p1: " + player1 + "; p2: " + player2;
    }

}
