package com.example.pullpointdev.user.model;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.security.Role;
import com.example.pullpointdev.wallet.model.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name="user_data")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="phone")
    private String phone;

    @Column(name="favourites")
    @OneToMany
    @JsonIgnore
    private List<Artist> favourites;

    @Column(name="is_artist")
    private Boolean isArtist;

    @OneToMany
    @JsonIgnore
    private List<Artist> artists;

    @OneToOne
    private Wallet wallet;

    @Nullable
    private String token;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword(){
        return token;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @JsonIgnore
    private Role role;
}
