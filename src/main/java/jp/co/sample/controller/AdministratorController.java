package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報をコントロールするAdministratorControllerクラス.
 * 
 * @author masashi.nose
 * 
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * 入力画面へ遷移します.
	 * 
	 * @return 入力画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";

	}

	/**
	 * 
	 * @param form リクエストパラメータ
	 * @return ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);

		return "redirect:/";

	}

	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * ログイン画面へ遷移します.
	 * 
	 * @param form  リクエストパラメータ
	 * @param model リクエストスコープ作成
	 * @return 従業員一覧
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

		if (administrator == null) {
			model.addAttribute("errorComment", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";

		}

		session.setAttribute("administratorName", administrator.getName());

		return "forward:/employee/showList";
	}
	
	/**
	 * ログイン画面にリダイレクトします.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
}
