package com.accenture.lkm.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.entity.ComplaintTypeEntity;

/**
To-Do Item 1.2: Define a custom repository for managing ComplaintTypes.
	TODO:
	--Use custom repository.
	--Add a method to get all ComplaintTypes details from the database.
*/
@RepositoryDefinition(idClass=Integer.class, domainClass=ComplaintTypeEntity.class)
@Transactional(value = "txManager")
public interface ComplaintTypeDao {
	@Query(name="getComplaintDetailsBetweenDates")
	List<ComplaintTypeEntity> getComplaintTypes(Date fromDate, Date toDate);
	List<ComplaintTypeEntity> findAll();
}
