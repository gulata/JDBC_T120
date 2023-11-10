package stepDefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.JDBCReusableMethods;
import utilities.QueryManage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JDBC_StepDefinitions {
    QueryManage queryManage= new QueryManage();
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    @Given("Database baglantisi kurulur.")
    public void database_baglantisi_kurulur() {
        JDBCReusableMethods.createConnection();


    }
    @Given("Query hazirlanir ve chat_users tablosuna execute edilir.")
    public void query_hazirlanir_ve_chat_users_tablosuna_execute_edilir() throws SQLException {

        String query= queryManage.getChatUsersQuery();
        resultSet= JDBCReusableMethods.getStatement().executeQuery(query);


    }
    @Given("Chat_users tablosundan donen resultSet dogrulanir.")
    public void chat_users_tablosundan_donen_result_set_dogrulanir() throws SQLException {
        String expectedData="11";
       resultSet.next();
       String actualData=resultSet.getString(1);
        assertEquals(expectedData,actualData);

    }
    @Given("Database baglantisi kapatilir.")
    public void database_baglantisi_kapatilir() {
        JDBCReusableMethods.closeConnection();

    }
//------------query02---------------
    @Given("Query hazirlanir ve students tablosuna execute edilir.")
    public void query_hazirlanir_ve_students_tablosuna_execute_edilir() throws SQLException {

        String query= queryManage.getStudentsQuery();
        resultSet=JDBCReusableMethods.getStatement().executeQuery(query);

    }
    @Given("Students tablosundan donen resultSet`teki email bilgisi dogrulanir.")
    public void students_tablosundan_donen_result_set_teki_email_bilgisi_dogrulanir() throws SQLException {
        String expectedData= "brain@gmail.com";
        resultSet.next();
        String actualData=resultSet.getString("email");
        assertEquals(expectedData,actualData);

    }
    //********* Query03 ***********

    @Given("Query hazirlanir ve onlineexam tablosuna execute edilir.")
    public void query_hazirlanir_ve_onlineexam_tablosuna_execute_edilir() throws SQLException {

        String query = queryManage.getOnlineExamQuery();

        resultSet = JDBCReusableMethods.getStatement().executeQuery(query);

    }
    @Given("Onlineexam tablosundan donen resultSet`teki bilgiler listelenir.")
    public void onlineexam_tablosundan_donen_result_set_teki_bilgiler_listelenir() throws SQLException {

        while(resultSet.next()){

            System.out.println( " exam : "+ resultSet.getString(1)+ " ->   ortalama: " +
                    resultSet.getString(2));

        }
    }

    //********** Query04 ***************

    @Given("Query hazirlanir ve transport_feemaster tablosuna executeUpdate edilir.")
    public void query_hazirlanir_ve_transport_feemaster_tablosuna_execute_update_edilir() throws SQLException {

        String query = "UPDATE u168183796_qawonder.transport_feemaster SET fine_amount = ? WHERE month = ?";

        // PreparedStatement ile execute islemi

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);

        preparedStatement.setFloat(1,250);
        preparedStatement.setString(2, "October");

        int rowCount = preparedStatement.executeUpdate();

        System.out.println("Update islemi gerceklesti ve "+ rowCount +" satir etkilendi.");


    }

    //********** Query05 *********

    @Given("UpdateQuery`si hazirlanir ve roles tablosuna execute edilir.")
    public void update_query_si_hazirlanir_ve_transport_route_tablosuna_execute_edilir() throws SQLException {

        String query = queryManage.getRolesQuery();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);

        preparedStatement.setString(1, "NewRole");
        preparedStatement.setInt(2, 1);
        preparedStatement.setInt(3, 1);

        int rowCount = preparedStatement.executeUpdate();

        assertTrue(rowCount==1);

        System.out.println(rowCount + " satirda update islemi gerceklesti.");

    }


    //**************Query06 *****************

    @Given("Delete Query`si hazirlanir ve staff_leave_request tablosuna execute edilir.")
    public void delete_query_si_hazirlanir_ve_staff_leave_request_tablosuna_execute_edilir() throws SQLException {

        String query = queryManage.getStafLeaveRequestQuery();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);

        preparedStatement.setInt(1,12);

        int rowCount = preparedStatement.executeUpdate();

        if(rowCount>0){
            System.out.println( rowCount +" satir silindi");
        }else{
            System.out.println( "Silme islemi sirasinda bir hata olustu.. Failed");
        }

    }


}