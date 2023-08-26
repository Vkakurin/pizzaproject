package com.example.demo.model;

import com.example.demo.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;


/**
 * the User entity class with private fields in which
 * will be recorded and stored in the database
 */
@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @NotBlank(message = "OrderId cannot be empty")
    private Long user_id;

    @Length(max=255, message = "Username can't  too long")
    @NotBlank(message = "username cannot be empty")
    private String username;

    @Length(max=255, message = "password can't  too long")
    @NotBlank(message = "password cannot be empty")
    private String password;
    @NotBlank(message = "password confirmation cannot be empty")
    @Transient
    private String password2;



    /**
     * Parameter: isActivateUser
     */
    private boolean active;//активен ли пользователь

    @Length(max=255, message = "email can't  too long")
    @NotBlank(message = "email cannot be empty")
    @Email(message = "Email is not correct")
    private String email;

    @Length(max=255, message = "")
    private String activationCode;

    /**
     * make Set of Roles.
     * Make  the table with columns "user_role" and "user_id"
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)//данные из Енам
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)//указываем тип enum
    private Set<Role> roles;//назначаем атрибуту Set (ролей)

    /**
     * Constructor with all arguments.
     * can be change annotation:"@AllArgsConstructor"
     */
    public User(Long user_id, String username, String password, boolean active, String email, String activationCode, Set<Role> roles) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.email = email;
        this.activationCode = activationCode;
        this.roles = roles;
    }
    /**
     * Constructor without arguments.
     * can be change annotation:"@NoArgsConstructor"
     */
    public User() {

    }
    /**
     * method  to use parameter "activationCode" in "userList.ftlh"
     * @return  activationCode or "<none>" if activationCode == null.
     * I use this method to debug the frontend
     */
    public String getCodeForUserList() {
        return activationCode != null ? getActivationCode() : "<none>";
    }
    /**
     * method  to use parameter "email" in "userList.ftlh"
     * @return email or "<none>" if email == null.
     * I use this method to debug the frontend
     */
    public String getEmailForUserList() {
        return email != null ? getEmail() : "<none>";
    }

    /***
     * Getters and Setters all fields.
     * Can be change annotation:"@Getter and @Setter" or "@Data "
     * @return
     */
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

    /**
     * Override methods implement UserDetails to authorisation.
     * @return
     */
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


}
