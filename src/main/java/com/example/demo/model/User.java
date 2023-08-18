package com.example.demo.model;

import com.example.demo.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
@Entity
@Table(name = "usr")



public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String username;
    private String password;
    private boolean active;//активен ли пользователь
    private String email;
    private String activationCode;




    @ElementCollection(targetClass = Role.class,fetch =FetchType.EAGER )//данные из Енам
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)//указываем тип enum
    private Set<Role> roles;//назначаем атрибуту Set (ролей)


    public User(Long user_id, String username, String password, boolean active, String email, String activationCode, Set<Role> roles) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.email = email;
        this.activationCode = activationCode;
        this.roles = roles;
    }


    public User() {

    }
    public String getCodeForUserList(){
        return activationCode != null ? getActivationCode() : "<none>";   }
    public String getEmailForUserList(){
        return email != null ? getEmail() : "<none>";   }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
