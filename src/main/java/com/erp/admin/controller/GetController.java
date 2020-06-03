package com.erp.admin.controller;

import com.erp.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getReuqest() {
        return "hi";
    }

    @GetMapping("/getParam")
    public String getParameter(@RequestParam(name = "id") String id, @RequestParam(name = "password") String password) {
        System.out.println("ID : " + id + "\t PASSWORD : " + password);
        return id + " : " + password;
    }

    /**
     * 자동으로 object에 매핑이 됨
     * @param searchParam
     * @return
     */
    @GetMapping("/getMultiParameter")
    public String getMultiParameter (SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        return "ok";
    }

    /**
     * Json으로 자동으로 변환되어 반환함
     * @param searchParam
     * @return
     */
    @GetMapping("/getJsonParameter")
    public SearchParam getJsonParameter (SearchParam searchParam) {
        return searchParam;
    }

}
