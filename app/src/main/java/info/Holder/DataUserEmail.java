package info.Holder;

public class DataUserEmail {
    private static DataUserEmail  dataObject = null;

    private DataUserEmail () {
        // left blank intentionally
    }

    public static DataUserEmail  getInstance() {
        if (dataObject == null)
            dataObject = new DataUserEmail ();
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
