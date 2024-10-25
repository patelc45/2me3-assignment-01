public class Circle extends AbstractElement implements CollisionDetector {
    private Point center;
    private float radius;

    public Circle() {
        this(new Point(), 1);
    }

    public Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
        numberOfInstances++;
    }

    public Point getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
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
        return l.intersect(this);
    }

    @Override
    public boolean intersect(Rectangle r) {
        return r.intersect(this);
    }

    @Override
    public boolean intersect(Circle c) {
        float distanceSquared = (float) (Math.pow(this.center.getX() - c.center.getX(), 2) +
                                         Math.pow(this.center.getY() - c.center.getY(), 2));
        float radiusSum = this.radius + c.radius;
        return distanceSquared <= radiusSum * radiusSum;
    }
}