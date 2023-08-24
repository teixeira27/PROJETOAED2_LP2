package Projeto;

import algs4.SeparateChainingHashST;

public class Professor extends Vertice {

    private Integer number;
    private SeparateChainingHashST<String, Discipline> disciplines = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, String> turmas = new SeparateChainingHashST<>();
    private Schedule schedule;

    public Professor(String name, Integer number, Integer x, Integer y, Integer z, Integer type) {
        super(x, y, z, type);
        this.setName(name);
        this.setNumber(number);
    }

    /**
     * contrutor da class professor
     *
     * @param name   nome do professor
     * @param number numero do professor
     */
    public Professor(String name, Integer number) {
        this.setName(name);
        this.setNumber(number);
    }

    /**
     * construtor por omissão
     */
    public Professor() {
    }

    /**
     * função que adiciona disciplinas à ST de disciplinas da class Professor
     *
     * @param d
     */
    public void addDiscipline(Discipline d) {
        if (this.disciplines.contains(d.getSigla()))
            return;
        this.disciplines.put(d.getSigla(), d);
    }

    /**
     * função que remove disciplinas à ST de disciplinas da class Professor
     *
     * @param d disciplina a adicionar
     */
    public void removeDiscipline(Discipline d) {
        if (this.disciplines.contains(d.getSigla())) {
            this.disciplines.delete(d.getSigla());
            System.out.println(d + " deleted from " + this.getName());
        }
    }

    /**
     * função que edita disciplinas à ST de disciplinas da class Professor
     *
     * @param d    disciplina a editar
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
     * função que imprime as disciplinas da ST de disciplinas da class Professor
     */
    public void printAllDisciplines() {
        for (String key : disciplines.keys()) {
            Discipline d = this.disciplines.get(key);
            if (d == null) System.out.println("Doesn't exist any discipline");
            else System.out.println(d);
        }
    }

    /**
     * função que adiciona turmas à ST de turma da class Professor
     *
     * @param t turma a adicionar
     */
    public void addTurma(String t) {
        int count = 0, i = 0;
        for (Integer k : turmas.keys()) {
            i++;
            String dkey = turmas.get(k);
            if (dkey.compareTo(t) != 0)
                count = 0;
            else
                count++;
        }
        if (count == 0) {
            i++;
            turmas.put(i, t);
        }
    }

    /**
     * função que remove turmas à ST de turma da class Professor
     *
     * @param t turma a remover
     */
    public void removeTurma(String t) {
        for (Integer k : turmas.keys()) {
            String dkey = turmas.get(k);
            if (dkey.compareTo(t) == 0) {
                this.turmas.delete(k);
                System.out.println(dkey + " deleted from " + this.getName());
                return;
            }
        }
        System.out.println("Turma doesn't exist");
    }

    /**
     * função que imprime as turmas da ST de turma da class Professor
     */
    public void printAllTurmas() {
        for (Integer key : turmas.keys()) {
            String t = this.turmas.get(key);
            if (t == null) System.out.println("Doesn't exist any turma");
            else System.out.println(t);
        }
    }


    @Override
    public String toString() {
        return "Professor{" + "name=" + name + " , " + "number=" + number + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public SeparateChainingHashST<String, Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(SeparateChainingHashST<String, Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}