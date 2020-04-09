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
	 * 
	 * 従業員を入社日順で全件表示します.
	 * 
	 * @return 従業員オブジェクトが詰まったリスト
	 */
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	/**
	 * 従業員の詳細情報を表示します.
	 * 
	 * @param id ID
	 * @return 従業員詳細情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);

	}

	/**
	 * 従業員の扶養人数情報をアップデートします.
	 * 
	 * @param employee 従業員情報
	 */
	public void updateDependentsCount(Employee employee) {
		employeeRepository.update(employee);
	}
}
