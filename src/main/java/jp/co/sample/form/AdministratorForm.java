package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 登録画面からリクエストパラメータを受け取るフォーム.
 * 
 * @author masashi.nose
 *
 */
public class AdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください。")
	private String name;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "メールアドレスが不正です。")
	private String mailAddress;

	/** パスワード */
	@NotBlank(message = "パスワードを入力してください。")
	private String password;

	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください。")
	private String confirmationPassword;

	@Override
	public String toString() {
		return "AdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", confirmationPassword=" + confirmationPassword + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

}
