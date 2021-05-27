package models;

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

@Table(name = "follow")

@NamedQueries({
        @NamedQuery(name = "getMyAllFollows",
                query = "SELECT f FROM Follow AS f WHERE f.employee =:emp ORDER BY f.id ASC "),
        @NamedQuery(name="getFollows",
                query="SELECT f FROM Follow AS f WHERE f.regemp_id =:emp")
})

@Entity
public class Follow{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "registered_at", nullable = false)
    private Timestamp registered_at;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="regemp_id",nullable=false)
    private Employee regemp_id;




    public void setRegemp_id(Employee regemp_id) {
        this.regemp_id = regemp_id;
    }



    public Employee getRegemp_id() {
        return regemp_id;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(Timestamp registered_at) {
        this.registered_at = registered_at;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



}