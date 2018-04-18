package spider.configure;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * 配置自定义数据库连接池和自定义事务模板
 */
@Configuration
@MapperScan(basePackages={"spider.dao"},
            sqlSessionFactoryRef="sessionFactory")  
@EnableTransactionManagement
public class DatasourceConfig {
	
	@Value("${mybatis.config-location}")
	private String mybatisConfig;

	@Value("${mybatis.mapper-locations}")
	private String mybatisMapper;
	
	@Bean(name = "dataSource",destroyMethod = "close")
	@Primary
	@ConfigurationProperties(prefix = "c3p0")
	public DataSource dataSource(){
	    return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}
	
	@Bean(name = "sessionFactory")
	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	    /**
	     * 注意！利用系统自带功能无法正常加载这两个部分配置文件,需要再次手动加载
	     */
	    PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
	    /** 设置mybatis configuration 扫描路径 */                
	    sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis_config.xml"));
	    /** 添加mapper 扫描路径 */
	    sqlSessionFactory.setMapperLocations(pathMatchingResourcePatternResolver.getResources(mybatisMapper));
	    sqlSessionFactory.setDataSource(dataSource);
	    return sqlSessionFactory.getObject();
	}
	
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
	    return new DataSourceTransactionManager(dataSource);
    }
	
	@Bean(name = "transactionTemplate")
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) throws Exception {
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		defaultTransactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		defaultTransactionDefinition.setTimeout(60); // 秒钟
	    return new TransactionTemplate(transactionManager, defaultTransactionDefinition);
	}
}
