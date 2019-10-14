package model;

public class Node {
    private Station st;
    private Node pre;
    private Line line=new Line();

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Station getSt() {
        return st;
    }

    public void setSt(Station st) {
        this.st = st;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }
}
