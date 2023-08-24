package Projeto;

import algs4.SeparateChainingHashST;

public class Discipline {

    private String name;
    private Integer ects;
    private String sigla;
    private SeparateChainingHashST<Integer, Student> students = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Professor> professors = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer,String> turmas = new SeparateChainingHashST<>();
    private Schedule schedule;

    /**
     * construtor da class discipline
     * @param name nome da disciplina
     * @param ects numero de creditos de uma disciplina
     * @param sigla sigla da disciplina
     */
    public Discipline(String name, Integer ects, String sigla) {
        this.setName(name);
        this.setEcts(ects);
        this.sigla=sigla;
    }

    /**
     * construtor por omissão da disciplina
     */
    public Discipline(){}

    /**
     * função que adiciona estudantes à ST de estudantes da class Discipline
     * @param s student a adicionar
     */
    public void addStudent(Student s) {
        if (this.getStudents().contains(s.getNumber()))
            return;
        this.getStudents().put(s.getNumber(), s);
    }

    /**
     * função que remove estudantes à ST de estudantes da class Discipline
     * @param s student a remover
     */
    public void removeStudent(Student s) {
        if (this.getStudents().contains(s.getNumber())) {
            this.getStudents().delete(s.getNumber());
            System.out.println(s+" deleted from "+this.getName());
        }
    }

    /**
     * função que edita estudantes na ST de estudantes da class Discipline
     * @param s student a editar
     * @param snew novo student editado
     */
    public void editStudent(Student s, Student snew) {
        if (this.getStudents().contains(s.getNumber())) {
            this.getStudents().delete(s.getNumber());
            this.getStudents().put(snew.getNumber(), snew);
        } else
            System.out.println("Can't edit this student because it doesn't exists");
    }

    /**
     * função que imprime os estudantes da ST da class Discipline
     */
    public void printAllStudents() {
        for (Integer key : getStudents().keys()) {
            Student s = this.getStudents().get(key);
            if (s == null) System.out.println("Doesn't exist any student");
            else System.out.println(s);
        }
    }

    /**
     * função que adiciona professores à ST de professores da class Discipline
     * @param p professor a adicionar
     */
    public void addProfessor(Professor p) {
        if (this.professors.contains(p.getNumber()))
            return;
        this.professors.put(p.getNumber(), p);
    }

    /**
     * função que remove professores à ST de professores da class Discipline
     * @param p professor a remover
     */
    public void removeProfessor(Professor p) {
        if (this.professors.contains(p.getNumber())) {
            this.professors.delete(p.getNumber());
            System.out.println(p+" deleted from "+this.getName());
        }
    }

    /**
     * função que edita professores na ST de professores da class Discipline
     * @param p professor a editar
     * @param pnew novo professor editado
     */
    public void editProfessor(Professor p, Professor pnew) {
        if (this.professors.contains(p.getNumber())) {
            this.professors.delete(p.getNumber());
            this.professors.put(pnew.getNumber(), pnew);
        } else
            System.out.println("Can't edit this professor because it doesn't exists");
    }

    /**
     * função que imprime os professores da ST de professores da class Discipline
     */
    public void printAllProfessors() {
        for (Integer key : professors.keys()) {
            Professor p = this.professors.get(key);
            if (p == null) System.out.println("Doesn't exist any professor");
            else System.out.println(p);
        }
    }

    /**
     * função que adiciona turmas à ST de turmas da class Discipline
     * @param t turma a adicionar
     */
    public void addTurma(String t) {
        int count=0,i=0;
        for (Integer k : turmas.keys()){
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
     * função que remove turmas à ST de turmas da class Discipline
     * @param t turma a remover
     */
    public void removeTurma (String t){
        for (Integer k : turmas.keys()){
            String dkey = turmas.get(k);
            if (dkey.compareTo(t) == 0){
                this.turmas.delete(k);
                System.out.println(dkey+" deleted from "+this.getName());
                return;
            }
        }
        System.out.println("Turma doesn't exist");
    }

    /**
     * função que imprime as turmas da ST de turmas da class Discipline
     */
    public void printAllTurmas(){
        for (Integer key : turmas.keys()) {
            String t = this.turmas.get(key);
            if (t == null) System.out.println("Doesn't exist any turma");
            else System.out.println(t);
        }
    }


    @Override
    public String toString() {
        return "Discipline{" + "name=" + name + " , " + "Ects=" + ects + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public SeparateChainingHashST<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(SeparateChainingHashST<Integer, Student> students) {
        this.students = students;
    }

    public SeparateChainingHashST<Integer, Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(SeparateChainingHashST<Integer, Professor> professors) {
        this.professors = professors;
    }
}