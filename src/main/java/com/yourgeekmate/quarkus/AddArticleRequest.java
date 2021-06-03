package com.yourgeekmate.quarkus;

import com.yourgeekmate.quarkus.entities.Article;
import lombok.Data;

import java.sql.Date;

@Data
public class AddArticleRequest {
    private String heading;
    private String content;
    private String postSlug;


    public Article toEntity(){
        Article a = new Article();
        a.setContent(this.content);
        a.setHeading(this.heading);
        a.setCreatedAt(new Date(System.currentTimeMillis()));
        a.setPostSlug(this.postSlug);
        return a;
    }
}
