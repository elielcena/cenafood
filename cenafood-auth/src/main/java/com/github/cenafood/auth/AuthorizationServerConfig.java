package com.github.cenafood.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()

                .withClient("cenafood-web")
                .secret(passwordEncoder.encode("123"))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("write", "read")

                .and()
                .withClient("client-credential")
                .secret(passwordEncoder.encode("123"))
                .authorizedGrantTypes("client_credentials")
                .scopes("write", "read")

                // http://localhost:8081/oauth/authorize?response_type=code&client_id=authorization-code&state=code&redirect_uri=http://client
                .and()
                .withClient("authorization-code")
                .secret(passwordEncoder.encode("123"))
                .authorizedGrantTypes("authorization_code")
                .scopes("write", "read")
                .redirectUris("http://client")

                .and()
                .withClient("resource-server")
                .secret(passwordEncoder.encode("resource-password"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // security.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

}
