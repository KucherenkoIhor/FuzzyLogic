package udhtu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igor on 27.11.16.
 */
public class Cluster {

    public final FourDPoint center;

    private List<FourDPoint> items = new ArrayList<>();

    public void addObject(FourDPoint fourDPoint) {
        items.add(fourDPoint);
    }

    public Cluster(FourDPoint center) {
        this.center = center;
    }

    public double distanceTo(FourDPoint fourDPoint) {
        return Math.pow(this.center.x - fourDPoint.x, 2)
                + Math.pow(this.center.y - fourDPoint.y, 2)
                + Math.pow(this.center.z - fourDPoint.z, 2)
                + Math.pow(this.center.j - fourDPoint.j, 2);
    }

}
