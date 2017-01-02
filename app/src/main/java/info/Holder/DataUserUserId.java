package info.Holder;

public class DataUserUserId {
    private static DataUserUserId  dataObject = null;

    private DataUserUserId () {
        // left blank intentionally
    }

    public static DataUserUserId  getInstance() {
        if (dataObject == null)
            dataObject = new DataUserUserId ();
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
