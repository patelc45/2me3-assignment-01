public class Rectangle extends AbstractElement implements CollisionDetector{
    private float left, right, top, bottom;

    public Rectangle(){
        this(0, 1, 1, 0);
    }

    public Rectangle(float left, float right, float top, float bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        numberOfInstances++;
    }

    public float getLeft() {
        return left;
    }
    public float getRight() {
        return right;
    }
    public float getTop() {
        return top;
    }
    public float getBottom() {
        return bottom;
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
        return !(this.left > r.right || this.right < r.left || 
                 this.top < r.bottom || this.bottom > r.top);
    }

    @Override
    public boolean intersect(Circle c) {
        float closestX = Math.max(this.left, Math.min(c.getCenter().getX(), this.right));
        float closestY = Math.max(this.bottom, Math.min(c.getCenter().getY(), this.top));
        
        float distanceX = c.getCenter().getX() - closestX;
        float distanceY = c.getCenter().getY() - closestY;
        
        float distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
        return distanceSquared <= (c.getRadius() * c.getRadius());
    }
    
    

}
