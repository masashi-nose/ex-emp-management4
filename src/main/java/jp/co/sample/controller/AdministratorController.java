package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.AdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 
 * administratorsテーブルを操作するコントローラ.
 * 
 * @author masashi.nose
 *
 */
@Controller
@RequestMapping("")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@ModelAttribute
	public AdministratorForm setUpForm() {
		return new AdministratorForm();

	}

	/**
	 * 登録画面へ遷移します.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "administrator/register";
	}

	/**
	 * 管理者を登録します.
	 * 
	 * 登録失敗の場合、エラー表示を返します. 登録完了後、ログイン画面へ遷移します.
	 * 
	 * @param form   リクエストパラメータ
	 * @param result
	 * @param model  リクエストスコープ作成
	 * @return （失敗）登録画面 / （成功）ログイン画面
	 */
	@RequestMapping("/register")
	public String register(@Validated AdministratorForm form, BindingResult result) {
//		Administrator administrator = administratorService.findByEmailAndPassword(form.getMailAddress(), form.getPassword());
//		
//		if(administrator != null) {
//			result.rejectValue("mailAddress", "", "入力されたメールアドレスは既に登録されています。");
//			result.rejectValue("password", "", "入力されたパスワードは使用できません。");
//		}

//		if(result.hasErrors()) {
//			return "administrator/register";
//		
//		}

//		フォームで受け取ったリクエストパラメータをドメインにセットしDBへインサート
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());

		System.out.println(administrator);

		administratorService.register(administrator);

		return "redirect:/";
	}

	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * 
	 * ログインします.
	 * 失敗の場合、エラーを表示させます.
	 * 
	 * @param form リクエストパラメータ
	 * @param result
	 * @return　従業員一覧画面 
	 */
	public String login(@Validated LoginForm form, BindingResult result) {
		Administrator administrator = administratorService.findByEmailAndPassword(form.getMailAddress(),
				form.getPassword());

		if (administrator == null) {
			result.rejectValue("mailAddress", "", "メールアドレスが不正です。");
			result.rejectValue("password", "", "パスワードが不正です。");
		}

		if (result.hasErrors()) {
			return "login";
		}

//		管理者名保持のため、セッションスコープに格納
		session.setAttribute("administratorName", administrator.getName());

		return "employee/showList";

	}

}
