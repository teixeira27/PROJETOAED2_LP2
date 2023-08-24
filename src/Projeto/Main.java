package Projeto;

import algs4.*;

import java.io.*;

public class Main {

    private static String FILE_DELIMITER = ";";
    public static Universidade u = new Universidade();

    public static void main(String[] args) throws IOException {

        client_r6(u.schedules, u.students, u.professors, u.disciplines, u.rooms);
        /*client_r4(u.schedules, u.students, u.professors, u.disciplines, u.rooms);
        client_r7(u.schedules, u.students, u.professors, u.disciplines, u.rooms);
        client_r8(u.professors, u.disciplines, u.rooms);
        client_r9(u.schedules, u.students, u.professors);
        client_r5(u.schedules, u.students, u.professors, u.disciplines, u.rooms);*/

        //readGraph("src//data//graph.txt", u.students, u.professors, u.rooms);
    }

    /**
     * função que lê os estudantes de um txt e preenche a respetiva ST
     *
     * @param path     caminho para o ficheiro
     * @param students ST a preencher
     */
    public static void readStudentsTxt(String path, SeparateChainingHashST<Integer, Student> students) {
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMITER);

            Student s = new Student(fields[0], Integer.parseInt(fields[1]));

            if (!students.contains(s.getNumber()))
                students.put(s.getNumber(), s);
        }
    }

    /**
     * função que guarda os estudantes da ST para um txt
     *
     * @param path     caminho para o ficheiro
     * @param students ST a guardar
     */
    private static void printStudentsTxt(String path, SeparateChainingHashST<Integer, Student> students) {
        Out out = new Out(path);
        for (Integer key : students.keys()) {
            Student s = students.get(key);
            out.println(s);
        }
        out.close();
    }

    /**
     * função que imprime  os estudantes de uma ST
     *
     * @param students ST a imprimir
     */
    private static void printAllStudents(SeparateChainingHashST<Integer, Student> students) {
        for (Integer key : students.keys()) {
            Student s = students.get(key);
            if (s == null) System.out.println("Doesn't exist any student");
            else System.out.println(s);
        }
    }

    /**
     * função que lê os professores de um txt e preenche a respetiva ST
     *
     * @param path       caminho para o ficheiro
     * @param professors ST a preencher
     */
    public static void readProfessorsTxt(String path, SeparateChainingHashST<Integer, Professor> professors) {
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMITER);

            Professor p = new Professor(fields[0], Integer.parseInt(fields[1]));

            if (!professors.contains(p.getNumber()))
                professors.put(p.getNumber(), p);
        }
    }

    /**
     * função que guarda os professores da ST para um txt
     *
     * @param path       caminho para o ficheiro
     * @param professors ST a guardar
     */
    private static void printProfessorsTxt(String path, SeparateChainingHashST<Integer, Professor> professors) {
        Out out = new Out(path);
        for (Integer key : professors.keys()) {
            Professor p = professors.get(key);
            out.println(p);
        }
        out.close();
    }

    /**
     * função que imprime os professores de uma ST
     *
     * @param professors ST a imprimir
     */
    private static void printAllProfessors(SeparateChainingHashST<Integer, Professor> professors) {
        for (Integer key : professors.keys()) {
            Professor p = professors.get(key);
            if (p == null) System.out.println("Doesn't exist any professor");
            else System.out.println(p);
        }
    }

    /**
     * função que guarda as disciplinas para um txt
     *
     * @param path        caminho para o ficheiro
     * @param disciplines ST a preencher
     */
    private static void readDisciplinesTxt(String path, SeparateChainingHashST<String, Discipline> disciplines) {
        int count = 0, i = 0;
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMITER);

            Discipline d = new Discipline(fields[0], Integer.parseInt(fields[1]), fields[2]);

            if (!disciplines.contains(d.getSigla()))
                disciplines.put(d.getSigla(), d);
        }
    }

    /**
     * função que lê as disciplinas de um txt e preenche a respetiva ST
     *
     * @param path        caminho para o ficheiro
     * @param disciplines ST a guardar
     */
    private static void printDisciplinesTxt(String path, SeparateChainingHashST<String, Discipline> disciplines) {
        Out out = new Out(path);
        for (String key : disciplines.keys()) {
            Discipline d = disciplines.get(key);
            out.println(d);
        }
        out.close();
    }

    /**
     * função que imprime as disciplinas de uma ST
     *
     * @param disciplines ST a imprimir
     */
    private static void printAllDisciplines(SeparateChainingHashST<String, Discipline> disciplines) {
        for (String key : disciplines.keys()) {
            Discipline d = disciplines.get(key);
            if (d == null) System.out.println("Doesn't exist any discipline");
            else System.out.println(d);
        }
    }

    /**
     * função que lê os salas de um txt e preenche a respetiva ST
     *
     * @param path  caminho para o ficheiro
     * @param rooms ST a preencher
     */
    public static void readRoomsTxt(String path, SeparateChainingHashST<Integer, Room> rooms) {
        int count = 0, i = 0;
        In in = new In(path);
        while (!in.isEmpty()) {
            String line = in.readLine();
            Room r = new Room(Integer.parseInt(line));

            if (!rooms.contains(r.getNumber()))
                rooms.put(r.getNumber(), r);
        }
    }

    /**
     * função que imprime as salas de uma ST
     *
     * @param path  caminho para o ficheiro
     * @param rooms ST a guardar
     */
    private static void printRoomsTxt(String path, SeparateChainingHashST<Integer, Room> rooms) {
        Out out = new Out(path);
        for (Integer key : rooms.keys()) {
            Room r = rooms.get(key);
            out.println(r);
        }
        out.close();
    }

    /**
     * função que imprime todas as salas
     *
     * @param rooms ST rooms
     */
    private static void printAllRooms(SeparateChainingHashST<Integer, Room> rooms) {
        for (Integer room : rooms.keys()) {
            Room r = rooms.get(room);
            System.out.println(r);
        }
    }

    /**
     * função que lê horários de um txt
     *
     * @param path        caminho para o ficheiro
     * @param rooms       ST rooms
     * @param schedules   ST schedules
     * @param professors  ST professors
     * @param students    ST students
     * @param disciplines ST disciplines
     */
    private static void readScheduleTxt(String path, SeparateChainingHashST<Integer, Room> rooms, SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<String, Discipline> disciplines) {
        int count = 0, count1 = 0;
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMITER);


            int id = Integer.parseInt(fields[0]);
            String name = fields[1];
            Date date = new Date(Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
            Date dfinal = new Date();
            Integer duration = Integer.parseInt(fields[5]);
            date.dfim(dfinal, duration);

            Room r = new Room();
            for (Integer room : rooms.keys()) {
                Room r1 = rooms.get(room);
                if (r1.getNumber().equals(Integer.parseInt(fields[6])))
                    r = r1;
            }
            if (r.getSchedule() == null) {
                Schedule sch = new Schedule(r.getNumber());
                r.setSchedule(sch);
            }

            Discipline d = new Discipline();
            for (String key : disciplines.keys()) {
                d = disciplines.get(key);
                if (d.getName().compareTo(fields[7]) == 0)
                    break;
            }
            String turma = fields[8];

            if (id == 1) {                     //id 1 para alunos   2 para professores
                Student s = new Student();
                for (Integer key : students.keys()) {
                    s = students.get(key);
                    if (s.getName().compareTo(name) == 0)
                        break;
                }
                if (s.getSchedule() == null) {
                    Schedule sch = new Schedule(s.getNumber());
                    s.setSchedule(sch);
                }
                Professor p = new Professor();
                for (Integer key : professors.keys()) {
                    p = professors.get(key);
                    if (p.getName().compareTo(fields[9]) == 0)
                        break;
                }

                Class c = new Class(date, dfinal, duration, r, d, p, turma);

                for (Date dc : s.getSchedule().getClasses().keys()) {
                    Class c1 = s.getSchedule().getClasses().get(dc);
                    if (c1.getDate().compareTo(c.getDate()) == 0) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {                     //se count=0, adicionamos a nova class
                    s.getSchedule().addClass(c);
                    s.getSchedule().addDiscipline(d);
                    s.getSchedule().addRoom(r);
                    s.getSchedule().setId(id);
                    s.addDiscipline(d);
                    s.addTurma(turma);

                    d.addStudent(s);

                    if (p.getSchedule() != null) {
                        p.getSchedule().addClass(c);
                        p.getSchedule().addDiscipline(d);
                        p.getSchedule().addRoom(r);
                        p.getSchedule().setId(id + 1);
                        p.addTurma(turma);
                        p.addDiscipline(d);

                        d.addProfessor(p);
                    } else {
                        Schedule sch1 = new Schedule(p.getNumber());
                        p.setSchedule(sch1);

                        p.getSchedule().addClass(c);
                        p.getSchedule().addDiscipline(d);
                        p.getSchedule().addRoom(r);
                        p.getSchedule().setId(id + 1);
                        p.addTurma(turma);
                        p.addDiscipline(d);

                        d.addProfessor(p);
                        if (!schedules.contains(p.getNumber()))
                            schedules.put(p.getNumber(), p.getSchedule());
                    }
                }

                for (Date dr : r.getSchedule().getClasses().keys()) {
                    Class c1 = r.getSchedule().getClasses().get(dr);
                    if (c1.getDate().compareTo(c.getDate()) == 0) {
                        count1++;
                        break;
                    }
                }
                if (count1 == 0) {
                    r.getSchedule().addClass(c);
                    r.getSchedule().addDiscipline(d);
                    r.getSchedule().addRoom(r);
                    r.getSchedule().setId(3);
                    if (!schedules.contains(r.getNumber()))
                        schedules.put(r.getNumber(), r.getSchedule());
                }
                if (!schedules.contains(s.getNumber()))
                    schedules.put(s.getNumber(), s.getSchedule());
            }
            if (id == 2) {
                Professor p1 = new Professor();
                for (Integer key : professors.keys()) {
                    p1 = professors.get(key);
                    if (p1.getName().compareTo(name) == 0)
                        break;
                }

                if (p1.getSchedule() == null) {
                    Schedule sch = new Schedule(p1.getNumber());
                    p1.setSchedule(sch);
                }

                count = 0;
                Class c = new Class(date, dfinal, duration, r, d, p1, turma);

                for (Date dc : p1.getSchedule().getClasses().keys()) {
                    Class c1 = p1.getSchedule().getClasses().get(dc);
                    if (c1.getDate().compareTo(c.getDate()) == 0) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {                     //se count=0, adicionamos a nova class
                    p1.getSchedule().addClass(c);
                    p1.getSchedule().addDiscipline(d);
                    p1.getSchedule().addRoom(r);
                    p1.getSchedule().setId(id);
                    p1.addTurma(turma);
                    p1.addDiscipline(d);
                    d.addProfessor(p1);
                }

                for (Date dc : r.getSchedule().getClasses().keys()) {
                    Class c1 = r.getSchedule().getClasses().get(dc);
                    if (c1.getDate().compareTo(c.getDate()) == 0) {
                        count1++;
                        break;
                    }
                }
                if (count1 == 0) {
                    r.getSchedule().addClass(c);
                    r.getSchedule().addDiscipline(d);
                    r.getSchedule().addRoom(r);
                    r.getSchedule().setId(3);
                    if (!schedules.contains(r.getNumber()))
                        schedules.put(r.getNumber(), r.getSchedule());
                }
                if (!schedules.contains(p1.getNumber()))
                    schedules.put(p1.getNumber(), p1.getSchedule());
            }
        }
    }

    /**
     * função que guarda todos os horários num txt
     *
     * @param path      caminho para o ficheiro
     * @param schedules ST schedules
     */
    private static void printSchedulesTxt(String path, SeparateChainingHashST<Integer, Schedule> schedules) {
        Out out = new Out(path);
        for (Integer key : schedules.keys()) {
            Schedule s = schedules.get(key);
            out.println(s);
        }
        out.close();
    }

    /**
     * função que imprime todos os horários
     *
     * @param schedules ST schedules
     */
    private static void printAllSchedules(SeparateChainingHashST<Integer, Schedule> schedules) {
        for (Integer d : schedules.keys()) {
            Schedule sch = schedules.get(d);
            System.out.println(sch);
        }
    }

    /**
     * função que guarda as aulas num txt
     *
     * @param path      caminho para o ficheiro
     * @param schedules ST schedules
     */
    private static void printClassesTxt(String path, SeparateChainingHashST<Integer, Schedule> schedules) {
        Out out = new Out(path);
        for (Integer key : schedules.keys()) {
            Schedule s = schedules.get(key);
            out.println(s);
            for (Date d : s.getClasses().keys()) {
                Class c = s.getClasses().get(d);
                out.println(c);
            }
        }
        out.close();
    }

    /**
     * função que imprime todas as aulas
     *
     * @param schedules ST schedules
     */
    private static void printAllClasses(SeparateChainingHashST<Integer, Schedule> schedules) {
        for (Integer d : schedules.keys()) {
            Schedule c = schedules.get(d);
            System.out.println(c);
            c.printAllClasses();
        }
    }

    /**
     * função que imprime o status da rede no momento
     *
     * @param schedules  ST schedules
     * @param students   ST students
     * @param professors ST professors
     */
    private static void now(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors) {
        Date dnow = new Date();
        System.out.println("Aulas a ocorrer neste momento: ");
        for (Integer d : schedules.keys()) {
            Schedule sch = schedules.get(d);
            for (Date d1 : sch.getClasses().keys()) {
                Class c = sch.getClasses().get(d1);
                if (dnow.getDay().equals(d1.getDay())) {
                    if (dnow.compareTo(d1) >= 0 && dnow.compareTo(c.getDfinal()) <= 0) {
                        System.out.println(sch);
                        System.out.println("\t" + c);
                    }
                }
            }
        }
        System.out.println("\nProfessores disponiveis neste momento: ");
        for (Integer key : professors.keys()) {
            Professor p = professors.get(key);
            if (p.getSchedule() != null) {
                Schedule sch = p.getSchedule();
                for (Date d1 : sch.getClasses().keys()) {
                    Class c = sch.getClasses().get(d1);
                    if (dnow.getDay().equals(d1.getDay())) {
                        if (dnow.compareTo(d1) >= 0 && dnow.compareTo(c.getDfinal()) <= 0) {
                            break;
                        } else {
                            System.out.println(p);
                            break;
                        }
                    } else {
                        System.out.println(p);
                        break;
                    }
                }
            }
        }
    }

    /**
     * função que imprime todos os professores de uma disciplina
     *
     * @param discipline  disciplina em questão
     * @param disciplines ST professors
     */
    private static void printAllProfessorsByDiscipline(Discipline discipline, SeparateChainingHashST<String, Discipline> disciplines) {
        for (String key : disciplines.keys()) {
            Discipline d = disciplines.get(key);
            if (d.getName().compareTo(discipline.getName()) == 0) {
                d.printAllProfessors();
                break;
            }
        }
    }

    /**
     * função que imprime todas as turmas de um professor
     *
     * @param professor  professor em questão
     * @param professors ST professors
     */
    private static void printAllTurmasByProfessor(Professor professor, SeparateChainingHashST<Integer, Professor> professors) {
        for (int key : professors.keys()) {
            Professor p = professors.get(key);
            if (p.getNumber().equals(professor.getNumber())) {
                System.out.println(professor);
                System.out.println("Turmas: ");
                professor.printAllTurmas();
            }
        }
    }

    /**
     * função que imprime todas as salas vazias entre 2 datas
     *
     * @param d1    data 1 a testar
     * @param d2    data 2 a testar
     * @param rooms ST rooms
     */
    private static void printAllEmptyRooms(Date d1, Date d2, SeparateChainingHashST<Integer, Room> rooms) {
        int count;
        System.out.println("Salas vazias: ");
        for (Integer i : rooms.keys()) {
            Room r = rooms.get(i);
            count = 0;
            for (Date dc : r.getSchedule().getClasses().keys()) {
                Class c = r.getSchedule().getClasses().get(dc);
                if (d1.compareTo(c.getDate()) <= 0 && d2.compareTo(c.getDfinal()) >= 0)
                    count++;
            }
            if (count == 0)
                System.out.println(r);
        }
    }

    /**
     * função que imprime as aulas por data e sala
     *
     * @param d1    data 1 a testar
     * @param d2    data 2 a testar
     * @param rooms ST rooms
     * @param room  sala a testar
     */
    private static void printClassesByDateAndRoom(Date d1, Date d2, SeparateChainingHashST<Integer, Room> rooms, Room room) {
        System.out.println("A sala " + room.getNumber() + " está ocupada nas datas " + d1 + " - " + d2 + " com as aulas:");
        for (Integer i : rooms.keys()) {
            Room r = rooms.get(i);
            for (Date dc : r.getSchedule().getClasses().keys()) {
                Class c = r.getSchedule().getClasses().get(dc);
                if (c.getRoom().getNumber().equals(room.getNumber())) {
                    if (d1.compareTo(c.getDate()) <= 0 && d2.compareTo(c.getDfinal()) >= 0) {
                        System.out.println(c);
                    }
                }
            }
        }
    }

    /**
     * função que remove um professor da respetiva ST
     *
     * @param professors ST professors
     * @param p          professor a remover
     * @param schedules  ST schedules
     */
    private static void removeProfessor(SeparateChainingHashST<Integer, Professor> professors, Professor p, SeparateChainingHashST<Integer, Schedule> schedules) {
        Out out = new Out("src//data//deletedProfessors.txt");
        for (String s : p.getDisciplines().keys()) {
            Discipline d = p.getDisciplines().get(s);
            d.removeProfessor(p);
        }
        for (Date s : p.getSchedule().getClasses().keys()) {
            Class c = p.getSchedule().getClasses().get(s);
            p.getSchedule().removeClass(c);
        }
        schedules.delete(p.getNumber());
        professors.delete(p.getNumber());
        out.println(p);
    }

    /**
     * função que remove um student da respetiva ST
     *
     * @param students  ST students
     * @param st        student a remover
     * @param schedules ST schedules
     */
    private static void removeStudent(SeparateChainingHashST<Integer, Student> students, Student st, SeparateChainingHashST<Integer, Schedule> schedules) {
        Out out = new Out("src//data//deletedStudents.txt");
        for (String s : st.getDisciplines().keys()) {
            Discipline d = st.getDisciplines().get(s);
            d.removeStudent(st);
        }
        for (Date s : st.getSchedule().getClasses().keys()) {
            Class c = st.getSchedule().getClasses().get(s);
            st.getSchedule().removeClass(c);
        }
        schedules.delete(st.getNumber());
        students.delete(st.getNumber());
        out.println(st);
    }

    /**
     * função que remove uma discipline da respetiva ST
     *
     * @param schedules   ST schedules
     * @param disciplines ST disciplines
     * @param d           discipline a remover
     */
    private static void removeDiscipline(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<String, Discipline> disciplines, Discipline d) {
        Out out = new Out("src//data//deletedDisciplines.txt");
        for (Integer i : d.getStudents().keys()) {
            Student s = d.getStudents().get(i);
            s.removeDiscipline(d);
        }
        for (Integer i : d.getProfessors().keys()) {
            Professor p = d.getProfessors().get(i);
            p.removeDiscipline(d);
        }
        for (Integer s : schedules.keys()) {
            Schedule sch = schedules.get(s);
            sch.removeDiscipline(d);
        }
        disciplines.delete(d.getSigla());
        out.println(d);
    }

    /**
     * função que remove uma sala da respetiva ST
     *
     * @param schedules ST schedules
     * @param rooms     ST rooms
     * @param r         sala a remover
     */
    private static void removeRoom(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Room> rooms, Room r) {
        Out out = new Out("src//data//deletedRooms.txt");
        for (Integer s : schedules.keys()) {
            Schedule sch = schedules.get(s);
            sch.removeRoom(r);
        }
        rooms.delete(r.getNumber());
        out.println(r);
    }

    /**
     * função que remove uma class da respetiva ST
     *
     * @param schedules ST schedules
     * @param c         class a remover
     */
    private static void removeClass(SeparateChainingHashST<Integer, Schedule> schedules, Class c) {
        Out out = new Out("src//data//deletedClasses.txt");
        for (Integer s : schedules.keys()) {
            Schedule sch = schedules.get(s);
            sch.removeClass(c);
        }
        out.println(c);
    }

    /**
     * função que remove um schedule da respetiva ST
     *
     * @param schedules ST schedules
     * @param sch       schedule a remover
     */
    private static void removeSchedule(SeparateChainingHashST<Integer, Schedule> schedules, Schedule sch) {
        Out out = new Out("src//data//deletedSchedules.txt");
        schedules.delete(sch.getNumber());
        for (Date s : sch.getClasses().keys()) {
            Class c = sch.getClasses().get(s);
            sch.removeClass(c);
        }
        out.println(sch);
    }

    /**
     * função que lê um grafo de um txt
     * @param path caminho do  ficheiro a ler
     * @param students ST de students
     * @param professors ST de professors
     * @param rooms ST  de rooms
     * @throws IOException
     */

    public static void readGraph(String path, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<Integer, Room> rooms) throws IOException {

        int i = 0;

        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMITER);

            Student s;
            Professor p;
            Room r;
            Vertice v;
            if (Integer.parseInt(fields[4]) == 1) {  //Aluno
                for (Integer key : students.keys()) {
                    s = students.get(key);
                    if (s.getNumber().compareTo(Integer.parseInt(fields[0])) == 0) {
                        s.setName(String.valueOf(s.getNumber()));
                        s.setX(Integer.parseInt(fields[1]));
                        s.setY(Integer.parseInt(fields[2]));
                        s.setZ(Integer.parseInt(fields[3]));
                        s.setType(Integer.parseInt(fields[4]));
                        s.setDescription(fields[5]);
                        if (!MainGenericPointRenderer.u.st.contains(i)) {
                            MainGenericPointRenderer.u.st.put(i, s);
                            i++;
                        }
                    }
                }
            } else if (Integer.parseInt(fields[4]) == 2) {  //Professor
                for (Integer key : professors.keys()) {
                    p = professors.get(key);
                    if (p.getNumber().compareTo(Integer.parseInt(fields[0])) == 0) {
                        p.setName(String.valueOf(p.getNumber()));
                        p.setX(Integer.parseInt(fields[1]));
                        p.setY(Integer.parseInt(fields[2]));
                        p.setZ(Integer.parseInt(fields[3]));
                        p.setType(Integer.parseInt(fields[4]));
                        p.setDescription(fields[5]);
                        if (!MainGenericPointRenderer.u.st.contains(i)) {
                            MainGenericPointRenderer.u.st.put(i, p);
                            i++;
                        }
                    }
                }

            } else if (Integer.parseInt(fields[4]) == 3) { //Sala
                for (Integer key : rooms.keys()) {
                    r = rooms.get(key);
                    if (r.getNumber().compareTo(Integer.parseInt(fields[0])) == 0) {
                        r.setName(String.valueOf(r.getNumber()));
                        r.setX(Integer.parseInt(fields[1]));
                        r.setY(Integer.parseInt(fields[2]));
                        r.setZ(Integer.parseInt(fields[3]));
                        r.setType(Integer.parseInt(fields[4]));
                        r.setDescription(fields[5]);
                        if (!MainGenericPointRenderer.u.st.contains(i)) {
                            MainGenericPointRenderer.u.st.put(i, r);
                            i++;
                        }
                    }
                }
            } else if (Integer.parseInt(fields[4]) == 4) { //Ponto de Passagem
                v = new Vertice(Integer.parseInt(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                v.setName(fields[0]);
                v.setDescription(fields[5]);
                if (!MainGenericPointRenderer.u.st.contains(i)) {
                    MainGenericPointRenderer.u.st.put(i, v);
                    i++;
                }
            }
        }
        for (Integer k : MainGenericPointRenderer.u.st.keys()) {
            Vertice v = MainGenericPointRenderer.u.st.get(k);
            System.out.println(k + ": " + v);
        }
        System.out.println();

        MainGenericPointRenderer.u.g = new EdgeWeightedGraph_Projeto(MainGenericPointRenderer.u.st.size());
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(4, 5, 200, 10));
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(4, 8, 100, 15));
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(5, 6, 50, 5));
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(6, 0, 10, 2));
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(6, 7, 15, 7));
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(8, 9, 5, 1));
        MainGenericPointRenderer.u.g.addEdge(new Edge_Projeto(9, 0, 5, 1));

        System.out.println(MainGenericPointRenderer.u.g);

        /*Out out = new Out("src//data//sg.txt");
        for (int v = 0; v < g.V(); v++) {
            out.print(v + ";");
            for (Edge e : g.adj(v)) {
                int w = e.other(v);
                out.print(w + ";");
            }
            out.println();
        }
        out.close();*/

    }

    /**
     * função cliente para testar o requisito 4
     *
     * @param schedules   ST schedules
     * @param students    ST students
     * @param professors  ST professors
     * @param disciplines ST disciplines
     * @param rooms       ST rooms
     */
    private static void client_r4(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<String, Discipline> disciplines, SeparateChainingHashST<Integer, Room> rooms) {
        System.out.println("\n----- Validar a consistência: -----");
        System.out.print("----- Professores: -----\n");
        for (String s : disciplines.keys()) {
            Discipline d = disciplines.get(s);
            if (!d.getProfessors().isEmpty()) {
                System.out.println(d);
                for (Integer i : d.getProfessors().keys()) {
                    Professor p = d.getProfessors().get(i);
                    if (professors.contains(p.getNumber()))
                        System.out.println("\t" + p + " check");
                    else
                        System.out.println("\t" + p + "not check");
                }
            }
        }
        System.out.print("\n----- Alunos: -----\n");
        for (String s : disciplines.keys()) {
            Discipline d = disciplines.get(s);
            if (!d.getStudents().isEmpty()) {
                System.out.println(d);
                for (Integer i : d.getStudents().keys()) {
                    Student st = d.getStudents().get(i);
                    if (students.contains(st.getNumber()))
                        System.out.println("\t" + st + " check");
                    else
                        System.out.println("\t" + st + "not check");
                }
            }
        }
        System.out.print("\n----- Disciplinas: -----\n");
        for (Integer i : students.keys()) {
            Student st = students.get(i);
            if (!st.getDisciplines().isEmpty()) {
                System.out.println(st);
                for (String s : st.getDisciplines().keys()) {
                    Discipline d = st.getDisciplines().get(s);
                    if (disciplines.contains(d.getSigla()))
                        System.out.println("\t" + d + " check");
                    else
                        System.out.println("\t" + d + "not check");
                }
            }
        }
        System.out.println();
        for (Integer i : professors.keys()) {
            Professor p = professors.get(i);
            if (!p.getDisciplines().isEmpty()) {
                System.out.println(p);
                for (String s : p.getDisciplines().keys()) {
                    Discipline d = p.getDisciplines().get(s);
                    if (disciplines.contains(d.getSigla()))
                        System.out.println("\t" + d + " check");
                    else
                        System.out.println("\t" + d + "not check");
                }
            }
        }
        System.out.println();
        for (Integer i : schedules.keys()) {
            Schedule sch = schedules.get(i);
            if (!sch.getDisciplines().isEmpty()) {
                System.out.println(sch);
                for (String s : sch.getDisciplines().keys()) {
                    Discipline d = sch.getDisciplines().get(s);
                    if (disciplines.contains(d.getSigla()))
                        System.out.println("\t" + d + " check");
                    else
                        System.out.println("\t" + d + "not check");
                }
            }
        }
        System.out.print("\n----- Rooms: -----\n");
        for (Integer i : schedules.keys()) {
            Schedule sch = schedules.get(i);
            if (!sch.getRooms().isEmpty()) {
                System.out.println(sch);
                for (Integer s : sch.getRooms().keys()) {
                    Room r = sch.getRooms().get(s);
                    if (rooms.contains(r.getNumber()))
                        System.out.println("\t" + r + " check");
                    else
                        System.out.println("\t" + r + "not check");
                }
            }
        }
    }

    /**
     * função cliente para testar o requisito 5
     *
     * @param schedules   ST schedules
     * @param students    ST students
     * @param professors  ST professors
     * @param disciplines ST disciplines
     * @param rooms       ST rooms
     */
    private static void client_r5(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<String, Discipline> disciplines, SeparateChainingHashST<Integer, Room> rooms) {          // garantir a remoção total e o seu dump para txt
        Professor p = professors.get(29346);
        System.out.println("\n----- Antes da remoção de " + p + " -----");
        printAllProfessors(professors);
        System.out.println();
        removeProfessor(professors, p, schedules);
        System.out.println("\nApós da remoção de " + p);
        printAllProfessors(professors);

        Student s = students.get(36911);
        System.out.println("\n----- Antes da remoção de " + s + " -----");
        printAllStudents(students);
        System.out.println();
        removeStudent(students, s, schedules);
        System.out.println("\nApós da remoção de " + s);
        printAllStudents(students);

        Room r = rooms.get(108);
        System.out.println("\n----- Antes da remoção de " + r + " -----");
        printAllRooms(rooms);
        System.out.println();
        removeRoom(schedules, rooms, r);
        System.out.println("\nApós da remoção de " + r);
        printAllRooms(rooms);

        Discipline d = disciplines.get("SO");
        System.out.println("\n----- Antes da remoção de " + d + " -----");
        printAllDisciplines(disciplines);
        System.out.println();
        removeDiscipline(schedules, disciplines, d);
        System.out.println("\nApós da remoção de " + d);
        printAllDisciplines(disciplines);

        Schedule sch = schedules.get(209);
        System.out.println("\n----- Antes da remoção de " + sch + " -----");
        printAllSchedules(schedules);
        System.out.println();
        removeSchedule(schedules, sch);
        System.out.println("\nApós da remoção de " + sch);
        printAllSchedules(schedules);

        System.out.println();
        Date date = new Date(4, 19, 0);
        Class c = schedules.get(109).getClasses().get(date);
        System.out.println("\n----- Antes da remoção de " + c + " -----");
        printAllClasses(schedules);
        System.out.println();
        removeClass(schedules, c);
        System.out.println("\nApós da remoção de " + c);
        printAllClasses(schedules);
        System.out.println();
    }

    /**
     * função cliente para testar o requisito 6
     *
     * @param schedules   ST schedules
     * @param students    ST students
     * @param professors  ST professors
     * @param disciplines ST disciplines
     * @param rooms       ST rooms
     */
    private static void client_r6(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<String, Discipline> disciplines, SeparateChainingHashST<Integer, Room> rooms) {
        System.out.println("\n----- Toda a informação carregada a partir de ficheiros txt: -----\n");
        readStudentsTxt("src//data//students.txt", students);
        printAllStudents(students);
        System.out.println();

        readProfessorsTxt("src//data//professors.txt", professors);
        printAllProfessors(professors);
        System.out.println();

        readDisciplinesTxt("src//data//disciplines.txt", disciplines);
        printAllDisciplines(disciplines);
        System.out.println();

        readRoomsTxt("src//data//rooms.txt", rooms);
        printAllRooms(rooms);
        System.out.println();

        readScheduleTxt("src//data//schedules.txt", rooms, schedules, professors, students, disciplines);
        printAllSchedules(schedules);
        System.out.println();

        printAllClasses(schedules);
        System.out.println();
    }

    /**
     * função cliente para testar o requisito 7
     *
     * @param schedules   ST schedules
     * @param students    ST students
     * @param professors  ST professors
     * @param disciplines ST disciplines
     * @param rooms       ST rooms
     */
    private static void client_r7(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<String, Discipline> disciplines, SeparateChainingHashST<Integer, Room> rooms) {                   // guardar toda a informação em txt
        String p1 = "src//data//printStudents.txt";
        String p2 = "src//data//printProfessors.txt";
        String p3 = "src//data//printDisciplines.txt";
        String p4 = "src//data//printClasses.txt";
        String p5 = "src//data//printSchedules.txt";
        String p6 = "src//data//printRooms.txt";
        printStudentsTxt(p1, students);
        printProfessorsTxt(p2, professors);
        printDisciplinesTxt(p3, disciplines);
        printClassesTxt(p4, schedules);
        printSchedulesTxt(p5, schedules);
        printRoomsTxt(p6, rooms);
    }

    /**
     * função cliente para testar o requisito 8
     *
     * @param professors  ST professors
     * @param disciplines ST disciplines
     * @param rooms       ST rooms
     */
    private static void client_r8(SeparateChainingHashST<Integer, Professor> professors, SeparateChainingHashST<String, Discipline> disciplines, SeparateChainingHashST<Integer, Room> rooms) {           //pesquisas
        System.out.println("\n----- Todas as turmas de um professor: -----");
        Professor p = professors.get(31238);
        printAllTurmasByProfessor(p, professors);

        System.out.println("\n----- Todas as salas livres num determinado horário: -----");
        Date d1 = new Date(4, 19, 0);
        Date d2 = new Date(4, 21, 0);
        printAllEmptyRooms(d1, d2, rooms);

        System.out.println("\n----- A ocupação de uma sala entre datas: -----");
        Date d3 = new Date(2, 19, 0);
        Date d4 = new Date(6, 21, 0);
        Room r = new Room(209);
        printClassesByDateAndRoom(d3, d4, rooms, r);

        System.out.println("\n----- Todos os professores de uma unidade curricular: -----");
        Discipline dis = disciplines.get("LP2");
        System.out.println(dis);
        printAllProfessorsByDiscipline(dis, disciplines);
    }

    /**
     * função cliente para testar o requisito 9
     *
     * @param schedules  ST schedules
     * @param students   ST students
     * @param professors ST professors
     */
    private static void client_r9(SeparateChainingHashST<Integer, Schedule> schedules, SeparateChainingHashST<Integer, Student> students, SeparateChainingHashST<Integer, Professor> professors) {
        System.out.println("\n----- Now: -----");
        now(schedules, students, professors);
    }
}