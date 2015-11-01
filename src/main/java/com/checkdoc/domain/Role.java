package com.checkdoc.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "ROLE")
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Set<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (getId() != role.getId()) return false;
        if (!getName().equals(role.getName())) return false;
        return !(getUsers() != null ? !getUsers().equals(role.getUsers()) : role.getUsers() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }
}
