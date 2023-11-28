package springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "springmvc.mvc" }, // component-scan 범위 지정
		excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {

}
