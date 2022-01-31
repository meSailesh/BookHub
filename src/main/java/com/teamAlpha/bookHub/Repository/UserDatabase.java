package com.teamAlpha.bookHub.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teamAlpha.bookHub.Entities.User;

@Repository
public interface UserDatabase extends CrudRepository<User,Long> {

}
