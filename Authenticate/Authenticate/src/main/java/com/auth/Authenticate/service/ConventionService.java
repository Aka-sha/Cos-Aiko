package com.auth.Authenticate.service;

import com.auth.Authenticate.dao.ConventionRepository;
import com.auth.Authenticate.entity.ConventionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("conventionService")
public class ConventionService {
    @Autowired
    private ConventionRepository repo;

    public ConventionEntity save(ConventionEntity con) {
        repo.save(con);
        return con;
    }

    public List<ConventionEntity> listAll() {
        return repo.findAll();
    }

    public ConventionEntity get(Integer cid) {
        return repo.findById(cid).get();
    }

    public void delete(Integer cid) {
        repo.deleteById(cid);
    }
}
