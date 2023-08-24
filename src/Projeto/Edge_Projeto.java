package Projeto;

import algs4.Edge;

public class Edge_Projeto extends Edge {

    private double tempo;

    public Edge_Projeto(int v, int w, double weight, double tempo) {
        super(v, w, weight);
        this.tempo = tempo;
    }


    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return String.format("%d-%d dist:%.3f tempo:%.3f", v, w, weight, tempo);
    }
}
