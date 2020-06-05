package com.erp.admin.interfaces;


import com.erp.admin.model.network.Header;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudInterface<Request, Response> {
    Header<Response> create(Header<Request> request);
    Header<Response> read(Long id);
    Header<Response> update(Header<Request> request);
    Header<?> delete(Long id);
    Header<List<Response>> search(Pageable pageable);
}
