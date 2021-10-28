package com.kyo.springbootstart.process.base.controller;

import com.kyo.springbootstart.config.exception.ApiException;
import com.kyo.springbootstart.config.exception.ExceptionEnum;
import com.kyo.springbootstart.process.base.dto.HateoasSample;
import com.kyo.springbootstart.process.base.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * hateoas (Hypermedia As The Engine Of Application State)
 *      - 하이퍼미디어를 REST API의 상태 정보를 관리하기 위한 매커니즘으로 활용하는 것
 *
 *      - 서버가 클리이언트에게 자원을 보내면서 다음 작업을 할 수 있는 URL을 링크로 같이
 *         클라이언트는 링크를 확인하고 다음 작업을 할 수 있는 URL을 확인합니다.
 *         이런 방식으로 REST API의 URL 변경시 단점을 해결
 *
 *      - 참고 : https://www.baeldung.com/spring-hateoas-tutorial
 *
 */
@RestController
@RequiredArgsConstructor
public class HateoasController {

    private final S3Uploader s3Uploader;

    // 단건
    @GetMapping(value = "/hateoas-test", produces = { "application/hal+json" })
    public EntityModel<HateoasSample> hateoasTest() {

        HateoasSample sample = HateoasSample.builder()
                .name("kyo")
                .prefix("kim")
                .build();

        if (sample == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION_01);
        }

        EntityModel<HateoasSample> model = EntityModel.of(sample);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).hateoasTest());
        model.add(linkTo.withSelfRel());
        return model;

    }

    // 복수
    @GetMapping("/hateoas-test2")
    public CollectionModel<HateoasSample> hateoasTest2() {

        Collection<HateoasSample> collections = new ArrayList<>();


        for(int i=0; i<2; i++) {

            Link selfLink = linkTo(methodOn(this.getClass()).hateoasTest2())
                    .slash("kyo" + i)
                    .withSelfRel();

            HateoasSample sample = HateoasSample.builder()
                    .name("kyo" + i)
                    .prefix("kim")
                    .build().add(selfLink);
            collections.add(sample);

        }

        CollectionModel<HateoasSample> model = CollectionModel.of(collections);
        model.add(linkTo(methodOn(this.getClass()).hateoasTest2()).withSelfRel());

        return model;

    }


    @PostMapping(value = "/hateoas-test3", produces = { "application/hal+json" })
    public EntityModel<HateoasSample> upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {

        String name = s3Uploader.upload(multipartFile, "static");

        HateoasSample sample = HateoasSample.builder()
                .name(name)
                .prefix("s3-test")
                .build();

        if (sample == null) {
            throw new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION_01);
        }

        EntityModel<HateoasSample> model = EntityModel.of(sample);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).upload(multipartFile));
        model.add(linkTo.withSelfRel());
        return model;
    }





}
