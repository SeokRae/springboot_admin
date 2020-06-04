package com.erp.admin.controller;

import com.erp.admin.interfaces.CrudInterface;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.UserAPIRequest;
import com.erp.admin.model.network.response.UserAPIResponse;
import com.erp.admin.service.UserAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserAPIController implements CrudInterface<UserAPIRequest, UserAPIResponse> {

    @Autowired
    private UserAPIService userAPIService;

    @Override
    @PostMapping("")
    public Header<UserAPIResponse> create(@RequestBody Header<UserAPIRequest> userAPIRequest) {
        log.info(" LOG - create : {} ", userAPIRequest);
        return userAPIService.create(userAPIRequest);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserAPIResponse> read(@PathVariable(name = "id") Long id) {
        log.info(" LOG - read : {} ", id);
        return userAPIService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserAPIResponse> update(@RequestBody Header<UserAPIRequest> userAPIRequest) {
        log.info(" LOG - update {} ", userAPIRequest);
        return userAPIService.update(userAPIRequest);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<?> delete(@PathVariable(name = "id") Long id) {
        log.info(" LOG - delete {} ", id);
        return userAPIService.delete(id);
    }
}
