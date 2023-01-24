package jpabook.jpashop.domain;

import lombok.Getter;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address { // 값타임은 변경 되면 안됨 -> 생성할때만 세팅
    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    protected Address() {

    }
}
