package gopherco.configs.map;

import java.util.List;

public record Path(List<Point> path) {
    public Path(List<Point> path) {
        if (path == null) {
            throw new IllegalArgumentException();
        }
        this.path = path;
    }

    public void addPoint(Point point) {
        path.add(point);
    }
}
