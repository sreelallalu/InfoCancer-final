package info.Holder;

public class DataUserPhone {
    private static DataUserPhone  dataObject = null;

    private DataUserPhone () {
        // left blank intentionally
    }

    public static DataUserPhone  getInstance() {
        if (dataObject == null)
            dataObject = new DataUserPhone ();
        return dataObject;
    }
    private String distributor_id;;

    public String getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(String distributor_id) {

        this.distributor_id = distributor_id;
    }
}
