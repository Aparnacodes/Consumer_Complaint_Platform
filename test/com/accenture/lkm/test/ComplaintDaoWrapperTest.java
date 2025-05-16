package com.accenture.lkm.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.business.bean.ComplaintBean;
import com.accenture.lkm.dao.ComplaintDaoWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/cst-root-ctx.xml" })
@Transactional
public class ComplaintDaoWrapperTest {

	@Autowired
	private ComplaintDaoWrapper complaintDaoWrapper;

	@Test
	public void registerComplaintDetails() throws Exception {
		// Your implementation goes here
		ComplaintBean cBean = new ComplaintBean();
		cBean.setComplaintId(10004);
		cBean.setCustomerName("Aparna");
		cBean.setAmount(230.0);
		cBean.setComplaintTypeId(3);
		Date date = new Date(2014-10-10);
		cBean.setDateOfIncidence(date);
		int id = complaintDaoWrapper.registerComplaintDetails(cBean);
		assertNotNull(id);
		assertTrue(id > 10003);
	}

	
		
}
