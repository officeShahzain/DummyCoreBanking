package com.corebanking.system.controller;

import com.corebanking.system.service.Impl.ReportServiceImpl;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/download")
public class FileUpload {
    @Autowired
    ReportServiceImpl reportService;
    @GetMapping("/file/{format}")
    public ResponseEntity<?> downloadFile(@PathVariable("format")String format) throws JRException, FileNotFoundException {
        String response = reportService.exportReport(format);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
