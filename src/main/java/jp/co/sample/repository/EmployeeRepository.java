package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author masashi.nose
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * employeesテーブル１行分の情報を保持するローマッパー
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipcode(rs.getString("zipcode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	/**
	 * 従業員情報を全件検索します.
	 * 
	 * @return 従業員情報オブジェクトが詰まったリスト.
	 */
	public List<Employee> employeeList() {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT id, name, image, gender, hire_date, mail_address, zipcode, address, telephone, salary, characteristics, dependents_count ");
		sql.append("FROM employees ORDER BY hire_date");
		List<Employee> employeeList = template.query(sql.toString(), EMPLOYEE_ROW_MAPPER);
		return employeeList;

	}

	/**
	 * IDを用いて従業員情報を検索します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 */
	public Employee load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT id, name, image, gender, hire_date, mail_address, zipcode, address, telephone, salary, characteristics, dependents_count ");
		sql.append("WHERE id = :id");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql.toString(), param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}

	/**
	 * 指定されたIDの従業員が持つ扶養人数情報をアップデートします.
	 * 
	 * @param employee
	 */
	public void update(Employee employee) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id");
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("dependentsCount", employee.getDependentsCount()).addValue("id", employee.getId());
		template.update(sql.toString(), param);

	}

}
