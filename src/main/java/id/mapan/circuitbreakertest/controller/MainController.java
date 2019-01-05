package id.mapan.circuitbreakertest.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author edwin < edwinkun at gmail dot com >
 *
 */
@RestController
@RequestMapping("/")
public class MainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @HystrixCommand(fallbackMethod = "failing")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map index(@RequestParam String url) throws Exception {
        
        logger.info(">>>>> firing to {}", url);
        
        return new HashMap() {
            {
                put("code", 1);
                put("message", new RestTemplate().getForObject(url, String.class));
            }
        };
    }

    public Map failing(String url) {
        return new HashMap() {
            {
                put("code", 0);
                put("message", "fail");
            }
        };
    }
}