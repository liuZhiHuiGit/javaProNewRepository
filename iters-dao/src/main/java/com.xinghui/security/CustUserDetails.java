package com.xinghui.security;


import com.xinghui.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Author chenkuanxing
 * @Date 2019/6/21
 * 自定义安全用户
 */
@Data
public class CustUserDetails extends User implements UserDetails {

    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
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


//    public CustUserDetails(User user) {
//        setId(user.getId());
//        setUserCode(user.getUserCode());
//        setPassword(user.getPassword());
//        setUserName(user.getUserName());
//        setSex(user.getSex());
//        setEmail(user.getEmail());
//        setMobileNo(user.getMobileNo());
//    }

}
