package com.yangyag.msa.auth.service;

public interface AuthQueryService {
    String authenticate(String userId, String password);
}
