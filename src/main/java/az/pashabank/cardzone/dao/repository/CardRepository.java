package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Modifying
    @Query(value = "update Card set balance=:balance where id=:id")
    void updateCardBalanceById(@Param("id") long id, @Param("balance") double balance);

//    @Query(value = "from Card where customerId=:customerId and balance >=:minBalance and balance <=:maxBalance")
//    Page<Card> findAllBySpecification(@Param("customerId") long customerId, @Param("minBalance") double minBalance, @Param("maxBalance") double maxBalance, Pageable pageable);

}
