package it.bologna.ausl.bdm.utilities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

/**
 *
 * @author gdm
 */
public class StepLog {
    private String stepId;
    private String stepType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    protected DateTime executionDate;

    private Bag logData;

    public StepLog() {
    }

    public StepLog(String stepId, String stepType, DateTime executionDate) {
        this.stepId = stepId;
        this.stepType = stepType;
        this.executionDate = executionDate;
        this.logData = null;
    }

    public StepLog(String stepId, String stepType, DateTime executionDate, Bag logData) {
        this.stepId = stepId;
        this.stepType = stepType;
        this.executionDate = executionDate;
        this.logData = logData;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getStepType() {
        return stepType;
    }

    public void setStepType(String stepType) {
        this.stepType = stepType;
    }

    public DateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(DateTime executionDate) {
        this.executionDate = executionDate;
    }

    public Bag getLogData() {
        return logData;
    }

    public void setLogData(Bag logData) {
        this.logData = logData;
    }

    @JsonIgnore
    public void putInLogData(String key, Object value) {
        if (logData == null)
            logData = new Bag();

        logData.put(key, value);
    }

    @JsonIgnore
    public Object getFromLogData(String key) {
        if (logData != null)
            return logData.get(key);
        else
            return null;
    }
}
