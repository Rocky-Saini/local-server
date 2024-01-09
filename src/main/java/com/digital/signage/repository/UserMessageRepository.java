package com.digital.signage.repository;

import com.digital.signage.models.UserMessage;
import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessage, Long> {

    String UserMessageQueryOne =
            "select um from UserMessage as um where  um.userId=:userId  " +
                    "and DATE(um.sentOn)>=:sentOnPastDate";

    String UserMessageQueryTwo =
            "select um from UserMessage as um where  um.userId=:userId " +
                    "and um.customerId=:customerId " +
                    "and DATE(um.sentOn)=:sentOnPastDate";

    @Query(value = UserMessageQueryOne)
    Page<UserMessage> findByUserIdAndPastDate(@Param("userId") Long userId,
                                              @Param("sentOnPastDate") Date sentOnPastDate,
                                              Pageable pageable);

    @Query(value = UserMessageQueryTwo)
    Page<UserMessage> userMessageQuery(@Param("userId") Long userId,
                                       @Param("customerId") Long customerId,
                                       @Param("sentOnPastDate") Date sentOnPastDate, Pageable pageable);

    Page<UserMessage> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select count(*) from user_message as um where  um.user_id=:userId and "
            + "DATE(um.sent_on)>=:sentOnPastDate and state=:state", nativeQuery = true)
    Integer countAllByUserIdAndState(@Param("userId") Long userId,
                                     @Param("sentOnPastDate") Date sentOnPastDate, @Param("state") Boolean state);

    UserMessage findAllByMessageIdAndUserId(Long MessageId, Long userId);

    List<UserMessage> findAllByCustomerId(Long customerId);
}