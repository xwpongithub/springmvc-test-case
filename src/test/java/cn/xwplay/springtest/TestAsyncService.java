package cn.xwplay.springtest;

import cn.xwplay.springtest.async.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TestAsyncService {

  private final AsyncService asyncService;

  @Test
  public void testAsyncProcess() throws InterruptedException, ExecutionException {
    asyncService.asyncProcess01();
    Future<String> future = asyncService.asyncProcess02();
    log.info("Async process 02 return: {}",future.get());
  }

}
