package com.rate.api.repository;

import com.rate.api.model.Admin;

import java.util.Optional;

public interface AdminRepository extends UserRepository{
    Optional<Admin> findAdminByLogin(String login);

}
