package com.yourgeekmate.quarkus;

import com.yourgeekmate.quarkus.entities.Article;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerResponse;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/blog")
@Blocking
public class BlogResource {

    @Inject
    private EntityManager entityManager;

    @Inject
    Mutiny.Session session;

    @GET
    @Path("/articles/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Article>> getAllArticles() {
        // In this case, it makes sense to return a Uni<List<Fruit>> because we return a reasonable amount of results
        // Consider returning a Multi<Fruit> for result streams
        return session.createNamedQuery(Article.FIND_ALL, Article.class)
                .getResultList();
    }

    @PUT
    @Path("/article/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Uni<AddArticleRequest> saveArticle(AddArticleRequest addArticleRequest, HttpServerResponse response) {

        return session.persist(addArticleRequest.toEntity())
                .chain(session::flush)
                .onItem().transform(ignore -> {
                    response.setStatusCode(201);
                    return addArticleRequest;
                });
    }
}
