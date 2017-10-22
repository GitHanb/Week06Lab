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
    public boolean login(String name,String password)
    {
        return (name.trim().equals("adam") || name.trim().equals("betty") ) && password.equals("password");
    }
}
