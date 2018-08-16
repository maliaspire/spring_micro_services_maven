package com.mxninja.example.sms.usersservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode(of = "uid")
public class User {

    @Id
    private UUID uid;

    private String name;

    private String hashPassword;

    private String email;

}
