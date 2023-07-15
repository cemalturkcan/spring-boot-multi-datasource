package com.gsc.demo.foo.domain.foo;

import com.gsc.demo.foo.domain.foo.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {

}
