package udhtu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igor on 22.11.16.
 */
public class PointsFilter {

    public static List<FourDPoint> filter(List<FourDPoint> list) {
        List<FourDPoint> result = new ArrayList<>();
        list.forEach(item -> {
           if(item.x <= 1800
                   && item.x > 0
                   && item.j > 0
                   && item.j <= 5
                   && item.y > 0
                   && item.y <= 5
                   && item.z > 0
                   && item.z <= 5) {
               result.add(item);
           }
        });
        return result;
    }

}
