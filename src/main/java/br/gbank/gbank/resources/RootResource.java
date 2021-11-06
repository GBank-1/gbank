package br.gbank.gbank.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class RootResource {
    /**
     *
     */
    private static final String SWAGGER_UI_INDEX_HTML = "/swagger-ui/index.html";

    @GetMapping
    public ResponseEntity<Object> getRoot() {
        URI apidocs;
        try {
            apidocs = new URI(SWAGGER_UI_INDEX_HTML);
        } catch (URISyntaxException e) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(apidocs).build();
    }
}
