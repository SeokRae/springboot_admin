package com.erp.admin.interfaces;


import com.erp.admin.model.network.Header;

public interface CrudInterface<Request, Response> {
    Header<Response> create(Header<Request> request);
    Header<Response> read(Long id);
    Header<Response> update(Header<Request> request);
    Header<?> delete(Long id);
}
