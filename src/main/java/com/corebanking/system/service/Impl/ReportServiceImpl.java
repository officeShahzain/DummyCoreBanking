package com.corebanking.system.service.Impl;

import com.corebanking.system.model.entity.Account;
import com.corebanking.system.repository.AccountRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.type.descriptor.jdbc.ObjectNullResolvingJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl {
    @Autowired
    private AccountRepository accountRepository;
    private final String path = "D:\\DummyCoreBankingSystem\\Jasper_Report";
    public String exportReport(String reportFormat) throws JRException, FileNotFoundException {
        List<Account> accounts = accountRepository.findAll();
        File file = ResourceUtils.getFile("classpath:account.jrxml");
        JasperDesign jasperDesign;
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(accounts);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\account.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\account.pdf");
        }
        return "report  generated in path "+path;

    }
}
