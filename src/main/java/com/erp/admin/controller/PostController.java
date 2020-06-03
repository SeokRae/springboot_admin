package com.erp.admin.controller;

import com.erp.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    /**
     * ajax 검색
     * html form 태그 검색
     * json, xml, multipart-form / text-plain
     *
     * Content-Type / application/json
     *
     * @return
     */
    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

    @PutMapping
    public void put() {

    }

    @PatchMapping
    public void patch() {

    }

    @DeleteMapping
    public void delete() {

    }
}
