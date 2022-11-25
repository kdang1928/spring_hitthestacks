package com.revature.hitthestacks.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "FROM User WHERE email=:email")
    Optional<User> findByEmail(String email);

    @Query(value = "UPDATE User SET is_active=false WHERE id=:id")
    void deactivate(String id);
}
