package ru.duckcoder.model;

import ru.duckcoder.util.Priority;
import ru.duckcoder.util.RepeatType;
import ru.duckcoder.util.TaskType;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Task {
    //Types: Simple, block, chain,
    //Params: priority, term, repeat type
    private TaskType taskType;
    private Priority priority;
    private RepeatType repeatType;
    private Date createDate;
    private Date termDate;
    private Date actDate = null;
    private String taskHeader;
    private String taskDescription;

    public Task(
            TaskType taskType,
            Priority priority,
            RepeatType repeatType,
            Date termDate,
            String taskHeader,
            String taskDescription
    ) {
        this.taskType = taskType;
        this.priority = priority;
        this.repeatType = repeatType;
        createDate = new Date(System.currentTimeMillis());
        if(createDate.after(termDate)) {
            this.termDate = Date.from(createDate.toInstant().plus(1L, ChronoUnit.DAYS));
        } else {
            this.termDate = termDate;
        }
        this.taskHeader = taskHeader;
        this.taskDescription = taskDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public RepeatType getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(RepeatType repeatType) {
        this.repeatType = repeatType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getTermDate() {
        return termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getTaskHeader() {
        return taskHeader;
    }

    public void setTaskHeader(String taskHeader) {
        this.taskHeader = taskHeader;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void completeTask() {
        this.actDate = new Date(System.currentTimeMillis());
    }

    public Boolean isComplete() {
        return this.actDate != null;
    }
}
