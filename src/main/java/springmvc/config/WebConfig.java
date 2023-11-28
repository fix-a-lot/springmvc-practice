package springmvc.config;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // Spring MVC 활성화
@ComponentScan("springmvc.mvc") // component-scan 범위 지정
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * viewResolver 설정
     *
     * @return
     * @author fixalot
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // DispatcherServlet이 고정적인 리소스들에 대한 요청을 자신이 직접 처리하지 않고 서블릿 컨테이너의 디폴트 서블릿 전달을 요청한다.
        configurer.enable();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSource dataSource = new DataSource() {

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {
                // TODO Auto-generated method stub

            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {
                // TODO Auto-generated method stub

            }

            @Override
            public int getLoginTimeout() throws SQLException {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Connection getConnection() throws SQLException {
                // TODO Auto-generated method stub
                return null;
            }
        };
        return new DataSourceTransactionManager(dataSource);
    }
}
