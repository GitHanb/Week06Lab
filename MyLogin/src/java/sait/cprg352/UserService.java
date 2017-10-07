/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

import java.io.Serializable;

/**
 *
 * @author hanzh
 */
public class UserService implements Serializable
{
    private String username;
    private String password;

    public UserService() 
    {
        username="";
        password="";
    }

    public UserService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(String username, String password)
    {
        if((username.equals("adam")||username.equals("betty"))&& password.equals("password"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
