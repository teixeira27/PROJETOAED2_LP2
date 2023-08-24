package Projeto;

import algs4.SeparateChainingHashST;

public class Student extends Vertice {

    private Integer number;
    private Schedule schedule;
    private SeparateChainingHashST<String, Discipline> disciplines = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, String> turmas = new SeparateChainingHashST<>();


    public Student(String name, Integer number, Integer x, Integer y, Integer z, Integer type) {
        super(x, y, z, type);
        this.setName(name);
        this.setNumber(number);
    }

    /**
     * construtor da class student
     *
     * @param name   nome do estudante
     * @param number numero do estudante
     */
    public Student(String name, Integer number) {
        this.setName(name);
        this.setNumber(number);
    }

    /**
     * construtor por omissão do estudante
     */
    public Student() {
    }


    /**
     * função que adiciona disciplinas à ST de disciplinas da class Student
     *
     * @param d disciplina a adicionar
     */
    public void addDiscipline(Discipline d) {
        if (this.getDisciplines().contains(d.getSigla()))
            return;
        this.getDisciplines().put(d.getSigla(), d);
    }

    /**
     * função que remove disciplinas à ST de disciplinas da class Student
     *
     * @param d disciplina a remover
     */
    public void removeDiscipline(Discipline d) {
        if (this.getDisciplines().contains(d.getSigla())) {
            this.getDisciplines().delete(d.getSigla());
            System.out.println(d + " deleted from " + this.getName());
        }
    }

    /**
     * função que edita disciplinas da ST de disciplinas da class Student
     *
     * @param d    disciplina a editar
     * @param dnew disciplina editada
     */
    public void editDiscipline(Discipline d, Discipline dnew) {
        if (this.getDisciplines().contains(d.getSigla())) {
            this.getDisciplines().delete(d.getSigla());
            this.getDisciplines().put(dnew.getSigla(), dnew);
        } else
            System.out.println("Can't edit this discipline because it doesn't exists");
    }

    /**
     * função que imprime as disciplinas da ST de disciplinas da class Student
     */
    public void printAllDisciplines() {
        for (String key : getDisciplines().keys()) {
            Discipline d = this.getDisciplines().get(key);
            if (d == null) System.out.println("Doesn't exist any discipline");
            else System.out.println(d);
        }
    }

    /**
     * função que adiciona turmas à ST de turmas da class Student
     *
     * @param t
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
     * função que remove turmas à ST de turmas da class Student
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
     * função que imprime as turmas da ST de turmas da class Student
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
        return "Student{" + "name=" + name + " , " + "number=" + number + "}";
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

    public SeparateChainingHashST<Integer, String> getTurmas() {
        return turmas;
    }

    public void setTurmas(SeparateChainingHashST<Integer, String> turmas) {
        this.turmas = turmas;
    }
}