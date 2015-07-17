import java.util.Comparator;

/**
 * @description immutable type point class
 * @author hharwani
 *
 */
public class Point implements Comparable<Point> {

    private int xCord;
    private int yCord;

    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    public Point(int x, int y) { // construct the point (x, y)
        this.xCord = x;
        this.yCord = y;
    }

    public void draw() { // draw this point
        StdDraw.point(this.xCord, this.yCord);
    }

    public void drawTo(Point that) { // draw the line between the two given points
        StdDraw.line(this.xCord, this.yCord, that.xCord, that.yCord);
    }

    public String toString() {
        return "("+String.valueOf(xCord)+","+" "+String.valueOf(yCord)+")";
    }

    /**
     * 
     * @param that
     * @return double
     */
    public double slopeTo(Point that) {

        double deltaX = this.xCord - that.xCord;
        double deltaY = this.yCord - that.yCord;
        if (deltaX == 0 && deltaY == 0) { // Degenerate Line Segment
            return Double.NEGATIVE_INFINITY;
        } else if (deltaX == 0) { // vertical line segment
            return Double.POSITIVE_INFINITY;
        } else if (deltaY == 0) { // horizontal line segment
            return 0.0;
        } else {
            return deltaY / deltaX; // slope
        }

    }

    @Override
    // comparing the points and deciding whether one point is lexigraphically
    // smaller than the other.
    public int compareTo(Point that) {
        if (this.yCord < that.yCord) {
            return -1;
        } else if (this.yCord > that.yCord) {
            return 1;
        } else if (this.yCord > that.yCord) {
            return 1;
        } else if (this.xCord < that.xCord) {
            return -1;
        } else if (this.xCord > that.xCord) {
            return 1;
        } else {
            return 0;
        }
    }

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            if (slope1 < slope2)
                return -1;
            else if (slope1 > slope2)
                return 1;
            else
                return 0;
        }

    }

}
