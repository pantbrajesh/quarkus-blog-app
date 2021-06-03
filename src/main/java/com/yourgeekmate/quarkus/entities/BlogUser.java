package com.yourgeekmate.quarkus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = BlogUser.FIND_ALL, query = "SELECT u FROM BlogUser u ORDER BY u.userId")
public class BlogUser {
    public static final String FIND_ALL = "Users.findAll";

    @Id
    @SequenceGenerator(name = "userSequence", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userSequence")
    private long userId;
    private String login;
    private String firstName;
    private String lastName;
}
