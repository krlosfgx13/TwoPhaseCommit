package com.example.ddbproject.utils;

public abstract class ConnectionConstants {
    public static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/project_ddb?autoReconnect=true&useSSL=false";
    public static String MYSQL_USER_NAME = "root";
    public static String MYSQL_PASSWORD = "12345678";
    public static String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    //public static final String MSSQL_CONNECTION_STRING = "jdbc:sqlserver://192.168.0.105:1433;databaseName=project_ddb;loginTimeout=30;encrypt=false;";
    public static final String MSSQL_CONNECTION_STRING = "jdbc:sqlserver://35.225.122.204:1433;databaseName=project_ddb;loginTimeout=30;encrypt=false;";
    //public static String MSSQL_USER_NAME = "sa";
    public static String MSSQL_USER_NAME = "sqlserver";
    public static String MSSQL_PASSWORD = "12345678";
    public static String MSSQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SQL_SERVER = "mssql";
    public static final String MY_SQL = "mysql";
}
