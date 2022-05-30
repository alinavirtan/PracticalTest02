package ro.pub.cs.systems.eim.practicaltest02.model;
import java.util.Calendar;

public class GenericResults {

    private String rate;
    private String updated;
    private long time;

    public GenericResults(String rate, String updated, long time) {
        this.rate = rate;
        this.updated = updated;
        this.time = time;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "BitcoinResults{" +
                "rate='" + rate + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }

}