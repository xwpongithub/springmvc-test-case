package cn.xwplay.springtest.controller;

import cn.hutool.core.collection.CollUtil;
import cn.xwplay.springtest.domain.form.QueryUserForm;
import cn.xwplay.springtest.domain.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

  @GetMapping
  public List<UserVO> list() {
    return CollUtil.newArrayList(new UserVO(),new UserVO(),new UserVO());
  }

  // 可以使用正则表达式：
  @GetMapping("{id:\\d+}")
  public UserVO info(@PathVariable Long id) {
    return new UserVO(id,"小明",12,"男",60);
  }

  @GetMapping("query")
  public UserVO queryInfo(@RequestParam String username) {
    return new UserVO(1L,username,12,"女",100);
  }

  // 可通过query参数请求，也可通过form-data形式请求
  @GetMapping("query/form")
  public UserVO queryInfoByForm(QueryUserForm form) {
    // ReflectionToStringBuilder.toString(form,ToStringStyle.MULTIPLE);
    return new UserVO(1L,form.getUsername(),form.getAge(),"男",30);
  }

  @GetMapping("page")
  public List<UserVO> page(@PageableDefault(page=1,size = 20,sort = {"username"},direction = Sort.Direction.ASC) Pageable page) {
    System.out.println(page.getPageNumber());
    System.out.println(page.getPageSize());
    page.getSort().stream().forEach(order -> {
      System.out.println(order.getProperty()+":"+order.getDirection().toString());
    });
    return CollUtil.newArrayList(new UserVO(),new UserVO(),new UserVO());
  }

  @GetMapping("simple/view")
  @JsonView(UserVO.SimpleView.class)
  public UserVO simpleView () {
    return new UserVO(1L,"小明",12,"男",60);
  }

  @GetMapping("complex/view")
  @JsonView(UserVO.ComplexView.class)
  public UserVO complexView () {
    return new UserVO(1L,"小明",12,"男",60);
  }

}
