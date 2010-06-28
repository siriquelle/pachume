package model;

import java.sql.*;

public class DataBaseConnection {

    //Remote//
    //String dbURL = "jdbc:mysql://pachume.com/eyeonz0_pachume";
    //String dbUsername = "eyeonz0_siriquelle";
    //String dbPassword = "smithfield26";
//    
    //Local//
    String dbURL = "jdbc:mysql://localhost/eyeonz0_pachume";
    String dbUsername = "root";
    String dbPassword = "admin";    //Local Remote//
//    
    //Local-Remote
    //String dbURL = "jdbc:mysql://localhost/eyeonz0_pachume";
    //String dbUsername = "eyeonz0_siriquelle";
    //String dbPassword = "smithfield26";
    
//    
    String dbDriver = "com.mysql.jdbc.Driver"; // change to other driver if needed
    private Connection dbCon = null;
    private DatabaseMetaData dbMetaData;
    static String[] tableTypes = {
        "TABLES"
    };

    public void DataBaseConnection() {
    }

    public boolean connect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName(this.getDbDriver()).newInstance();
        dbCon = DriverManager.getConnection(this.getDbURL(), this.getDbUsername(), this.getDbPassword());
        return true;
    }

    public void close() throws SQLException {
        dbCon.close();
    }

    public ResultSet execSQL(String sql) throws SQLException {
        Statement s = dbCon.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return (rs == null) ? null : rs;
    }

    public int execUpdate(String sql) throws SQLException {
        Statement s = dbCon.prepareStatement(sql);
        int result = s.executeUpdate(sql);
        return (result == 0) ? 0 : result;
    }

    public String getDbDriver() {
        return this.dbDriver;
    }

    public void setDbDriver(String newValue) {
        this.dbDriver = newValue;
    }

//Take a break Tonto//
    public String getDbURL() {
        return this.dbURL;
    }

    public String getDbUsername() {
        return this.dbUsername;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }
//Take a break Tonto//
    public void setDbURL(String newValue) {
        this.dbURL = newValue;
    }

    public Connection getConnection() {
        return dbCon;
    }

    public ResultSet getTables() throws SQLException {
        dbMetaData = dbCon.getMetaData();
        return dbMetaData.getTables(null, null, null, tableTypes);
    }

    public ResultSet getTable(String tableName) throws SQLException {
        return dbMetaData.getColumns(null, null, tableName, null);
    }
}
