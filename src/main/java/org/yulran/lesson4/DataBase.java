package org.yulran.lesson4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public  class DataBase {
   private static final String DB_URL = "jdbc:postgresql://ep-orange-snowflake-a26rwxcl.eu-central-1.aws.neon.tech/InfoTelegBot?user=panko.yulya&password=jzUfLtu2ZIX0&sslmode=require";
   private static final String user = "Yuliia Panko";
   private static final String password = "gV6nqX$Wx@np!NY";
   private static final String driverName = "org.postgresql.Driver";

  public static void sendDBRequest (){
      try{
          Connection conn = DriverManager.getConnection(DB_URL, user, password);
          Statement statment = conn.createStatement();
          statment.execute("DROP TABLE IF EXISTS weatherTable ");
          statment.execute("CREATE TABLE IF NOT EXISTS weatherTable (id SERIAL PRIMARY KEY, time TEXT ,temp INTEGER)");
          statment.execute("INSERT INTO  weatherTable (time, temp) VALUES ( 'day', 6)");

         ResultSet rs= conn.createStatement().executeQuery("SELECT * FROM weatherTable");
          while (rs.next()) {
             System.out.println(rs.getString("time") + " " + rs.getInt("temp"));
          }
          statment.close();
          conn.close();
  } catch (Exception e) {
          System.out.println("Ошибка:" + e.getMessage());
      }


  }
    protected static void saveWeatherToDatabase(String cityName, String weatherInfo) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, password);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO weatherTable (city, weather_info) VALUES ('" + cityName + "', '" + weatherInfo + "')");
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

