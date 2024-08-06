package com.futguesser.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author 34661
 */

@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority{
    
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
    
}
