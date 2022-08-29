package com.example.uz_kassa.repository;

import com.example.uz_kassa.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByCode(Integer code);
}
