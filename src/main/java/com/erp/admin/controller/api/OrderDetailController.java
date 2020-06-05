package com.erp.admin.controller.api;

import com.erp.admin.controller.CrudController;
import com.erp.admin.model.entity.OrderDetail;
import com.erp.admin.model.network.request.OrderDetailApiRequest;
import com.erp.admin.model.network.response.OrderDetailApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

}
