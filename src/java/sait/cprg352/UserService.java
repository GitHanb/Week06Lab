/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

/**
 *
 * @author hanzh
 */
public class UserService
{
    public User login(String name,String password)
    {
        User user=null;
        if((name.trim().equals("adam") || name.trim().equals("betty") ) && password.equals("password") ) 
        {
            user = new User(name,null);
        }
        return user;
    }
}
