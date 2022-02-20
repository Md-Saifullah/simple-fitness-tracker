package bd.edu.seu;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class DBActions {
    public DBActions() {}

    public Connection getConnection() {
        Connection connection = null;
        {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/records", "root", "Incorrect");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    public  boolean insertRecord(Record record){
        String query= String.format("insert into records values(\"%s\",\"%s\",%f,%d,%d);",
                record.getDate(),
                record.getTime(),
                record.getWeight(),
                record.getSystolic(),
                record.getDiastolic());
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Record> getRecords(){
        ArrayList<Record>records=new ArrayList<>();

        /*records.add(new Record(LocalDate.now(),45.5, LocalTime.parse("15:45"),80,110));
        records.add(new Record(LocalDate.parse("2020-12-12"),45.5, LocalTime.parse("15:25"),80,110));
        records.add(new Record(LocalDate.parse("2020-12-12"),45.5, LocalTime.parse("02:25"),81,110));
        records.add(new Record(LocalDate.parse("2020-01-01"),45.5, LocalTime.parse("15:22"),80,110));*/


        String query="select * from records;";
        Connection connection=getConnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                LocalDate date= LocalDate.parse(resultSet.getString("date"));
                LocalTime time=LocalTime.parse(resultSet.getString("time"));
                double weight=resultSet.getDouble("weight");
                int systolic=resultSet.getInt("systolic");
                int diastolic=resultSet.getInt("diastolic");
                Record record=new Record(date,weight,time,systolic,diastolic);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(records);
        return records;
    }
}
