package com.blog.constant;

public class EndPoints {

    public static final String VERSION="/v1";
    public static final String API="/api";
    public static final String ROOT=API+VERSION;

    public static final String USER="/user";
    public static final String POST="/post";
    public static final String CATEGORY="/category";
    public static final String COMMENT="/comment";
    public static final String REGISTER="/register";
    public static final String LOGIN="/login";

    public static final String SAVE="/save/{id}";
    public static final String UPDATE="/update";
    public static final String DELETE="/delete";
    public static final String DELETEBYID="/delete/{id}";
    public static final String FINDALL="/findall";
    public static final String FINDBYNAME="/findByName/{name}";
    public static final String FINDBYTITLE="/findByTitleIgnoreCase/{title}";
    public static final String FINDBYID="/findbyid/{id}";
    public static final String GETALL="/getall";
    public static final String FINDALLBYNAME="/findAllByName/{name}";
    public static final String FINDALLPOSTBYUSERID="/findAllPostById/{id}";
    public static final String FINDALLCOMMENTBYID="/findAllCommentById/{id}";
    public static final String FINDALLBYORDERBYRELEASEDATE="/findAllByOrderByReleaseDate";
    public static final String FINDALLBYPOSTLISTSBYID="/findAllByPostListById/{id}";
    public static final String FINDBYCATEGORYLISTID="/findByCategoryList_Id/{categoryId}";
    public static final String CREATE="/create/{id}";
    public static final String FINDALLBYSTARTWİTH="/findAllByNameStartingWith/{word}";
}
