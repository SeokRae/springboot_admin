package com.erp.admin.controller;

import com.erp.admin.interfaces.CrudInterface;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.ItemAPIRequest;
import com.erp.admin.model.network.response.ItemAPIResponse;
import com.erp.admin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemAPIController implements CrudInterface<ItemAPIRequest, ItemAPIResponse> {

    @Autowired
    private ItemService itemService;

    @Override
    @PostMapping("") // /api/item
    public Header<ItemAPIResponse> create(@RequestBody Header<ItemAPIRequest> request) {
        return itemService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/item/{id}
    public Header<ItemAPIResponse> read(@PathVariable Long id) {
        return itemService.read(id);
    }

    @Override
    @PutMapping("") // /api/item
    public Header<ItemAPIResponse> update(@RequestBody Header<ItemAPIRequest> request) {
        return itemService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/item/{id}
    public Header<?> delete(@PathVariable Long id) {
        return itemService.delete(id);
    }
}
