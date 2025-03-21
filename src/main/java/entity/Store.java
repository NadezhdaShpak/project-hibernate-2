package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(schema = "movie", name = "store")
public class Store {
    @Id
    @Column(name = "store_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff managerStaff;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "store")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "store")
    private Set<Inventory> inventories;

    @OneToMany(mappedBy = "store")
    private Set<Staff> staff;
}