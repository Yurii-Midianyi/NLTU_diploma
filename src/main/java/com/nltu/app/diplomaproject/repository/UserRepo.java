package com.nltu.app.diplomaproject.repository;

import com.nltu.app.diplomaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
