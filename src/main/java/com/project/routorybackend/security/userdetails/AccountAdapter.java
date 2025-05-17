package com.project.routorybackend.security.userdetails;

import com.project.routorybackend.account.model.Account;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Map;

@Getter
public class AccountAdapter extends User {
    private final Account account;
    private final Map<String, Object> attributes;

    public AccountAdapter(Account account) {
        super(account.getEmail(), account.getPassword(), Collections.emptySet());
        this.account = account;
        this.attributes = Collections.emptyMap();
    }

    public AccountAdapter(Account account, Map<String, Object> attributes) {
        super(account.getEmail(), account.getPassword(), Collections.emptySet());
        this.account = account;
        this.attributes = attributes;
    }
}
