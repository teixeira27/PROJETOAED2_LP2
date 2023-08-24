package Projeto;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.lang.Comparable;

public class Date implements Comparable<Date> {

    private int day;
    private int hour;
    private int minute;

    /**
     * construtor de uma data dada pelo utilizador
     * @param day dia da semana
     * @param hour hora
     * @param minute minuto
     */
    public Date(int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * construtor de uma data à data atual da criaçao
     */
    public Date() {
        Calendar gregCalendar = new GregorianCalendar();
        this.day = gregCalendar.get(Calendar.DAY_OF_WEEK);
        this.hour = gregCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregCalendar.get(Calendar.MINUTE);
    }


    /**
     * função que calcula a data final de uma aula a partir da data inicial mais a sua duração
     * @param d data inicial da aula
     * @param duration duração da aula
     */
    public void dfim(Date d, int duration) {
        if (duration % 60 == 0) {
            int h = duration / 60;
            d.setDay(this.getDay());
            d.setHour(this.getHour() + h);
            d.setMinute(this.getMinute());
        } else {
            int h = duration / 60;
            int m = duration % 60;
            d.setDay(this.getDay());
            d.setHour(this.getHour() + h);
            d.setMinute(this.getMinute() + m);
        }
    }

    /**
     * função que compara uma data com a data atual
     * @param d data a comparar com a data atual
     * @return
     */
    public boolean beforeDate(Date d) {
        return this.compareTo(d) < 0;
    }

    /**
     *
     * @param d
     * @return
     */
    public boolean afterDate(Date d) {
        return false;
    }

    /**
     * função que compara uma data com a data atual
     * @param d data a comparar
     * @return
     */
    public int compareTo(Date d) {
        if (this.day == d.day && this.hour == d.hour && this.minute == d.minute) {
            return 0;
        } else if (this.day == d.day) {
            if (this.hour == d.hour) {
                return this.minute < d.minute ? -1 : 1;
            } else {
                return this.hour < d.hour ? -1 : 1;
            }
        } else {
            return this.day < d.day ? -1 : 1;
        }
    }

    @Override
    public String toString() {
        String day = null;
        int d = getDay();
        switch (d) {
            case 1:
                day = "sunday";
                break;
            case 2:
                day = "monday";
                break;
            case 3:
                day = "tuesday";
                break;
            case 4:
                day = "wednesday";
                break;
            case 5:
                day = "thursday";
                break;
            case 6:
                day = "friday";
                break;
            case 7:
                day = "saturday";
                break;
        }
        return "DATE(" + " day= " + day + ", hour= " + hour + ", minute= " + minute + " )";
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}