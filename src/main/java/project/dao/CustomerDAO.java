package project.dao;

import project.Entity.User;

public interface CustomerDAO 
{
	public void insert(User customer);
	public User findByCustomerId(int custId);
}




