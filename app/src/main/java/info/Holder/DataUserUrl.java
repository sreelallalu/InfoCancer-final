package info.Holder;

public class DataUserUrl {
    private static DataUserUrl  dataObject = null;

    private DataUserUrl () {
        // left blank intentionally
    }

    public static DataUserUrl  getInstance() {
        if (dataObject == null)
            dataObject = new DataUserUrl ();
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
