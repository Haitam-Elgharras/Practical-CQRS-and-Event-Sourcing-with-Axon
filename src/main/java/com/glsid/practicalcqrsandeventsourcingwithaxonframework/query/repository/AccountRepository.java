package com.glsid.practicalcqrsandeventsourcingwithaxonframework.query.repository;


import com.glsid.practicalcqrsandeventsourcingwithaxonframework.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
