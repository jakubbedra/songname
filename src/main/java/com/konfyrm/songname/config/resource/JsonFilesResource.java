package com.konfyrm.songname.config.resource;

import com.konfyrm.songname.config.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/config")
@Controller
public class JsonFilesResource {

    private final DataManager dataManager;

    @Autowired
    public JsonFilesResource(
            DataManager dataManager
    ) {
        this.dataManager = dataManager;
    }

    @PostMapping("/export")
    public ResponseEntity<Void> exportFiles() {
        dataManager.exportDataToFile();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importFiles() {
        dataManager.importDataFromFile();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hardcode")
    public ResponseEntity<Void> hardcodeData() {
        dataManager.initData();
        return ResponseEntity.ok().build();
    }

}
