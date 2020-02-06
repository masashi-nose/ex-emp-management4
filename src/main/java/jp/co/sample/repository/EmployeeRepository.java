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

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAdrress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;

	};

	/**
	 * DBから従業員情報を全件検索します.
	 * 
	 * @return 従業員リスト
	 */
	public List<Employee> findAll() {
		String findAllSql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count from employees";

		List<Employee> employeeList = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);

		return employeeList;

	}

	/**
	 * DBから従業員情報を1件検索します.
	 * 
	 * @param id 従業員ID
	 * @return 従業員情報
	 */
	public Employee load(Integer id) {
		String loadSql = "select id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count from employees WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(loadSql, param, EMPLOYEE_ROW_MAPPER);

		return employee;
	}

	/**
	 * DBの従業員情報のうち、扶養人数を更新します.
	 * 
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		String updateSql = "UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("dependentsCount",
				employee.getDependentsCount()).addValue("id", employee.getId());

		template.update(updateSql, param);

	}
}