package com.thayalantech.adhd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import java.math.BigDecimal;
import javax.persistence.Enumerated;
import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @NotNull(message = "Friendly Reference for Account. E.g Westpac Debit")
    public String friendlyname;


    @NotNull(message = "Account Types Include: Equity, Asset, Liability, Expense, Revenue")
    @Enumerated(EnumType.STRING)
    public AccountTypes account_type;


    @NotNull(message = "Please Include a description, for authorized third party reference")
    @Size(min = 5, max = 500, message = "Descriptions must have between {min} and {max} characters")
    public String description;
    
    @NotNull(message = "Credit Balance: Essential for balancing requirements")
    public BigDecimal balancecr;
    

    @NotNull(message = "Debit Balance: Essential for balancing requirements")
    public BigDecimal balancedr;


    protected Account() {  }


    @ManyToOne
    @JoinColumn
    private Company company;
    
    public void setCompany(Company company) {
        this.company = company;
    }



    public Account(String friendlyname, AccountTypes accountType, String description, BigDecimal BalanceCR, BigDecimal BalanceDR) {
        this.friendlyname = friendlyname;
        this.account_type = accountType;
        this.description = description;
        this.balancedr = BalanceDR;
        this.balancecr = BalanceCR;
    }

}
