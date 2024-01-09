package com.digital.signage.models;

//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author -Ravi Kumar created on 12/23/2022 1:30 AM
 * @project - Digital Sign-edge
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    //@ApiModelProperty(notes = "API Response status true/false", required = true)
    private Boolean status;
    //@ApiModelProperty(notes = "API response message to display.", required = true)
    private String message;
    //@ApiModelProperty(notes = "API Response data if status is true")
    private Object response;
}
