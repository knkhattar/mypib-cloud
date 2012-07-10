/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

/**
 *
 * @author rahul.tiple
 */
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class FirstBean
 */
@Stateless
@Remote
public class FirstBean implements FirstBeanRemote {

    /**
     * Default constructor. 
     */
    public FirstBean() {
        // TODO Auto-generated constructor stub
    }
    public String sayHello() {
    	System.out.println("Hi its ierewr n the staeless");
        return "Hello";
    }
}