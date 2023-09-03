package com.harshit.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {

    Long newsId;
    String title;
    String content;
    String author;
    String category;
}
