package com.gsc.demo.bar.domain.shoe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
}
