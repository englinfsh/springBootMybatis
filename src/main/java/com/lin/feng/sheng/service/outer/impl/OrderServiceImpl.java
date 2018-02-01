package com.lin.feng.sheng.service.outer.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lin.feng.sheng.dao.mapper.TeacherMapper;
import com.lin.feng.sheng.entity.Teacher;
import com.lin.feng.sheng.service.outer.IOrderService;


@Service(value="orderServiceImpl")
public class OrderServiceImpl implements IOrderService{
	public Logger logger= Logger.getLogger(this.getClass());

	@Autowired
	TeacherMapper teacherMapper;

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void order() throws Exception {
		logger.info("============order()====");

		for (int i = 0; i < 5; i++) {

			Teacher t = new Teacher();
			String aa ="bnbb-"+System.currentTimeMillis();
			t.setId(aa.hashCode());
			t.setName("a-"+System.currentTimeMillis());
			t.setAge(System.currentTimeMillis());
			if(i>3){
				throw new Exception("tx error!");
			}
			teacherMapper.saveTeacher(t);

		}



		Thread.sleep(2000);
	}

	public void noTxOrder() throws Exception {
		Teacher t = new Teacher();
		String aa ="bnbb-"+System.currentTimeMillis();
		t.setId(aa.hashCode());
		t.setName("a-"+System.currentTimeMillis());
		t.setAge(System.currentTimeMillis());
		teacherMapper.saveTeacher(t);

	}

	@Override
	public void updateOrder() throws Exception {

		int id=9999;
		String status="10";
		String oldStatus="1";
		int n = teacherMapper.updateTh(id, status, oldStatus);
		logger.info("updateOrder---------------------------------,n="+n);

	}

}
