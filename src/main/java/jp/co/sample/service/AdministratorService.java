package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報に関する業務を処理する AdministratorServiceクラス.
 * 
 * @author masashi.nose
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者テーブルに登録するメソッド.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}

	/**
	 * 入力されたメールアドレスとパスワードから管理者を検索するメソッド.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return administrator 管理者情報
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = administratorRepository.findByMailAddressPassword(mailAddress, password);
		return administrator;
	}

}
