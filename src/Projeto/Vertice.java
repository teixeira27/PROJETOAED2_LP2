package Projeto;

public class Vertice implements Comparable<Vertice> {
    public Integer x;
    public Integer y;
    public Integer z;
    public String name;
    public Integer type ;
    public String description;


    public Vertice(Integer x, Integer y, Integer z, Integer type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
    }

    public Vertice(){}

    @Override
    public String toString() {
        return "Vertice{" + "x=" + x + " , " + "y=" + y + " , " + "z=" + z + " , " + "name=" + name + "}";
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Vertice vertice) {
       if(name.compareTo(vertice.getName())==0)
           return 0;
       else
           return 1;
    }
}
