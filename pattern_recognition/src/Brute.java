import java.util.Arrays;

public class Brute {

    public static void main(String args[]) {
        // get the filename as the command line argument
        String filename = args[0];
        // create input from the file
        In in = new In(filename);
        // read the number of points which is the first number in the line
        int N = in.readInt();
        // create an array of the points.
        Point[] pointsArray = new Point[N];
        // create the x and y co-ordinate system as the integers can be in range
        // from 0 to 32768.
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show();
        // read the points from the file and make point objects and populate the
        // point object array
        for (int i = 0; i < pointsArray.length; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point point = new Point(x, y);
            pointsArray[i] = point;
            point.draw();
        }
        Arrays.sort(pointsArray);
        // going through all the points to find out the pairs of four collinear points.
        //complexity n^4
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    for (int l = k + 1; l < N; l++) {
                        Point p = pointsArray[i];
                        Point q = pointsArray[j];
                        Point r = pointsArray[k];
                        Point s = pointsArray[l];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            StdOut.print(p.toString() + " -> " + q.toString() + " -> " + r.toString() + " -> "
                                    + s.toString());
                            p.drawTo(r); //drawing the line connecting the four collinear points.
                            StdOut.print("\n");
                        }
                    }
                }
            }
        }
    }
}
