package Projeto;

import algs4.RedBlackBST;
import algs4.SeparateChainingHashST;

public class Schedule {

    private SeparateChainingHashST<String, Discipline> disciplines = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Room> rooms = new SeparateChainingHashST<>();
    private RedBlackBST<Date, Class> classes = new RedBlackBST<>();
    private Integer number;
    private Integer id;


    /**
     * construtor da class schedule
     * @param number identificador do dono do horário
     */
    public Schedule(Integer number) {
        this.number= number;
    }

    /**
     * função que adiciona disciplinas à ST de disciplinas da class Schedule
     * @param d disciplina a adicionar
     */
    public void addDiscipline(Discipline d) {
        if (this.disciplines.contains(d.getSigla()))
            return;
        this.disciplines.put(d.getSigla(), d);
    }

    /**
     * função que remove disciplinas à ST de disciplinas da class Schedule
     * @param d disciplina a remover
     */
    public void removeDiscipline(Discipline d) {
        if (this.disciplines.contains(d.getSigla())) {
            this.disciplines.delete(d.getSigla());
            System.out.println(d+" deleted from schedule of "+this.getNumber());
        }
    }

    /**
     * função que edita disciplinas da ST de disciplinas da class Schedule
     * @param d disciplina a editar
     * @param dnew disciplina editada
     */
    public void editDiscipline(Discipline d, Discipline dnew) {
        if (this.disciplines.contains(d.getSigla())) {
            this.disciplines.delete(d.getSigla());
            this.disciplines.put(dnew.getSigla(), dnew);
        } else
            System.out.println("Can't edit this discipline because it doesn't exists");
    }

    /**
     * função que imprime as disciplinas daa ST de disciplinas da class Schedule
     */
    public void printAllDisciplines() {
        for (String key : disciplines.keys()) {
            Discipline d = this.disciplines.get(key);
            if (d == null) System.out.println("Doesn't exist any discipline");
            else System.out.println(d);
        }
    }

    /**
     * função que adiciona salas à ST de salas da class Schedule
     * @param r sala a adicionar
     */
    public void addRoom(Room r) {
        if (this.rooms.contains(r.getNumber()))
            return;
        this.rooms.put(r.getNumber(), r);
    }

    /**
     * função que remove salas à ST de salas da class Schedule
     * @param r sala a remover
     */
    public void removeRoom(Room r) {
        if (this.rooms.contains(r.getNumber())) {
            this.rooms.delete(r.getNumber());
            System.out.println(r+" deleted from schedule of "+this.getNumber());
        }
    }

    /**
     * função que edita salas da ST de salas da class Schedule
     * @param r sala a editar
     * @param rnew sala editada
     */
    public void editRoom(Room r, Room rnew) {
        if (this.rooms.contains(r.getNumber())) {
            this.rooms.delete(r.getNumber());
            this.rooms.put(rnew.getNumber(), rnew);
        } else
            System.out.println("Can't edit this room because it doesn't exists");
    }

    /**
     * função que imprime as salas à ST de salas da class Schedule
     */
    public void printAllRooms() {
        for (Integer i : rooms.keys()) {
            Room r = this.rooms.get(i);
            if (r == null) System.out.println("Doesn't exist any room");
            else System.out.println(r);
        }
    }

    /**
     * função que adiciona aulas à ST de aulas da class Schedule
     * @param c aula a adicionar
     */
    public void addClass(Class c) {
        if (this.classes.contains(c.getDate()))
            return;
        this.classes.put(c.getDate(), c);
    }

    /**
     * função que remove aulas à ST de aulas da class Schedule
     * @param c aula a remover
     */
    public void removeClass(Class c) {
        if (this.classes.contains(c.getDate())) {
            this.classes.delete(c.getDate());
            System.out.println(c+" deleted from schedule of "+this.getNumber());
        }
    }

    /**
     * função que edita aulas da ST de aulas da class Schedule
     * @param c aula a editar
     * @param cnew aula editada
     */
    public void editClass(Class c, Class cnew) {
        if (this.classes.contains(c.getDate())) {
            this.classes.delete(c.getDate());
            this.classes.put(cnew.getDate(), cnew);
        } else
            System.out.println("Can't edit this class because it doesn't exists");
    }

    /**
     * função que imprime as aulas da ST de aulas da class Schedule
     */
    public void printAllClasses() {
        for (Date d : classes.keys()) {
            Class c = this.classes.get(d);
            if (c == null) System.out.println("Doesn't exist any class");
            else System.out.println(c);
        }
    }

    @Override
    public String toString() { return "Schedule{" + " number= " + number +" id= " + id + " }";}

    public SeparateChainingHashST<String, Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(SeparateChainingHashST<String, Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public SeparateChainingHashST<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(SeparateChainingHashST<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public RedBlackBST<Date, Class> getClasses() {
        return classes;
    }

    public void setClasses(RedBlackBST<Date, Class> classes) {
        this.classes = classes;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}