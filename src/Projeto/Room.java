package Projeto;

public class Room extends Vertice {

    private Integer number;
    private Schedule schedule;

    public Room(Integer number, Integer x, Integer y, Integer z, Integer type) {
        super(x, y, z, type);
        this.setNumber(number);
    }

    /**
     * construtor da class room
     *
     * @param number numero da sala
     */
    public Room(Integer number) {
        this.setNumber(number);
    }

    /**
     * construtor por omissão da class room
     */
    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" + "number=" + number + "}";
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
}