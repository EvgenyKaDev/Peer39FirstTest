package com.peer.testapp.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peer.testapp.controller.exception.Error;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Result {

    private String url;
    private String text;
    private Error error;

}
