package models;

import lombok.Data;

@Data
public class GetUsersResponseLombokModel {
    String page, per_page, total, total_pages;
    Object [] data;
    Object support;
    Object _meta;
}
