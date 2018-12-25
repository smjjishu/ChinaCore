package com.example.project.controller.h5;

import com.example.project.comm.BaseParam;
import com.example.project.comm.ResponseData;
import com.example.project.model.*;
import com.example.project.xml.xmlhelper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Api("测试模块")
@RestController
@RequestMapping({"/api/h5/custom"})
public class CustomController {

    @Value("${comm.one}")
    private String INFOMATION;
    //---------------------------------------------------------------------------
    //------------------------- 应用程序测试------------------------------
    //---------------------------------------------------------------------------

    @RequestMapping(value = "/aaa", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> aaaa(@RequestBody BaseParam baseParam) throws Exception {
        List<HealthExamPackItem> list = xmlhelper.GetList();
        return new ResponseData<Object>(list);
    }

    @RequestMapping(value = "/bbb", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> bbb(@RequestBody UserInfo userInfo) throws Exception {
        HealthExamPackDetail model = xmlhelper.GetSingle(userInfo.getId());
        return new ResponseData<Object>(model);
    }

    @RequestMapping(value = "/ccc", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Object> ccc(@RequestBody UserInfo userInfo) throws Exception {
        return new ResponseData<Object>(INFOMATION);
    }


    @RequestMapping(value = "/javatest", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<Object> javatest() throws Exception {
        return new ResponseData<Object>(INFOMATION);
    }


}
