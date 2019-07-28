package com.learn.action.swaggerexample.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-07-28 12:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserController.class)
public class UserControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder request =  null;

        // 1. get查一下User列表，应该为空
        request = get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        // 2. post提交一个User
        request = post("/user/")
                .param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");

        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 3. get获取user列表，应该有刚才插入的数据
        request = get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        // 4. 获取id为i的user信息
        request = get("/user/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));

        // 5. put 修改id为1的user
        request = put("/user")
                .param("id", "1")
                .param("name", "测试终极大师");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        // 6. 获取id为i的user信息
        request = get("/user/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试终极大师\",\"age\":20}]")));

        // 7. 删除id为1的user
        request = delete("/user/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 8. 查询列表，应该为空
        request = get("/user/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }
}
