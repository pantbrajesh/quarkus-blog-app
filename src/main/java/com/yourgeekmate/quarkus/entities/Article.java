package com.yourgeekmate.quarkus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = Article.FIND_ALL, query = "SELECT f FROM Article f ORDER BY f.articleId")
public class Article {

    public static final String FIND_ALL = "Articles.findAll";

    @Id
    @SequenceGenerator(name = "articlesSequence", sequenceName = "article_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "articlesSequence")
    private long articleId;
    private String heading;
    private String content;
    private String postSlug;
    private Date createdAt;
    @ManyToOne
    private BlogUser blogUser;
}
