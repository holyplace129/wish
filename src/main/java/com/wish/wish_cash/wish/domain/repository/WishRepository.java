package com.wish.wish_cash.wish.domain.repository;

import com.wish.wish_cash.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Integer> {


}
