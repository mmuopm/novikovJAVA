package Proj.library.dto;


import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class LoginDTO {
    private String login;
    private String password;
}
