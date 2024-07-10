package com.csis3275.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class userData {
    private String document_id;
    private String username;
    private String password;
    private String dob;

    public userData(){
        this.setDocument_id(this.username);
    }
}
