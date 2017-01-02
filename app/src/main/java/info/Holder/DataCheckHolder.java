package info.Holder;

/**
 * Created by SLR on 11/24/2016.
 */

public class DataCheckHolder {
    private static DataCheckHolder dataObject = null;

    private DataCheckHolder() {
        // left blank intentionally
    }

    public static DataCheckHolder getInstance() {
        if (dataObject == null)
            dataObject = new DataCheckHolder();
        return dataObject;
    }
    private boolean distributor_id;;

    public boolean getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(boolean distributor_id) {

        this.distributor_id = distributor_id;
    }
}

