package com.infoshareacademy.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "daysOff_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dayOff_id")
})
public class DayOff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayOff_id", nullable = false)
    private int id;

    @Column(name = "dayOff_startDay", nullable = false)
    private LocalDate startDay;

    @Column(name = "dayOff_endDay", nullable = false)
    private LocalDate endDay;

    @ManyToOne
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "listDaysOff_table", joinColumns = @JoinColumn(name = "dayOff_id"))
    private List<LocalDate> listOfDays;

    public DayOff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LocalDate> getListOfDays() {
        return listOfDays;
    }

    public void setListOfDays(List<LocalDate> listOfDays) {
        this.listOfDays = listOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayOff)) return false;
        DayOff dayOff = (DayOff) o;
        return id == dayOff.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DayOff{" +
                "id=" + id +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", user=" + user +
                ", listOfDays=" + listOfDays +
                '}';
    }
}
