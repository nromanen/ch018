/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.dao;

import com.ch018.library.entity.PersonRole;
import java.util.List;

/**
 *
 * @author Edd Arazian
 */
public interface PersonRoleDao {
    
    void save(PersonRole personRole);
    List<PersonRole> getAll();
    
}
