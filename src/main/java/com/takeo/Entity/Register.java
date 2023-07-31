package com.takeo.Entity;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document (collection = "Register")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
	@Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long phoneNumber;
    private String address;
    private String dob;
    private String sex;
    private List<Inventory> stocks = new ArrayList<>();
   
}
