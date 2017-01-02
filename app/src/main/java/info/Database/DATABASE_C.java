package info.Database;

/**
 * Created by SLR on 10/25/2016.
 */
public interface DATABASE_C {

   String DATABASENAME="cancer_databse_db";
    int DATABSEVERSION=1;



    interface TABLE_NAME
    {

        String T_IMGAGE="imagetables";
        String T_USER="user_table";
    }
    interface COLUMN_NAME_IMAGE
    {   String I_id="idimage";
        String I_text="textimage";
        String I_title="titleimage";
        String I_url="titleurl";

    }
    interface COLUMN_NAME_USER
    {   String U_id="iduser";
        String U_name="username";
        String U_userid="userid";
        String U_PPP="whose";
        String U_url="userurl";
        String U_pass="userpass";
        String U_email="useremail";
        String U_phone="userphone";
        String U_age="userage";
    }
    interface COLUMN_NAME_DOCTOR
    {
        String D_id="";
        String D_name="";
        String D_phone="";
        String D_url="";
        String D_pass="";
        String D_email="";

    }


}
