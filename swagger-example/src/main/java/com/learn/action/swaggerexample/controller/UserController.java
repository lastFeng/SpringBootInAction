package com.learn.action.swaggerexample.controller;

import com.learn.action.swaggerexample.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author guowf
 * @mail guowf_buaa@163.com
 * @description:
 * @data created in 2019-07-28 11:47
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());


    @ApiOperation(value = "获取用户列表")
    @GetMapping()
    public List<User> getUserList() {
        List<User> list = new ArrayList<>(users.values());
        return list;
    }

    /**
     * @ApiOpreation: 给API增加说明
     * @ApiImplicitParams：给多个参数增加说明,内部使用{@ApiImplicitParam来区分多个参数}
     * @ApiImplicitParam：给参数增加说明
     * */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name="user", value = "用户详细实体User", required = true, dataType = "User")
    @PostMapping
    public String postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据URL的id来获取用户详细信心")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据URL的id来指定更新对象，并根据传递的信息进行更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体User", required = true, dataType = "User")
    })
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);

        if (u == null) {
            return "fail";
        }

        if (user.getName() != null && user.getName() != "") {
            u.setName(user.getName());
        }

        if (user.getAge() != null) {
            u.setAge(user.getAge());
        }

        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据URL的id来制定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
