package com.auth.Authenticate.service;

/**
 * This class serves as a bridge between the ConventionController and the ConventionRepository
 */

import com.auth.Authenticate.dao.ConventionRepository;
import com.auth.Authenticate.entity.ConventionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("conventionService")
public class ConventionService {
    @Autowired
    private ConventionRepository repo;

    /**
     *  Save the convention to DB
     *
     * @param con convention being saved
     * @return the new convention
     */
    public ConventionEntity save(ConventionEntity con) {
        repo.save(con);
        return con;
    }

    /**
     * Select all from the Conventions table
     *
     * @return list of all convention in the conventions table
     */
    public List<ConventionEntity> listAll() {
        return repo.findAll();
    }

    /**
     * Select a convention by its unique id
     *
     * @param cid unique id for convention
     * @return
     */
    public ConventionEntity get(Integer cid) {
        return repo.findById(cid).get();
    }

    /**
     * Delete a convention by its unique id
     *
     * @param cid the conventions unique id
     */
    public void delete(Integer cid) {
        repo.deleteById(cid);
    }
}
