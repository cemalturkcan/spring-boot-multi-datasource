package com.gsc.demo;

import com.gsc.demo.bar.domain.bar.Bar;
import com.gsc.demo.bar.domain.shoe.Shoe;
import com.gsc.demo.bar.domain.bar.BarRepository;
import com.gsc.demo.bar.domain.shoe.ShoeRepository;
import com.gsc.demo.foo.domain.foo.Foo;
import com.gsc.demo.foo.domain.foo.FooRepository;
import com.gsc.demo.foo.domain.shoesource.ShoeSource;
import com.gsc.demo.foo.domain.shoesource.ShoeSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foobar")
public class FooBarController {

    @Autowired
    private  FooRepository fooRepo;
    @Autowired
    private ShoeSourceRepository shoeSourceRepo;
    @Autowired
    private  BarRepository barRepo;
    @Autowired
    private ShoeRepository shoeRepo;

    @GetMapping
    public void fooBar() {
        Foo foo = new Foo();
        foo.setFoo("foo");
        fooRepo.save(foo);
        Bar bar = new Bar();
        bar.setBar("bar");
        barRepo.save(bar);
        Shoe shoe = new Shoe();
        shoe.setBar("shoe");
        shoeRepo.save(shoe);
        ShoeSource shoeSource = new ShoeSource();
        shoeSource.setFoo("shoeSource");
        shoeSourceRepo.save(shoeSource);
    }
}
