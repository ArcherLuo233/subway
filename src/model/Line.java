package model;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<String> line=new ArrayList<String>();

    public List<String> getLine() {
        return line;
    }

    public void setLine(List<String> line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
