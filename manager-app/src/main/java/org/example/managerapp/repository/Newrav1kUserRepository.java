package org.example.managerapp.repository;

import org.example.managerapp.entity.Newrav1kUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Newrav1kUserRepository extends JpaRepository<Newrav1kUser, Long> {

    @Query("select u from Newrav1kUser u where u.email = :email")
    Optional<Newrav1kUser> findNewrav1kUserByUsername(@Param("email") String username);

}