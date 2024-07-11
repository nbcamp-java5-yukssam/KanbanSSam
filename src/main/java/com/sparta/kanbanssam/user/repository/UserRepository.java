package com.sparta.kanbanssam.user.repository;

import com.sparta.kanbanssam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
