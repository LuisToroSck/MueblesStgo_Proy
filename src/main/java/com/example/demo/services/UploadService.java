package com.example.demo.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.entities.DatarelojEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;

@Service
public class UploadService {
    private String folder="cargas//";
    private final Logger logg = LoggerFactory.getLogger(UploadService.class);

    public String save(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte [] bytes= file.getBytes();
                Path path = Paths.get( folder+file.getOriginalFilename() );
                Files.write(path, bytes);
                logg.info("Archivo guardado");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Archivo guardado correctamente";
    }


}