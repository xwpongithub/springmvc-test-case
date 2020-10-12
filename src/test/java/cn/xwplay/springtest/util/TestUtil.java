package cn.xwplay.springtest.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestUtil {

  public static MockHttpServletRequestBuilder getJSON(String url) {
    return MockMvcRequestBuilders.get(url)
            .characterEncoding("UTF-8")
            .contentType(MediaType.APPLICATION_JSON);
  }

}
