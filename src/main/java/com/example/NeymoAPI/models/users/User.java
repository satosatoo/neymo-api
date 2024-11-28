package com.example.NeymoAPI.models.users;

import com.example.NeymoAPI.models.expense.Expense;
import com.example.NeymoAPI.models.investment.Investment;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private int age;
    private String email;
    @CreationTimestamp
    private Date registrationDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;
    @OneToMany(mappedBy = "user")
    private List<Investment> investments;

    public User() {
    }

    public User(int age, String email, String password) {
        this.age = age;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {return UserDetails.super.isAccountNonExpired();}
    @Override
    public boolean isAccountNonLocked() {return UserDetails.super.isAccountNonLocked();}
    @Override
    public boolean isCredentialsNonExpired() {return UserDetails.super.isCredentialsNonExpired();}
    @Override
    public boolean isEnabled() {return UserDetails.super.isEnabled();}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return List.of(new SimpleGrantedAuthority(role.name()));}
    @Override
    public String getUsername() {return email;}
    public Long getUser_id() {return user_id;}
    public void setUser_id(Long user_id) {this.user_id = user_id;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Date getRegistrationDate() {return registrationDate;}
    public void setRegistrationDate(Date registrationDate) {this.registrationDate = registrationDate;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public List<Expense> getExpenses() {return expenses;}
    public void setExpenses(List<Expense> expenses) {this.expenses = expenses;}
    public List<Investment> getInvestments() {return investments;}
    public void setInvestments(List<Investment> investments) {this.investments = investments;}
}
