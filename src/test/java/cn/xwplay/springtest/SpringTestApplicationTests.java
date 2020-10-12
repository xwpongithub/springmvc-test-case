package cn.xwplay.springtest;

import cn.xwplay.springtest.util.AbstractMockMvc;
import cn.xwplay.springtest.util.MockResponseStatus;
import cn.xwplay.springtest.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

// jsonPath文档： https://github.com/json-path/JsonPath

@SpringBootTest
class SpringTestApplicationTests extends AbstractMockMvc {

  String baseUrl = "http://localhost:8080";

  public SpringTestApplicationTests(WebApplicationContext webApplicationContext) {
    super(webApplicationContext);
  }

  @Test
  void testListSuccess() throws Exception {
    String url = baseUrl + "/user";
    printJSONResult(perform(TestUtil.getJSON(url))
            .andExpect(MockResponseStatus.STATUS_OK)
            .andExpect(matchJson("$.length()").value(3))
            .andReturn());
  }

  @Test
  void testInfoSuccess() throws Exception {
    String url = baseUrl + "/user/"+1;
    printJSONResult(perform(TestUtil.getJSON(url))
            .andExpect(MockResponseStatus.STATUS_OK)
            .andExpect(matchJson("$.id").value(1))
            .andReturn());
  }

  @Test
  void testQuerySuccess() throws Exception {
    String url = baseUrl + "/user/query";
    MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
    map.add("username","小明");
    printJSONResult(
             perform(TestUtil.getJSON(url).queryParams(map))
            .andExpect(MockResponseStatus.STATUS_OK)
            .andExpect(matchJson("$.id").value(1))
            .andReturn());
  }

  @Test
  void testQueryByFormSuccess() throws Exception {
    String url = baseUrl + "/user/query/form";
    MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
    map.add("username","小明");
    map.add("age",12+"");
    printJSONResult(
            perform(TestUtil.getJSON(url).params(map))
                    .andExpect(MockResponseStatus.STATUS_OK)
                    .andExpect(matchJson("$.id").value(1))
                    .andReturn());
  }

}
