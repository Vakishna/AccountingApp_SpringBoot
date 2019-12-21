package com.thayalantech.adhd;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.Enumerated;



@Entity
@Getter
@EqualsAndHashCode(exclude = "accounts")
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private String business_name;
    
    @NotNull
    private long abn;
    private long acn;
    
    @NotNull
    private String primary_phone;
    private String email_address;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Account> accounts;

    public Company(String business_name,Account[] accounts,String email_address, String primary_phone,long abn,long acn) {
        this.business_name = business_name;
        this.abn = abn;
        this.acn = acn;
        this.primary_phone = primary_phone;
        this.email_address = email_address;
        this.accounts = Stream.of(accounts).collect(Collectors.toSet());
        this.accounts.forEach(x -> x.setCompany(this));
    }
}
