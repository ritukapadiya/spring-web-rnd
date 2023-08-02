package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.CountryBean;
import com.bean.UserBean;
import com.bean.UserCountryBean;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate stmt;

	public void addUser(UserBean user) {
		stmt.update("insert into users (firstName,email,password,role,profilePath,countryCode) values (?,?,?,?,?,?)", user.getFirstName(),
				user.getEmail(), user.getPassword(), user.getRole(),user.getProfilePath(),user.getCountry());
	}
	public UserBean getUserByEmail(String email) {
		// to read single user data we have queryForObject
		try {
		return stmt.queryForObject("select * from users where email = ? ", new BeanPropertyRowMapper<UserBean>(UserBean.class),
				new Object[] { email });
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<UserCountryBean> getAllUsers() {
		return stmt.query("select * from users left join countries c on c.code = users.countryCode", new BeanPropertyRowMapper<UserCountryBean>(UserCountryBean.class));
	}
	public List<UserCountryBean> getAllUsers(int key) {
//		SELECT * FROM tbl LIMIT 5,10;  # Retrieve rows 6-15
		return stmt.query("select * from users left join countries c on c.code = users.countryCode limit ?,?", new BeanPropertyRowMapper<UserCountryBean>(UserCountryBean.class),(key-1)*10,10);
	}
	
	public List<UserCountryBean> getUserBySearch(String searchValue) {
		return stmt.query("select * from users left join countries c on c.code = users.countryCode where lower(firstName) like '"+searchValue+"%'", new BeanPropertyRowMapper<UserCountryBean>(UserCountryBean.class));
	}
	
	public void deleteUser(String userId) {
		stmt.update("delete from users where email= ?", userId);
	}
	
	public void updateUser(UserBean userBean,String userId) {
		stmt.update("update users set firstName = ?, role=?, profilePath = ? where email = ?",userBean.getFirstName(),userBean.getRole(),userBean.getProfilePath(),userId);
	}
	
	
	public List<CountryBean> getAllCountries() {
		return stmt.query("select * from countries", new BeanPropertyRowMapper<CountryBean>(CountryBean.class));
	}
	
	public int totalUser() {
		return stmt.queryForObject("select count(*) from users", Integer.class);
	}
	
}
