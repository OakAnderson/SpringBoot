package br.com.erudio.repository;

import br.com.erudio.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.userName =:userName")
    Optional<User> findByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM User u where u.email =:email")
    Optional<User> findByEmail(@Param("email") String email);
}
