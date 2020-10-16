package cn.xwplay.springtest.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncService {

  @Async
  public void asyncProcess01() throws InterruptedException {
     log.info("AsyncService: Start to process 01 -> {}",
             Thread.currentThread().getName());
     Thread.sleep(2000);
    log.info("AsyncService: Done to process 01 -> {}",
            Thread.currentThread().getName());
  }

  @Async
  public Future<String> asyncProcess02() throws InterruptedException {
    log.info("AsyncService: Start to process 02 -> {}",
            Thread.currentThread().getName());
    Thread.sleep(2000);
    log.info("AsyncService: Done to process 02 -> {}",
            Thread.currentThread().getName());
    return new AsyncResult<>("返回结果02");
  }

  @Async
  public void asyncProcess03() throws InterruptedException {
    log.info("AsyncService: Start to process 03 -> {}",
            Thread.currentThread().getName());
    Thread.sleep(2000);
    throw new RuntimeException("throw exception in asyncprocess 03");
  }
}
