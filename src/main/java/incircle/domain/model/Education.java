package incircle.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import incircle.account.model.Account;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by zhuofanma on 11/3/15.
 */
@Entity
@Table(name="educations")
public class Education {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "EDUCATION_ID", unique = true, nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    private String school;

    private String location;

    private String major;

    @Temporal(TemporalType.TIMESTAMP)
    private Date starttime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endtime;

    private boolean isCurrent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public boolean getIscurrent() {
        return isCurrent;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
