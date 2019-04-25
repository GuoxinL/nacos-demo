package pub.guoxin.demo.gateway.dr.dis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.guoxin.demo.gateway.dr.dis.model.tag.TagComponentRelationship;

/**
 * Create by guoxin on 2019-04-24
 */
@RequestMapping("/gateway/component")
@RestController
public class TagComponentRelationshipController {

    @PostMapping
    public ResponseEntity add(@RequestBody TagComponentRelationship tagPathRelationships) {
        return null;
    }
}
