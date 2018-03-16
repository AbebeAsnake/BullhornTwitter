package com.abebe.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="roles")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "role_name",unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users;

    @CreationTimestamp
    @Column(name = "time_stamp")
    Timestamp ceartedAt;

    public AppRole() {
        this.users = new HashSet<>();

    }

    @Override
    public String toString() {
        return "AppRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                ", ceartedAt=" + ceartedAt +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

    public Timestamp getCeartedAt() {
        return ceartedAt;
    }

    public void setCeartedAt(Timestamp ceartedAt) {
        this.ceartedAt = ceartedAt;
    }
}
