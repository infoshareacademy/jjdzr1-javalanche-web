package com.infoshareacademy.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "nationalHolidays_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nationalHolidays_id")
})
public class NationalHoliday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id", nullable = false)
    private int id;
    @Column(name = "holiday_name", nullable = false)
    private String name;
    @Column(name = "holiday_description", nullable = false)
    private String description;
    @Column(name = "holiday_date", nullable = false)
    private LocalDate holidayDate;

    public NationalHoliday() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NationalHoliday that = (NationalHoliday) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(holidayDate, that.holidayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, holidayDate);
    }

    @Override
    public String toString() {
        return "NationalHoliday{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", holidayDate=" + holidayDate +
                '}';
    }
}
