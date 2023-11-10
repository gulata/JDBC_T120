package stepDefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.JDBCReusableMethods;
import utilities.QueryManage;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class JDBC_StepDefinitions {
    QueryManage queryManage= new QueryManage();
    ResultSet resultSet;

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



}
