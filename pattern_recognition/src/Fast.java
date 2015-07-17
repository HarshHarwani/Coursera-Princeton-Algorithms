import java.util.Arrays;
import java.util.LinkedList;

public class Fast {

    public static void main(String args[]){
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

        for(Point p:pointsArray){
            //for every point i will create a clone array of the original array
            Point[] sortedPointArray=pointsArray.clone();
            // i will sort that array according to the comparator
            //the current point p is the origin, we ll sort the array according to the slope of the points with
            //respoect to the origin
            Arrays.sort(sortedPointArray,p.SLOPE_ORDER);
            LinkedList<Point> segment=new LinkedList<>();
            segment.add(p);
            for(int i=1;i<pointsArray.length-1;i++){
                Point q=sortedPointArray[i];
                Point r=sortedPointArray[i+1];
                double slope=p.slopeTo(q);
                double slope1=p.slopeTo(r);
                if(slope==slope1){
                    //to avoid repetation
                    if(segment.getLast()!=q){
                    segment.add(q);
                    }
                    segment.add(r);
                }
                //when the slopes do not match we check the size and clear the segment and add the origin again
                //in case its not the last iteration we can still check for other points to be added to the list
                if(i==N-2 || slope!=slope1){
                    if(segment.size()>3){
                        //stil need to figure how to avoid repetation of segments, need some way to identify sub-segments.
                        //probably need to check the segment..!!!
                        printSegment(segment);
                    }
                    segment.clear();
                    segment.add(p);

                }
            }
        }
    }

    private static void printSegment(LinkedList<Point> segment) {
        Point first=segment.getFirst();
        Point last=segment.getLast();
        StringBuffer buffer=new StringBuffer();
        for(Point p:segment){
            buffer.append(p.toString());
            buffer.append(" --> ");
        }
        String s=buffer.toString();
        System.out.println(s.substring(0,s.lastIndexOf(" --> ")));
        first.drawTo(last);
    }

}
