package models;
import lombok.Data;

@Data
public class UpdateUserResponseModel {
    String last_name, first_name, email, updatedAt;
}
