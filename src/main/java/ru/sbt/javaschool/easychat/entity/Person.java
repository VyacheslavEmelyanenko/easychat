package ru.sbt.javaschool.easychat.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "nickname", length = 30, unique = true)
    private String nickname;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Message> messages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
