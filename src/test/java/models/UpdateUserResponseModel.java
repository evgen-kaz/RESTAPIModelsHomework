package models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserResponseModel {
    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("first_name")
    String firstName;

    String email, updatedAt;
}
