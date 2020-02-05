package jp.co.sample.domain;

import java.util.Date;

/**
 * @author mntky 従業員情報を表すドメイン
 */
public class Employee {
	/**ID */
	private Integer id;
	/**名前 */
	private String name;
	/**画像 */
	private String image;
	/**性別 */
	private String gender;
	/**入社日 */
	private java.util.Date hireDate;
	/**メールアドレス*/
	private String mailAdrress;
	/**郵便番号*/
	private String zipCode;
	/**住所 */
	private String address;
	/**電話 */
	private String telephone;
	/**給与 */
	private Integer salary;
	/**特性 */
	private String characteristics;
	/**扶養人数 */
	private Integer dependentsCount;

	public Employee(Integer id, String name, String image, String gender, Date hireDate, String mailAdrress,
			String zipCode, String address, String telephone, Integer salary, String characteristics,
			Integer dependentsCount) {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public java.util.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.util.Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getMailAdrress() {
		return mailAdrress;
	}

	public void setMailAdrress(String mailAdrress) {
		this.mailAdrress = mailAdrress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate="
				+ hireDate + ", mailAdrress=" + mailAdrress + ", zipCode=" + zipCode + ", address=" + address
				+ ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
				+ ", dependentsCount=" + dependentsCount + "]";
	}

}
