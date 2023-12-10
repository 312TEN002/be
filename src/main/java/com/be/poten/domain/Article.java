package com.be.poten.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {

    private String articleId;
    private String categoryId;
    private String articleCreatedDate;
    private String company;
    private String title;
    private String content;
    private String imgUrl;
    private String url;

}