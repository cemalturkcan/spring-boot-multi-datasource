package com.gsc.demo.foo.domain.shoesource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeSourceRepository extends JpaRepository<ShoeSource, Long> {

}
