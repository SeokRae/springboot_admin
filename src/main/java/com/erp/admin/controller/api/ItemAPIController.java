package com.erp.admin.controller.api;

import com.erp.admin.controller.CrudController;
import com.erp.admin.model.entity.Item;
import com.erp.admin.model.network.request.ItemApiRequest;
import com.erp.admin.model.network.response.ItemApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemAPIController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {
}
