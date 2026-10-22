package school.sptech;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class Conexao {

    private final BasicDataSource basicDataSource;
    private final JdbcTemplate jdbcTemplate;

    public Conexao() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/Soundfy");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("sptech");

        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}