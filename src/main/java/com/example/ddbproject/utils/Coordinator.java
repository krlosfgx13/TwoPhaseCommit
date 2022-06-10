package com.example.ddbproject.utils;

import java.sql.*;

public class Coordinator {
    private static final String SQL_SERVER = "mssql";
    private static final String MY_SQL = "mysql";

    public static Connection getDataBaseConnection(String dataBase) {
        try {
            Connection conn = null;
            switch (dataBase) {
                case SQL_SERVER:
                    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                    conn = DriverManager.getConnection(ConnectionConstants.MSSQL_CONNECTION_STRING,
                            ConnectionConstants.MSSQL_USER_NAME, ConnectionConstants.MSSQL_PASSWORD);
                    break;

                case MY_SQL:
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    conn = DriverManager.getConnection(ConnectionConstants.MYSQL_CONNECTION_STRING,
                            ConnectionConstants.MYSQL_USER_NAME, ConnectionConstants.MYSQL_PASSWORD);
                    break;
            }
            return conn;
        } catch (SQLNonTransientConnectionException ex) {
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean performOperation(PreparedStatement psMySql, PreparedStatement psSqlServer) throws SQLException {
        int affectedRowsMySql = psMySql.executeUpdate();
        int affectedRowsSqlServer = psSqlServer.executeUpdate();
        if(affectedRowsMySql > 0 && affectedRowsSqlServer > 0){
            return  true;
        }else{
            return false;
        }
    }
}
