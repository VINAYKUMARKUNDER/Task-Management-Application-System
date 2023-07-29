package com.vinay.task.service;


import java.util.List;

import com.vinay.task.model.User;



public interface UserService {
	
	/**
	 * 
	 * @param user: this is all about user info by provde a user
	 * this user data store in database
	 * @return: return a particular user all info (id, name, email,password, image)
	 */
    User createUser(User user);
    
    /**
     * 
     * @param user: this is all about user info by provide by a database
     * @return: return a user and also change user roll if admin want
     */

    User changeRoleToAdmin(User user);

    /**
     * 
     * @return: return a full all admin info
     */
    List<User> findAll();

    /**
     * 
     * @param email: this is user mail first fall check this user mail avalible are not 
     * @return: if able then return a particular full info user
     */
    User getUserByEmail(String email);

    /**
     * 
     * @param : email this is user mail first fall check this user mail avalible are not
     * @return: return a boolean type status true or false 
     */
    boolean isUserEmailPresent(String email);

    /**
     * 
     * @param userId: by userId check user able or not in database
     * @return: return full info of a user
     */
    User getUserById(Long userId);

    /**
     * 
     * @param id:  by Id check user able or not in database
     */
    void deleteUser(Long id);
    
    /**
     * 
     * @param email: by email check this email store in database or not if not then show erroe 
     * if able or true then check password;
     * @param password: if password and email both or true then return a user info
     * if not true then show erroe in ui
     * @return: return a user info
     */
    
    String loginUser(String email, String password);
}
