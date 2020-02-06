package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * employeesテーブルを操作する業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を取得する.
	 * 
	 * @return 従業員一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	/**
	 * @param id 従業員ID
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);

		return employee;
	}

}
