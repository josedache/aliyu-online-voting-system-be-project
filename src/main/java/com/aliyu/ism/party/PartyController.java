package com.aliyu.ism.party;

import com.aliyu.ism.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    @PostMapping
    public ResponseEntity<String> createParty(
            @Validated @RequestBody Party party,
            BindingResult validationResult
    ) {

        if (Utils.validationErrorCheck(validationResult)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        }

        partyRepository.save(party);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Party> removeParty(@PathVariable("id") Party party) {
        if (party != null) {
            partyRepository.delete(party);
            return ResponseEntity.ok(party);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public List<Party> getAllPartiesAndMembers() {
        return partyRepository.findAll();
    }

    @GetMapping("{id}")
    public Party getSinglePartyAndMembers(@PathVariable("id") Party party) {
        return party;
    }

    @GetMapping("generate-id")
    public String generatePartyId() {
        return PartyIdGenerator.getPartyId();
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new PartyValidator());
    }
}
