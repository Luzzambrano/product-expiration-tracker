package com.punto929.gdc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tb_user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class GDCUser implements UserDetails {

  @Id
  @SequenceGenerator(sequenceName = "user_id_sequence", name = "user_id_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
  private Long id;

  @Column
  protected String username;

  @Column
  @JsonIgnore
  protected String password;

  @Column
  protected String email;

  @Column
  protected String firstName;

  @Column
  protected String lastName;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ADMIN"));
  }

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  protected List<Token> tokens;

  @Override
  @JsonIgnore
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
