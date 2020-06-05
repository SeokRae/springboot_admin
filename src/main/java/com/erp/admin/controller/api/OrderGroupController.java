package com.erp.admin.controller.api;

import com.erp.admin.controller.CrudController;
import com.erp.admin.model.entity.OrderGroup;
import com.erp.admin.model.network.request.OrderGroupApiRequest;
import com.erp.admin.model.network.response.OrderGroupApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
}
