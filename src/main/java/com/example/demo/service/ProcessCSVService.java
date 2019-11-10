package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProcessCSVService {

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
        Reader reader = Files.newBufferedReader(Paths.get(fileLocation));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try {
            List<CSVRecord> records = csvParser.getRecords();
            // will iterate (total rows in excel - 2), skipping first record because it has header name
            // and will skip last row as well, because it contains "Total count"
            long limit = records.size() - 2;
            List<User> users =
                    records.stream()
                    .skip(1)
                    .limit(limit)
                    .map(r -> {
                        User u = new User();
                        u.setSystemDate(LocalDate.parse(r.get(0), formatter));
                        u.setFirmCD(r.get(1));
                        u.setFirstName(r.get(2));
                        u.setFromAddLine1(r.get(3));
                        u.setFromAddLine2(r.get(4));
                        u.setFromAddLine3(r.get(5));
                        u.setFromCity(r.get(6));
                        u.setFromSTCD(r.get(7));
                        u.setFromZip(r.get(8));
                        u.setFromCountry(r.get(9));
                        u.setFromPhone(r.get(10));
                        u.setFromPhoneExt(r.get(11));
                        u.setFromFax(r.get(12));

                        u.setFromLt(r.get(13));
                        u.setFulFlmCnfIntPrty1(r.get(14));
                        u.setFulFlmCnfIntPrty2(r.get(15));
                        u.setFulFlmCnfmAdd1(r.get(16));
                        u.setFulFlmCnfmAdd2(r.get(18));
                        u.setFulFlmCnfmAdd3(r.get(19));
                        u.setFulFlmCnfmCity(r.get(20));
                        u.setFulFlmCnfmStCd(r.get(21));


                        u.setFulFlmCnfmZip(r.get(22));
                        u.setFulFlmCnfmCtry(r.get(23));
                        u.setFulFlmCnfmElectDlvPrfrInd(r.get(24));
                        u.setFulFlmCnfmElecDlvMthdCd(r.get(25));
                        u.setFulFlmPrcIntPrtyName1(r.get(26));
                        u.setFulFlmPrcIntPrtyName2(r.get(27));
                        u.setFulFlmPrxAddr1(r.get(28));
                        u.setFulFlmPrxAddr2(r.get(29));
                        u.setFulFlmPrxAddr3(r.get(30));

                        u.setFulFlmPrxCityNm(r.get(31));
                        u.setFulFlmPrxStCd(r.get(32));
                        u.setFulFlmPrxZip(r.get(33));
                        u.setFulFlmPrxCtryNm(r.get(34));
                        u.setFulFlmPrxElecDlvPrfrInd(r.get(34));
                        u.setFulFlmPrxInvMgrElecAgtId(r.get(35));
                        u.setFulFlmAdvntEnabledMgrInd(r.get(36));
                        u.setFulFlmCntctSeqNbr(r.get(37));
                        u.setFulFlmCntctRole(r.get(38));
                        u.setFulFlmCntctEmailId(r.get(39));
                        u.setFulFlmCntctFirstNm(r.get(40));
                        u.setFulCntctMidNm(r.get(41));
                        u.setFrmCntctLastNm(r.get(42));
                        u.setFrmCntctPhnNbr(r.get(43));
                        u.setFrmCntctPhnExt(r.get(44));
                        u.setFrmCntctFaxNbr(r.get(45));
                        u.setFrmCntctDsclDlvInd(r.get(46));

                        return u;
                    }).collect(Collectors.toList());
            userRepository.saveAll(users);
        } catch(Exception e) {
            log.info("Exception reading csv and saving it to DB {}", e);
        } finally {
            reader.close();
            csvParser.close();
        }
    }
}
