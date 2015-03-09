package mx.com.mindbits.argos.inventory.dao.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SchemaGenerator implements InitializingBean {

	private String schema;
	
	@Autowired
	private DataSource dataSource;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("CREATE SCHEMA " + schema + " AUTHORIZATION DBA");
	}

}
