package HelloEureka.HelloEurekaClient;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HelloWebClient {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/")
	public String handleRequest(Model model){
		List<ServiceInstance> instances = discoveryClient.getInstances("SpringBootEurekaService");
		if (instances != null && instances.size()>0) {
			ServiceInstance serviceInstance = instances.get(0);
			String url = serviceInstance.getUri().toString();
			System.out.println(url);
			url = url + "/hello";
			RestTemplate restTemplate = new RestTemplate();
			HelloObject helloObject = restTemplate.getForObject(url, HelloObject.class);
			model.addAttribute("msg",helloObject.getMessage());
			model.addAttribute("time",LocalDateTime.now());
		}
		
		return "aayushi";
	}
}
