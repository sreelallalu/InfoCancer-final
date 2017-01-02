package info.Holder;

public class DataUserPPP {
    private static DataUserPPP  dataObject = null;

    private DataUserPPP () {
        // left blank intentionally
    }

    public static DataUserPPP  getInstance() {
        if (dataObject == null)
            dataObject = new DataUserPPP ();
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
