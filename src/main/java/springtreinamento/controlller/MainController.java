package springtreinamento.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path="/")
public class MainController {

  @RequestMapping(path="/")
  public @ResponseBody String hello() {
    return "WADDUP";
  }
}
