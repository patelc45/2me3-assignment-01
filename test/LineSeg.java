public class LineSeg extends AbstractElement implements CollisionDetector {
    private Point begin, end;

    public LineSeg() {
        this(new Point(), new Point(1,1));
    }

    public LineSeg(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
        numberOfInstances++;
    }

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }
    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    @Override
    public boolean intersect(Point p) {
        return p.intersect(this);
    }

    @Override
    public boolean intersect(LineSeg l) {
        float x1 = begin.getX(), y1 = begin.getY();
        float x2 = end.getX(), y2 = end.getY();
        float x3 = l.begin.getX(), y3 = l.begin.getY();
        float x4 = l.end.getX(), y4 = l.end.getY();

        float den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (Math.abs(den) < 1e-6) return false;

        float t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        float u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;

        return t >= 0 && t <= 1 && u >= 0 && u <= 1;
    }

    @Override
    public boolean intersect(Rectangle r) {
        if (begin.intersect(r) || end.intersect(r)) return true;
        LineSeg[] edges = {
            new LineSeg(new Point(r.getLeft(), r.getBottom()), new Point(r.getRight(), r.getBottom())),
            new LineSeg(new Point(r.getRight(), r.getBottom()), new Point(r.getRight(), r.getTop())),
            new LineSeg(new Point(r.getRight(), r.getTop()), new Point(r.getLeft(), r.getTop())),
            new LineSeg(new Point(r.getLeft(), r.getTop()), new Point(r.getLeft(), r.getBottom()))
        };
        for (LineSeg edge : edges) {
            if (this.intersect(edge)) return true;
        }
        return false;
    }

    @Override
    public boolean intersect(Circle c) {
        float x1 = begin.getX(), y1 = begin.getY();
        float x2 = end.getX(), y2 = end.getY();
        float cx = c.getCenter().getX(), cy = c.getCenter().getY();
        float r = c.getRadius();

        float dx = x2 - x1, dy = y2 - y1;
        float a = dx * dx + dy * dy;
        float b = 2 * (dx * (x1 - cx) + dy * (y1 - cy));
        float cc = cx * cx + cy * cy + x1 * x1 + y1 * y1 - 2 * (cx * x1 + cy * y1) - r * r;

        float discriminant = b * b - 4 * a * cc;
        if (discriminant < 0) return false;

        float t1 = (-b + (float)Math.sqrt(discriminant)) / (2 * a);
        float t2 = (-b - (float)Math.sqrt(discriminant)) / (2 * a);

        return (t1 >= 0 && t1 <= 1) || (t2 >= 0 && t2 <= 1);
    }
    
}
