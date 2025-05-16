package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.business.bean.ComplaintBean;
import com.accenture.lkm.business.bean.ComplaintTypeBean;
import com.accenture.lkm.entity.ComplaintEntity;
import com.accenture.lkm.entity.ComplaintTypeEntity;

@Repository
@Transactional(value = "txManager")
public class ComplaintDaoWrapper {

	@Autowired
	private ComplaintDao complaintDao;

	@Autowired
	private ComplaintTypeDao complaintTypeDao;

	@PersistenceContext
	private EntityManager manager;

	public int registerComplaintDetails(ComplaintBean complaintBean) {
		ComplaintEntity complaintEntity = new ComplaintEntity();
		BeanUtils.copyProperties(complaintBean, complaintEntity);
		complaintDao.save(complaintEntity);
		
		
		return complaintEntity.getComplaintId();
    }

	public List<ComplaintBean> getComplaintDetailsByDate(Date fromDate, Date toDate) {
		
		String getComplaintDetailsByDate = "select c from ComplaintEntity c where c.dateOfIncidence>= ?1 and c.dateOfIncidence<=?2";
		
		Query query = manager.createQuery(getComplaintDetailsByDate);
		
		query.setParameter(1, fromDate);
		query.setParameter(2, toDate);
		
		List<ComplaintEntity> entityList = query.getResultList();
		List<ComplaintBean> list = new ArrayList<>();
		
		for(ComplaintEntity c: entityList) {
			ComplaintBean cBean = new ComplaintBean();
			BeanUtils.copyProperties(c, cBean);
			list.add(cBean);
		}
		return list;
	}
	
	public List<ComplaintTypeBean> getAllComplaintTypes() {
		List<ComplaintTypeEntity> entList = complaintTypeDao.findAll();
		List<ComplaintTypeBean> beanList = new ArrayList<>();
		
		for(ComplaintTypeEntity c: entList) {
			ComplaintTypeBean cBean = new ComplaintTypeBean();
			BeanUtils.copyProperties(c, cBean);
			beanList.add(cBean);
		}
		
        return beanList;
	}

	public int getCustomerByComplaintType(String customerName, int complaintTypeId) {
		ComplaintEntity complaintEntity = complaintDao.getCustomerByComplaintType(customerName, complaintTypeId);
		if(complaintEntity!=null)
			return 1;
		return 0;
	}
	
}
