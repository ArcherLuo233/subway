package model;

public class Edge {
    private Station stationB;
    private Line line;
    private Edge nxt;

    public Edge getNxt() {
        return nxt;
    }

    public void setNxt(Edge nxt) {
        this.nxt = nxt;
    }

    public Station getStationB() {
        return stationB;
    }

    public void setStationB(Station stationB) {
        this.stationB = stationB;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
