package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.accenture.lkm.business.bean.ComplaintBean;
import com.accenture.lkm.business.bean.ComplaintTypeBean;
import com.accenture.lkm.dao.ComplaintDaoWrapper;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintDaoWrapper complaintDaoWrapper;

	public int registerComplaintDetails(ComplaintBean complaintBean) throws Exception {
		int status = complaintDaoWrapper.getCustomerByComplaintType(complaintBean.getCustomerName(), complaintBean.getComplaintTypeId());
		if(status == 1) {
			throw new Exception("You have already submitted a complaint with the same type.");
		}else {
			return complaintDaoWrapper.registerComplaintDetails(complaintBean);
		}
	}

	@Override
	public List<ComplaintBean> getComplaintDetailsByDate(Date fromDate, Date toDate) {
		return complaintDaoWrapper.getComplaintDetailsByDate(fromDate, toDate);
	}

	@Override
	public Map<Integer, String> getAllComplaintTypes() {
		Map<Integer, String> complaintMap = new HashMap<>();
		List<ComplaintTypeBean> beanList = new ArrayList<>();
		beanList = complaintDaoWrapper.getAllComplaintTypes();
		for(ComplaintTypeBean b: beanList) {
			complaintMap.put(b.getComplaintTypeId(), b.getComplaintTypeName());
		}
		return complaintMap;
	}
}
