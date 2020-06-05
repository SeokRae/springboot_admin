package com.erp.admin.controller.api;

import com.erp.admin.controller.CrudController;
import com.erp.admin.model.entity.Partner;
import com.erp.admin.model.network.request.PartnerApiRequest;
import com.erp.admin.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
}
