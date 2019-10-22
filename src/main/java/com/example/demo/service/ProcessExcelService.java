package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProcessExcelService {

    @Inject
    UserRepository userRepository;

    public String writeToTempDir(MultipartFile file) throws Exception {
        String tmpdir = "C:/data/";
        InputStream in = file.getInputStream();
        File currDir = new File(tmpdir);
        String path = currDir.getAbsolutePath();
        String fileLocation = tmpdir + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        return fileLocation;
    }

    public void processExcelFile(String fileLocation) throws Exception {
        FileInputStream excelFile = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        List<User> users = StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .map(r -> {
                    User u = new User();
                    u.setName(r.getCell(0).getStringCellValue());
                    u.setDatatype(r.getCell(1).getStringCellValue());
                    String required = r.getCell(2).getStringCellValue();
                    String output = r.getCell(3).getStringCellValue();
                    u.setRequired(required.equalsIgnoreCase("Y"));
                    u.setOutput(output.equalsIgnoreCase("Y"));
                    return u;
                }).collect(Collectors.toList());
        userRepository.saveAll(users);
    }
}
