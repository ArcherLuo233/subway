package model;

public class Station {
    private String name;
    private Edge fstedge=new Edge();

    public Edge getFstedge() {
        return fstedge;
    }

    public void setFstedge(Edge fstedge) {
        this.fstedge = fstedge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
