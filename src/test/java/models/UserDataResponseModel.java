package models;

import lombok.Data;

@Data
public class UserDataResponseModel {
    //поля (объект) в поле data.

    int id;
    String email;
    String first_name;
    String last_name;
    String avatar;
}
