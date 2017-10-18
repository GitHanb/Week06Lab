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
