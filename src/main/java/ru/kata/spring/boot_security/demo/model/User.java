package ru.kata.spring.boot_security.demo.model;

import com.google.gson.GsonBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Long id;

   @NotEmpty(message = "Name should not be empty")
   @Column(name = "name")
   private String firstName;

   @NotEmpty(message = "Last name should not be empty")
   @Column(name = "last_name")
   private String lastName;

   @NotNull
   @Column(name = "age", nullable = false)
   private int age;

   @NotEmpty(message = "Email should not be empty")
   @Column(name = "email", nullable = false, unique = true)
   private String email;

   @NotEmpty(message = "Password should not be empty")
   @Column(name = "password", nullable = false)
   private String password;

   @NotEmpty(message = "Role should be selected")
   @ManyToMany(fetch = FetchType.LAZY)
   @LazyCollection(LazyCollectionOption.EXTRA)
   @Fetch(FetchMode.JOIN)
   @OrderBy("name ASC")
   @JoinTable(
           name = "users_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<Role> roles = new TreeSet<>();

   public User() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }

   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public String toString() {
      return new GsonBuilder().create().toJson(this);
   }
}