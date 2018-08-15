package com.bookingservice.controller;

import com.bookingservice.model.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminController implements IBaseController<Admin, Admin, Long>{

    @Autowired
    AdminServiceImpl adminServiceImpl;

    @PostMapping
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
       return new ResponseEntity<>(adminServiceImpl.create(admin), HttpStatus.CREATED);
    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<Admin> findOne(@PathVariable Long id) {
        return adminServiceImpl.findOne(id).map(admin -> new ResponseEntity<>(admin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Admin>> findAll() {
        return new ResponseEntity<>(adminServiceImpl.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<Admin> update(@PathVariable Long id, @RequestBody Admin admin) {
        return adminServiceImpl.update(id, admin)
                .map(updatedAdmin -> new ResponseEntity<>(updatedAdmin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

/*    @GetMapping
    @Override
    public ResponseEntity<List<ClientRespDto>> foundPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        List<ClientRespDto> clientRespList = clientService.foundPage(page, size)
                .getContent();
        return new ResponseEntity<>(clientRespList, HttpStatus.OK);
    }*/

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<Admin> delete(@PathVariable Long id) {
        return adminServiceImpl.delete(id)
                .map(admin -> new ResponseEntity<>(admin, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}