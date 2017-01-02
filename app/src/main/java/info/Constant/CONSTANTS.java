package info.Constant;

/**
 * Created by SLR on 10/20/2016.
 */
public interface CONSTANTS {
    ///
   // questions.java
    interface questions{
       String WEBURL ="http://infocancer.nyesteventuretech.com/Service/question.php";

        String Question_TAG_ID="SelCat";
        String Question_TAG_IDUSER="SelCatuser";
        String Question_TAG_IDDoctor="SelCatdoctor";
        String CAT_TAG="categoryn";
        String CAT_UERqU="userquestion";



         String TAG_NAME ="user" ;
        String TAG ="picassa" ;

         String ID_C ="content" ;

         String ID_S ="title" ;
         String ID_T ="datetime";
        String ID_CAT ="category" ;

         String ID_Q_id ="postid" ;
        String ID_Q_urL="q_image";

        String ID_U_name="u_name";
        String ID_U_URL="u_image";

    }
    interface intent_send{
        String Intent_TITLE ="ititle" ;
        String Intent_CONTENT ="icontent";

        String Intent_TIME ="itime" ;
        String Intent_CAT ="icat" ;

        String Intent_Q_ID ="ipostid" ;
        String Intent_Q_IMAGE ="ipostpic" ;

        String Intent_U_NAME ="iusername" ;
        String Intent_U_IMAGE ="iuserpic" ;

    }
    interface comment_list{

        String WEBURL ="http://infocancer.nyesteventuretech.com/Service/comment.php";


        String TAG_COMMENT ="comment";
        String Comment_TAg_id="post_Id";

        String Comment_responce="comt";

        String ID_C="comment";
        String ID_N="fname";
        String ID_T="datetime";
        String ID_IMG="q_image";
        String ID_Cid="cmt_id";




    }

    interface sendquestion
    {
        String WEBURL ="http://infocancer.nyesteventuretech.com/Service/question.php";


        String TAG_COMMENT ="question";


        String ID_cate="categoryc";

        String ID_Sub="title";

        String ID_Con="content";

        String ID_IMG="userpic";
        String ID_user="userid";

    }
    interface U_createprofile
    {
        String WEBURL ="http://infocancer.nyesteventuretech.com/Service/login.php";

        String U_C_name="username";
        String U_C_email="useremail";

        String U_C_pass="userpass";
        String U_C_gender="usergender";

        String U_C_age="userage";
        String U_C_phone="userphone";

        String U_C_image="userimage";


    }
    interface Image_URL
    {
    String WEBURL="http://infocancer.nyesteventuretech.com/Service/cancer_deatils.php";
        String TAG="cat";
        String CatG="category";
          String I_HEAD ="title";
         String I_TEXT ="content";
        String I_PHOTO ="photo";
        String I_catego="category";
        String I_JsonOb="result";


    }

}
