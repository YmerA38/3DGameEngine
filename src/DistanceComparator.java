import java.util.Comparator;

public class DistanceComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1.getCenter().getX()>o2.getCenter().getX()){
            return -1;
        }else if(o1.getCenter().getX()<o2.getCenter().getX()){
            return 1;
        }else{
            return 0;
        }

    }
}
