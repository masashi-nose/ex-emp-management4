package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	public void insert(Administrator administrator) {
		String insertSql = "INSERT INTO administrators (id, name, mail_address, password) VALUES (id = :id, name = :name, mail_address = :mailAddress, password = :password)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		template.update(insertSql, param);

	}

	public Administrator findByMailAddressPassword(String mailAddress, String password) {
		String findByMailAddressSql = "select id, name, mail_address, password from administrators WHERE mail_address = :mail_address, password = :password";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress)
				.addValue("password", password);

		Administrator administrator = template.queryForObject(findByMailAddressSql, param, ADMINISTRATOR_ROW_MAPPER);

		return administrator;

	}
}
