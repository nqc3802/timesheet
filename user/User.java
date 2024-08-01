package com.example.timesheet.user;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.timesheet.user_role.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String password;
    private Date dob;
    private String gender;
    private String email;
    @Builder.Default
    private String address = "";
    private String user_type;
    private String level;
    private int position_id;
    private int branches_id;
    @Builder.Default
    private int trainner_id = 0;
    @Builder.Default
    private int salary = 0;
    @Builder.Default
    private String state = "Active";
    @Builder.Default
    private Time morning_start_time = Time.valueOf("08:30:00");
    @Builder.Default
    private Time morning_end_time = Time.valueOf("12:00:00");
    @Builder.Default
    private Time afternoon_start_time = Time.valueOf("13:00:00");
    @Builder.Default
    private Time afternoon_end_time = Time.valueOf("17:30:00");

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> userRoles;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // userRole -> new SimpleGrantedAuthority(userRole.getRole().getName())

        // return List.of(new SimpleGrantedAuthority(roles));
        return userRoles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (state.equals("Active")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
