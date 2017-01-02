package info.Holder;

import java.util.List;

public class DataHolderClass {
private static DataHolderClass dataObject = null;

private DataHolderClass() {
    // left blank intentionally
}

public static DataHolderClass getInstance() {
    if (dataObject == null)
        dataObject = new DataHolderClass();
    return dataObject;
}
private List<String> distributor_id;;

 public List<String> getDistributor_id() {
    return distributor_id;
 }

 public void setDistributor_id(List<String> distributor_id) {

     this.distributor_id = distributor_id;
 }
}
