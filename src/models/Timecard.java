package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "timecards")

@NamedQueries({
        @NamedQuery(name = "getMyAllTimecards",
                query = "SELECT t FROM Timecard AS t WHERE t.employee =:emp ORDER BY t.strtime_date DESC"),

        @NamedQuery(name = "getMyTimecardsCount",
                query = "SELECT COUNT(t) FROM Timecard AS t WHERE t.employee=:emp"),

     //   @NamedQuery(name = "getTimecard",
      //         query = "SELECT t FROM Timecard AS t WHERE  t.employee = :emp AND  "),

        @NamedQuery(name = "getStartTimecardDay",
                query = "SELECT t FROM Timecard As t WHERE t.employee = :emp AND t.strtime_date = :workingDay "),

        @NamedQuery(name = "getFinTimecardDay",
                query = "SELECT t FROM Timecard As t WHERE t.employee = :emp AND t.fintime_date = :workingDay ")
})

@Entity
public class Timecard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "strtime_date", nullable = false)
    private Date strtime_date;

    @Column(name = "fintime_date", nullable = true)
    private Date fintime_date;

    @Column(name = "started_at", nullable = false)
    private Timestamp started_at;

    @Column(name = "finished_at", nullable = true)
    private Timestamp finished_at;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    public Date getStrtime_date() {
        return strtime_date;
    }

    public void setStrtime_date(Date strtime_date) {
        this.strtime_date = strtime_date;
    }

    public Date getFintime_date() {
        return fintime_date;
    }

    public void setFintime_date(Date fintime_date) {
        this.fintime_date = fintime_date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Timestamp started_at) {
        this.started_at = started_at;
    }

    public Timestamp getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(Timestamp finished_at) {
        this.finished_at = finished_at;
    }

}
