package com.example.timesheet.user;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.timesheet.user_role.UserRole;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
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
    // @Enumerated(EnumType.STRING)
    // @Column(name = "role")
    @Builder.Default
    private String role = "User";
    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRole;

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

        return List.of(new SimpleGrantedAuthority(role));
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
