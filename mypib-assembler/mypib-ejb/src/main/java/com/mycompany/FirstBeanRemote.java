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

@Remote
public interface FirstBeanRemote {
	 public String sayHello();
}
