package cn.xwplay.springtest.domain.vo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

  @JsonView(SimpleView.class)
  private Long id;
  @JsonView(SimpleView.class)
  private String username;
  @JsonView(SimpleView.class)
  private Integer age;
  @JsonView(ComplexView.class)
  private String gender;
  private Integer points;

  public interface SimpleView {}
  public interface ComplexView extends SimpleView {}


}
