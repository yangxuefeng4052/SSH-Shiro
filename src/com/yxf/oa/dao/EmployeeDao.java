package com.yxf.oa.dao;

import java.util.List;

import com.yxf.oa.bean.Dept;
import com.yxf.oa.bean.Emp;
import com.yxf.oa.bean.Notice;

/**
*
* @author yxf
* @time 2018年8月29日下午5:45:33
*
*/
public interface EmployeeDao {
	
	/**
	 * 查询当前页所有员工
	 * @return
	 */
	List<Emp> findAllEmps(int page);
	/**
	 * 查询所有员工
	 * @return
	 */
	List<Emp> findAllEmps();
	
	/**
	 * 根据部门查询当前页所有员工信息
	 * @return
	 */
	List<Emp> findAllEmps(int deptId, int page);
	/**
	 * 查询所有员工总个数
	 * @return
	 */
	int findCount(int deptId);
	
	/**
	 * 添加员工
	 * @param emp
	 * @return
	 */
	boolean addEmp(Emp emp, int rid, int deptId);
	
	/**
	 * 修改员工
	 * @param emp
	 * @param rid
	 * @param deptId
	 */
	void updateEmp(Emp emp, int rid, int deptId);
	
	/**
	 * 根据id查dept
	 * @param dept
	 * @return
	 */
	Dept findDeptById(int id);
	
	/**
	 * 根据员工id查员工
	 * @param emp
	 * @return
	 */
	Emp findEmpById(int id);
	
	/**
	 * 修改员工信息
	 * @param emp
	 * @return
	 */
	Emp updateEmpInfo(Emp emp);
	
	/**
	 * 根据工号查员工
	 * @param emp
	 * @return
	 */
	Emp findEmpByEmpid(String empId);
	/**
	 * 删除员工
	 * @param emp
	 */
	void deleteEmp(Emp emp);
	/**
	 * 发布消息
	 * @param notice
	 * @return
	 */
	boolean addNotice(Notice notice);
	/**
	 * 查找部门发布信息
	 * @param deptId
	 * @return
	 */
	Notice findNoticeByDeptId(int deptId);
	
}
