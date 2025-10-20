package models;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetUsersResponseModel {
    String page;

    @JsonProperty("per_page")
    String perPage;

    String total;
    
    @JsonProperty("total_pages")
    String totalPages;

    Object [] data;
    Object support;
    Object _meta;
}
