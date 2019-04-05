package com.softserve.academy.Tips4Trips.service.impl;

import com.softserve.academy.Tips4Trips.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public abstract class ServiceImpl<T,ID,R extends JpaRepository<T,ID>> implements Service<T,ID,R> {

    protected R repository;

    protected ServiceImpl() {

    }

    protected ServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }
}
