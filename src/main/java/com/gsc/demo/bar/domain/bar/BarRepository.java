package com.gsc.demo.bar.domain.bar;

import com.gsc.demo.bar.domain.bar.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long> {
}
