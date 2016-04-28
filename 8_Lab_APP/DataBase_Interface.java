

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase_Interface {
    // ---For database connection
    private String dbPath = "";
    private String user = "";
    private String password = "";
    private String url = "";
    // ---

    private static Connection conn;

    public DataBase_Interface() {
        dbPath = "D:/Firebird/VOEN_FOR_APP.fdb";
        url = "jdbc:firebirdsql:localhost/3050:" + dbPath;
        user = "SYSDBA";
        password = "masterkey";
    }



    // Инициализация Firebird JDBC Драйвера
    public void initDriver() throws DataBase_Exception
    {
        try {
                Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();

        } catch (ClassNotFoundException ex) {
                throw new DataBase_Exception("DataBase_Interface.initDriver(): ClassNotFoundException");
        }
        catch (InstantiationException ex) {
                throw new DataBase_Exception("DataBase_Interface.initDriver(): InstantiationException");
        } catch (IllegalAccessException ex) {
                throw new DataBase_Exception("DataBase_Interface.initDriver(): IllegalAccessException");
        }
    }
    // Подключение к базе данных
    public boolean connect() throws DataBase_Exception
    {
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn == null) return false;
            else return true;
        } catch (SQLException ex) {
            throw new DataBase_Exception("DataBase_Interface.connect(): SQLException");
        }
    }
    public void insertData_Personal_file(Main.Personal_File  ps) throws DataBase_Exception
    {
        String query = "INSERT INTO Personal_file VALUES (" +
                ps.id_file + ", " + ps.id_recruit + ", '" + ps.registr_date + "')";
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();
            stmt.execute(query);
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataBase_Exception("DataBase_Interface.insertData_Personal_file(): SQLException");
        }
    }
    public void insertData_Father_info(Main.Father_info  fi) throws DataBase_Exception
    {
        String query = "INSERT INTO Father_info VALUES (" +
                fi.id_father + ", '" + fi.name + "', '" + fi.birth_date +
                "', '" + fi.registr_place + "')";

        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DataBase_Exception("DataBase_Interface.insertData_Father_info(): SQLException");
        }
    }
    public void insertData_Mother_info(Main.Mother_info  mi) throws DataBase_Exception
    {
        String query = "INSERT INTO Mother_info VALUES (" +
                mi.id_mother + ", '" + mi.name + "', '" + mi.birth_date +
                "', '" + mi.registr_place + "')";

        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DataBase_Exception("DataBase_Interface.insertData_Mother_info(): SQLException");
        }

    }
    public void insertData_Recruit(Main.Recruit  rc) throws DataBase_Exception
    {
        String query = "INSERT INTO Recruit VALUES (" +
                rc.id_recruit + ", '" + rc.name + "', '" + rc.birth_date +
                "', '" + rc.registr_place + "', " + rc.id_mother + ", "  + rc.id_father + ", " + rc.id_academic +
                 ")";

        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DataBase_Exception("DataBase_Interface.insertData_Recruit(): SQLException");
        }
    }
    public boolean SelectData_Activities(Main.Activities ac, String id_file) throws DataBase_Exception
    {
        boolean cnt = false;
        String common_id_activities = "";
        String common_activity_date = "";
        String common_activity_date_off = "";
        String selectTableSql = "SELECT id_activity, activity_date, activity_date_off  from Activities where id_file = " + id_file;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectTableSql);
            while (rs.next())
            {
                cnt = true;
                common_id_activities += rs.getString("id_activity") + "\n";
                common_activity_date += rs.getString("activity_date") + "\n";
                common_activity_date_off += rs.getString("activity_date_off") + "\n";
            }
            rs.close();
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DataBase_Exception("DataBase_Interface.SelectData_Activities(): SQLException");
        }
        ac.id_file = id_file;
        ac.id_activity = common_id_activities;
        ac.activity_date = common_activity_date;
        ac.activity_date_off = common_activity_date_off;
        return cnt;
    }

    public String SelectData_Kind_Activity(Main.Kind_Activity ka, String id_activity) throws DataBase_Exception
    {
        String selectTableSql = "SELECT kind_activity from kind_activity where id_activity = " + id_activity;
        String result = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt =  conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectTableSql);
            while (rs.next())
            {
                result = rs.getString("kind_activity");
            }
            rs.close();
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DataBase_Exception("DataBase_Interface.SelectData_Kind_Activity(): SQLException");
        }
        return result;
    }
}
