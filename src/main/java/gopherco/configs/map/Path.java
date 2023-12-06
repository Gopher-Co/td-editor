package gopherco.configs.map;

import java.util.List;

public record Path(List<Point> path) {
    public Path {
        if (path == null) {
            throw new IllegalArgumentException();
        }
    }

    public void addPoint(Point point) {
        path.add(point);
    }
}
