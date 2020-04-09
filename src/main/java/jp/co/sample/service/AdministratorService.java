package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * administratorsテーブルを操作する業務処理を行うサービスクラス.
 * 
 * @author masashi.nose
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者を登録します.
	 * 
	 * @param administrator
	 * @return IDが自動採番された管理者情報
	 */
	public Administrator register(Administrator administrator) {
		return administratorRepository.insert(administrator);
	}

	/**
	 * メールアドレスとパスワードから管理者を検索します.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報（ない場合、nullを返します）
	 */
	public Administrator findByEmailAndPassword(String mailAddress, String password) {
		return administratorRepository.findByEmailAndPassword(mailAddress, password);
	}
}
