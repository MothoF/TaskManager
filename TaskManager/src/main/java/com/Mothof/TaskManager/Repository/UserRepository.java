package com.Mothof.TaskManager.Repository;

import com.Mothof.TaskManager.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UserRepository implements JpaRepository<Users, Integer> {
}
