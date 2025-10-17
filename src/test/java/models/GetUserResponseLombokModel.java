package models;
import lombok.Data;

@Data
public class GetUserResponseLombokModel {
    UserDataResponseModel data; //главный ответ. И в нем есть поле data
    Object support;
    Object _meta;
}
