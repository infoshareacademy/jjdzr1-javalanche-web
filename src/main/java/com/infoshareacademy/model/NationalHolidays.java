package com.infoshareacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.infoshareacademy.api.Country;
import com.infoshareacademy.api.HolidayDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "nationalHolidays_table")
public class NationalHolidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "holiday_name", nullable = false)
    private String name;
    @Column(name = "holiday_description", nullable = false)
    private String description;
    @Column(name = "holiday_date", nullable = false)
    private LocalDate holidayDate;

    public NationalHolidays() {
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
        NationalHolidays that = (NationalHolidays) o;
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
        return "NationalHolidays{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", holidayDate=" + holidayDate +
                '}';
    }
}
