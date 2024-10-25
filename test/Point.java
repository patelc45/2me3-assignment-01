public class Point extends AbstractElement implements CollisionDetector{
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        numberOfInstances++;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public static int getNumOfInstances() {
        return numberOfInstances;
    }

    @Override
    public boolean intersect(Point p) {
        return this.x == p.getX() && this.y == p.getY();
    }

    @Override
    public boolean intersect(Lineseg l) {
        float x1 = l.getBegin().getX(), y1 = l.getBegin().getY();
        float x2 = l.getEnd().getX(), y2 = l.getEnd().getY();
        float dx = x2 - x1, dy = y2 - y1;
        float t = ((x - x1) * dx + (y - y1) * dy) / (dx * dx + dy * dy);
        return t >= 0 && t <= 1 && Math.abs ((x1 + t * dx) - x) < 1e-6 && Math.abs((y1 + t * dy) - y) < 1e-6;
    }

    @Override
    public boolean intersect(Rectangle r) {
        return x >= r.getLeft() && x <= r.getRight() && 
               y >= r.getBottom() && y <= r.getTop();
    }

    @Override
    public boolean intersect(Circle c) {
        float dx = this.x - c.getCenter().getX();
        float dy = this.y - c.getCenter().getY();
        return Math.sqrt(dx * dx + dy * dy) <= c.getRadius();
    }
    
}
