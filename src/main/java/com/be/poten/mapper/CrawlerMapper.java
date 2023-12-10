package com.be.poten.mapper;

import com.be.poten.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrawlerMapper {

    void insertArticleList(List<Article> articleList);

}
