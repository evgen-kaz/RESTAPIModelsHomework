package models;
import lombok.Data;

@Data
public class GetUserResponseModel {
    UserDataResponseModel data;
    Object support;
    Object _meta;
}
