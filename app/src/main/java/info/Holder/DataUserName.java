package info.Holder;

public class DataUserName {
    private static DataUserName dataObject = null;

    private DataUserName() {
        // left blank intentionally
    }

    public static DataUserName getInstance() {
        if (dataObject == null)
            dataObject = new DataUserName();
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
