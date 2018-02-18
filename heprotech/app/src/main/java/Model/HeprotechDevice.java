package Model;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Jessica on 2018-02-18.
 */

public class HeprotechDevice {
    @SerializedName("name")
    private String name;

    @SerializedName("armed")
    private boolean armed;

    @SerializedName("description")
    private String description;

    @SerializedName("latestEventTimestamp")
    private Date latestEventTimestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(boolean armStatus) {
        this.armed = armStatus;
    }

    public Date getLatestEventTimestamp() {
        return latestEventTimestamp;
    }

    public void setLatestEventTimestamp(Date lastUpdated) {
        this.latestEventTimestamp = lastUpdated;
    }

}
