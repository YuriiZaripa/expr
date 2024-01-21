package ua.okwine.productexpirationdate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.okwine.productexpirationdate.service.ParserService;

@RestController
@RequestMapping("/startParsing")
@RequiredArgsConstructor
public class ParsingController {

    private final ParserService parserService;

    @GetMapping
    public void okwineAPIParsing() {
        parserService.parsingOkwineData();
    }
}
