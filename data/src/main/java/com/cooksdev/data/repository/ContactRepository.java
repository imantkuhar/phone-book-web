package com.cooksdev.data.repository;

import com.cooksdev.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findContactByUserId(Integer id);

    Optional<Contact> findContactById(Integer id);


}
