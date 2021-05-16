package com.whelmed.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private int priority;
    @Column
    private String consequence;
    @Column
    private LocalDateTime dueDate;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private Group group;

    public Task() {
    }

    public Task(String title, int priority, String consequence, LocalDateTime dueDate) {
        this.title = title;
        this.priority = priority;
        this.consequence = consequence;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", consequence='" + consequence + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
