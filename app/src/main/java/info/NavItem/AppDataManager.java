package info.NavItem;

import java.util.ArrayList;

/**
 * Created by SLR on 11/21/2016.
 */

public class AppDataManager {
    private static AppDataManager appDataManager;
    private ArrayList<QuestionItem> arrString;

    private AppDataManager() {

    }

    public static AppDataManager getInstance() {
        if (appDataManager == null) {
            synchronized (AppDataManager.class) {
                if (appDataManager == null) {
                    appDataManager = new AppDataManager();
                    return appDataManager;
                } else {
                    return appDataManager;
                }
            }
        } else {
            return appDataManager;
        }
    }

    public void setArray(ArrayList<QuestionItem> data) {
        if(arrString == null)
            arrString = new ArrayList<QuestionItem>();
        this.arrString.addAll(data);
    }

    public ArrayList<QuestionItem> getArray() {
        return this.arrString;}
}