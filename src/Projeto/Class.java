package Projeto;

public class Class {

    private String turma;
    private Date date;
    private Date dfinal;
    private Integer duracao;
    private Room room;
    private Discipline discipline;
    private Professor professor;

    /**
     * construtor de uma classe class
     * @param date data de começo da class
     * @param dfinal data final da aula
     * @param duracao duração da aula
     * @param room sala da aula
     * @param discipline disciplina da aula
     * @param professor professor da aula
     * @param turma turma da aula
     */
    public Class(Date date,Date dfinal, Integer duracao, Room room, Discipline discipline, Professor professor, String turma) {
        this.setTurma(turma);
        this.setDate(date);
        this.setDfinal(dfinal);
        this.setDuracao(duracao);
        this.setRoom(room);
        this.setDiscipline(discipline);
        this.setProfessor(professor);
    }

    @Override
    public String toString() {
        return "Class{" + "day=" + date.getDay() + " , " + "hour=" + date.getHour() + " , " + "minute="
                + date.getMinute() + " , " + "hourF=" + dfinal.getHour() + " , " + "minuteF="
                + dfinal.getMinute() + " , "+ "duracao=" + duracao + " , "
                + "room=" + room.getNumber() + " , " + "discipline=" + discipline.getName()
                + " , " + "professor=" + professor.getName() + " , " + "turma=" + turma + "}";
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Date getDfinal() {
        return dfinal;
    }

    public void setDfinal(Date dfinal) {
        this.dfinal = dfinal;
    }
}