package com.itheima.jdbc;

import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AccountDaoImpl implements AccountDao {
	//声明JdbcTemplate属性及其setter方法
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//添加帐户

	public int addAccount(Account account) {
		// 定义SQL
		String sql = "insert into account(username, balance) value(?,?)";
		//定义数组来存储SQL语句中的参数
		Object[] obj = new Object[]{
				account.getUsername(),
				account.getBalance()
		};
		//执行添加操作，返回的是受SQL语句影响的记录条数
		int num = this.jdbcTemplate.update(sql, obj);
		return num;
	}

	//更新帐户
	public int updateAccount(Account account) {
		// 定义SQL
		String sql = "update account set username=?, balance=? where id = ?";
		//定义数组来存储SQL语句中的参数
		Object[] params = new Object[]{
				account.getUsername(),
				account.getBalance(),
				account.getId()
		};
		//执行更新操作，返回的是受SQL语句影响的记录条数
		int num = this.jdbcTemplate.update(sql, params);
		return num;
	}

	//删除帐户
	public int deleteAccount(int id) {
		// 定义SQL
		String sql = "delete from account where id = ?";
		//执行删除操作，返回的是受SQL语句影响的记录条数
		int num = this.jdbcTemplate.update(sql,id);
		return num;
	}
	
	//通过id查询
		public Account findAccountById(int id){
			
			//定义SQL语句
			String sql = "select * from account where id = ?";
			//创建一个新的BeanPropertyRowMapper对象
			RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
			//将id绑定到SQL语句中，并通过RowMapper返回一个Object类型的单行记录
			return this.jdbcTemplate.queryForObject(sql, rowMapper,id);
			
		}
		
		//查询所有帐户
		public List<Account> findAllAccount(){
			//定义SQL语句
			String sql = "select * from account";
			//创建一个新的BeanPropertyRowMapper对象
			RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
			//执行静态的SQL查询，并通过rowMapper返回结果
			return this.jdbcTemplate.query(sql, rowMapper);
			
		}

}
