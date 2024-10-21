package com.glsid.practicalcqrsandeventsourcingwithaxonframework.query.repository;

import com.glsid.practicalcqrsandeventsourcingwithaxonframework.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
