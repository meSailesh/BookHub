package com.teamAlpha.bookHub.User.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teamAlpha.bookHub.User.Entities.User;




@Repository
public interface UserDatabase extends CrudRepository<User,Long> {

}
