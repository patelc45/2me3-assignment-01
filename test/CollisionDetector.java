
import java.awt.Rectangle;   // This line was auto implemented by VSCode I dont know what does it mean.

public interface CollisionDetector {
    boolean intersect(Point p);
    boolean intersect(Lineseg l);
    boolean intersect(Rectangle r);
    boolean intersect(Circle c);
}
