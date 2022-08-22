package com.wh.managementSystem.repository;

import com.wh.managementSystem.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static junit.framework.TestCase.*;


@SpringBootTest
public class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void shouldSaveArticle(){
        Article article = articleRepository.save(getArticle());
        assertNotNull(article);
    }

    @Test
    void shouldFindArticleById(){
        Optional<Article> article = articleRepository.findById(1);
        assertTrue(article.isPresent());
        assertEquals("leg",article.get().getName());
    }

    private Article getArticle() {
        return new Article(1,"leg",12);
    }
}
