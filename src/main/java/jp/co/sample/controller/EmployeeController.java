package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報をコントロールするコントローラクラス.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		return new UpdateEmployeeForm();

	}

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員一覧へ遷移します.
	 * 
	 * @param model リクエストスコープ作成
	 * @return 従業員リストHTMLページ
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * 従業員詳細へ遷移します.
	 * 
	 * @param id    従業員ID
	 * @param model リクエストスコープ作成
	 * @return 従業員詳細HTMLページ
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";

	}
	
	/**
	 * 従業員一覧へ遷移します.
	 * 
	 * @param form　リクエストパラメータ
	 * @return	従業員一覧HTMLページ
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		System.out.println(form);
		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee);
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employee.setId(Integer.parseInt(form.getId()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
		
	}

}
