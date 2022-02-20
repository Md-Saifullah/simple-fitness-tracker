package bd.edu.seu;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;

public class Record implements Comparable<Record>{
    private LocalDate date;
    private double weight;
    private LocalTime time;
    private int systolic;
    private int diastolic;

    public Record(LocalDate date, double weight, LocalTime time, int systolic, int diastolic) {
        this.date = date;
        this.weight = weight;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    @Override
    public int compareTo(Record record) {
        if(this.getDate().equals(record.getDate())){
            return this.getTime().compareTo(record.getTime());
        }
        return this.getDate().compareTo(record.getDate());
    }
}
