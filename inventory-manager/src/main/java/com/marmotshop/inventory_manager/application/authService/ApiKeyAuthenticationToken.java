package com.marmotshop.inventory_manager.application.authService;

import com.marmotshop.inventory_manager.domain.shared.RoleEnum;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
    public ApiKeyAuthenticationToken() {
        // TODO: now i only have admin and manager tole in this server, because it's not
        // designed for user, to simplify the proble, only manager is able to access all
        // routes now, maybe later I will set some routes like create an order for
        // manager only and admin can acccess otehr routes 
        super(AuthorityUtils.createAuthorityList(RoleEnum.ADMIN.toString()));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        // since i don't plan to have user data in this micro server, so i only check
        // the apiKey token
        return "Admin";
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("Hey, you cannot change authentication context.");
    }
}
