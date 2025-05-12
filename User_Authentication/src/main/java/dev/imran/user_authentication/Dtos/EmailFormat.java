package dev.imran.user_authentication.Dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.Format;

@Getter
@Setter
@JsonSerialize
public class EmailFormat implements Serializable {

    private String to;
    private String from;
    private String subject;
    private String content;

}
