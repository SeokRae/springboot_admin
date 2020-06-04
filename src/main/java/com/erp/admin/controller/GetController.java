package com.erp.admin.controller;

import com.erp.admin.model.SearchParam;
import com.erp.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getReuqest() {
        return "hi";
    }

    /**
     * 쿼리스트링으로 데이터를 요청 하는 컨트롤러
     * @param id 쿼리스트링으로 전달할 id 값
     * @param password 쿼리스트링으로 전달할 password
     * @return 정상적으로 controller를 호출하는 경우 id와 password 값을 리턴
     */
    @GetMapping("/getParam")
    public String getParameter(@RequestParam(name = "id") String id, @RequestParam(name = "password") String password) {
        System.out.println("ID : " + id + "\t PASSWORD : " + password);
        return id + " : " + password;
    }

    /**
     * 자동으로 object에 매핑이 됨
     * @param searchParam client로부터 전달받은 파라미터들을 searchParam의 필드로 알아서 매핑 됨
     * @return 정상적으로 controller를 호출하는 경우 ok값을 리턴
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
     * @param searchParam client로부터 전달받은 파라미터들을 searchParam의 필드로 알아서 매핑 됨
     * @return json으로 convert되어 반환 할 SearchParam
     */
    @GetMapping("/getJsonParameter")
    public SearchParam getJsonParameter (SearchParam searchParam) {
        return searchParam;
    }

    @GetMapping("/header")
    public Header<?> getHeader() {
        return  Header.builder().resultCode("OK").description("OK").build();
    }
}
