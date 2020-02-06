package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author masashi.nose administratorsテーブルを操作するリポジトリ
 *
 */
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

	/**
	 * 管理者テーブルに登録するメソッド
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String insertSql = "INSERT INTO administrators (name, mail_address, password) VALUES (:name, :mailAddress, :password)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		template.update(insertSql, param);

	}

	/**
	 * 入力されたメールアドレスとパスワードから管理者を検索するメソッド
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return administrator 管理者情報
	 */
	public Administrator findByMailAddressPassword(String mailAddress, String password) {
		String findByMailAddressPasswordSql = "select id, name, mail_address, password from administrators WHERE mail_address = :mailAddress AND password = :password";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);

		List<Administrator> administratorList = template.query(findByMailAddressPasswordSql, param,
				ADMINISTRATOR_ROW_MAPPER);

		if (administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);

	}
}
