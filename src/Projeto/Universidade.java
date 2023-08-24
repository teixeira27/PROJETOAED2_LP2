package Projeto;

import algs4.ST;
import algs4.SeparateChainingHashST;

public class Universidade {

    SeparateChainingHashST<Integer, Student> students = new SeparateChainingHashST<>();
    SeparateChainingHashST<Integer, Professor> professors = new SeparateChainingHashST<>();
    SeparateChainingHashST<String, Discipline> disciplines = new SeparateChainingHashST<>();
    SeparateChainingHashST<Integer, Room> rooms = new SeparateChainingHashST<>();
    SeparateChainingHashST<Integer, Schedule> schedules = new SeparateChainingHashST<>();

    ST<Integer, Vertice> st = new ST<>();
    EdgeWeightedGraph_Projeto g;
}
