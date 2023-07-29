package com.vinay.task.service;



import java.util.List;

import com.vinay.task.model.Task;
import com.vinay.task.model.User;



public interface TaskService {

	/**
	 * 
	 * @param task: this task para is give info of task and save task info in the database
	 */
    void createTask(Task task);

    /**
     * 
     * @param id: by task id we are check this task are able are not if able then fetch all info by task and update
     * by task param
     * @param task: this task para is give info of task and update task info in the database
     */
    void updateTask(Long id, Task task);

    /**
     * 
     * @param id:by task id we are check this task are able are not if able then delete all info of particular task and 
     */
    void deleteTask(Long id);

    /**
     * @return: this is return all about task info a list 
     */
    	List<Task> findAll();

    /**
     * 
     * @param user: user param is provide all info of user and by user we are find all task
     * @return: this is return a task list 
     */
    List<Task> findByOwnerOrderByDateDesc(User user);

    /**
     * 
     * @param id: by task id we are check this task are able are not if able then 
 		* change completion status
     */
    void setTaskCompleted(Long id);

    /**
     * 
     * @param id: by task id we are check this task are able are not if able then 
 		* change completion status
     */
    void setTaskNotCompleted(Long id);

    /**
     * 
     * @return: this is return all about task info a list who's those task not asign anyone
     */
    List<Task> findFreeTasks();

    /**
     * 
     * @param taskId: tis is task id and first for check this task id able or not 
     * @return: return all info of task
     */
    Task getTaskById(Long taskId);

    /**
     * 
     * @param task: this is all about of task info and also we are check by task id task able or not
     * @param user: this is all about user and
     * if both are able in database then assign task a user
     */
    void assignTaskToUser(Task task, User user);

    /**
     * 
     * @param task: this is all about of task info and also we are check by task id task able or not
     * and if able then unasiign task
     */
    void unassignTask(Task task);
}
