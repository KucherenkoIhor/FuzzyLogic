package udhtu;

/**
 * Created by igor on 21.11.16.
 */
public class FourDPoint {

    final double x;
    final double y;
    final double z;
    final double j;

    public FourDPoint(double x, double y, double z, double j) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.j = j;
    }

    public double distanceTo(FourDPoint other) {
        return Math.pow(this.x - other.x, 2)
                + Math.pow(this.y - other.y, 2)
                + Math.pow(this.z - other.z, 2)
                + Math.pow(this.j - other.j, 2);
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " z: " + z + " j: " + j;
    }
}
