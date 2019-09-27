import javax.lang.model.util.ElementScanner6;

public class Point
{
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){ return x; }
    public double getY(){ return y; }

    public double getRadius(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getAngle(){
        double absAngle = Math.acos(x / getRadius());
        if (y < 0) {
            return - absAngle;
        } else {
            return absAngle;
        }
    }

    public Point rotate90(){
        if (x > 0 && y > 0) {
            return new Point(-y, x);
        } else if (x > 0 && y < 0) {
            return new Point(-y, x);
        } else if (x < 0 && y < 0) {
            return new Point(-y, x);
        } else {
            return new Point(-y, x);
        }
    }


}