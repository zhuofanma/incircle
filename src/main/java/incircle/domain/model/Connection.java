package incircle.domain.model;

import incircle.account.model.Account;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by zhuofanma on 11/7/15.
 */
@Entity
@Table(name="connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CONNECTION_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT1_ID", nullable = false)
    private Account account1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT2_ID", nullable = false)
    private Account account2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

        public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }
}
