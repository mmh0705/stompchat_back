package com.example.sample.demo.domain.address;

import com.example.sample.demo.domain.member.Member;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;


@Getter
@Embeddable
public class Address
{
    private String city;
    private String street;
    private String zipcode;

    protected Address(){

    }

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
