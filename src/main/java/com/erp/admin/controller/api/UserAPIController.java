package com.erp.admin.controller.api;

import com.erp.admin.controller.CrudController;
import com.erp.admin.model.entity.User;
import com.erp.admin.model.network.Header;
import com.erp.admin.model.network.request.UserApiRequest;
import com.erp.admin.model.network.response.UserApiResponse;
import com.erp.admin.model.network.response.UserOrderInfoApiResponse;
import com.erp.admin.service.UserAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserAPIController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserAPIService userAPIService;

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", size = 15, direction = Sort.Direction.ASC) Pageable pageable) {
        return userAPIService.search(pageable);
    }

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        return userAPIService.orderInfo(id);
    }
}
