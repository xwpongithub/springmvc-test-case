package cn.xwplay.springtest.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

public abstract class AbstractMockMvc {

  protected WebApplicationContext webApplicationContext;
  protected MockMvc mockMvc;

  protected AbstractMockMvc(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  protected ResultActions perform(MockHttpServletRequestBuilder builder) {
    try {
      return mockMvc.perform(builder);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  protected JsonPathResultMatchers matchJson(String jsonPath) {
    return MockMvcResultMatchers.jsonPath(jsonPath);
  }

  protected void printJSONResult(MvcResult rs) {
    try {
      MockHttpServletResponse resp = rs.getResponse();
      resp.setCharacterEncoding("UTF-8");
      StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
      System.out.println("==========================================");
      System.out.println("执行方法:"+stackTraceElement.getMethodName());
      System.out.println(StrUtil.format("状态码:{}",resp.getStatus()));
      System.out.println(StrUtil.format("请求Response:{}",rs.getResponse().getContentAsString()));
      System.out.println("==========================================");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  private ResultHandler print() {
    return MockMvcResultHandlers.print();
  }

}
