package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.R;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author yzz
 * @Date 2023/5/29 下午5:08
 */
@Slf4j
@RestController
@RequestMapping("/testmq")
public class TestMq {



    @GetMapping("/sendmq")
    public R sendmq()
    {

        return R.ok();
    }
}
