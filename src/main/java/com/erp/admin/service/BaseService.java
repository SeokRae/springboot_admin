package com.erp.admin.service;

import com.erp.admin.interfaces.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false) // Entity가 필수는 아님
    protected JpaRepository<Entity, Long> baseRepository;

}
