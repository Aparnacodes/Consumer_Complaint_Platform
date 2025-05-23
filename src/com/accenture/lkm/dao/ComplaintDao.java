package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.entity.ComplaintEntity;

@RepositoryDefinition(idClass = Integer.class,domainClass = ComplaintEntity.class)
@Transactional(value = "txManager")
public interface ComplaintDao {
	public ComplaintEntity save(ComplaintEntity complaintEntity);
	@Query(name="getCustomerByComplaintType")
	public ComplaintEntity getCustomerByComplaintType(String customerName,int complaintTypeId);
}

