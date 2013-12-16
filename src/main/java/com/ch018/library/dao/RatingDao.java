/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.dao;

import com.ch018.library.entity.Rating;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public interface RatingDao {
    
    void save(Rating rating);
    void delete(int id);
    void update(Rating rating);
    List<Rating> getAll();
    List<Rating> getEq(float rating);
    List<Rating> getLt(float rating);
    List<Rating> getGt(float rating);
    
    
}
