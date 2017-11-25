package iaraliev.rashid.bigbroserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Order(1)
public class FolderInitializer implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(FolderInitializer.class);

    @Value("${image.folder}")
    private String imageFolder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("createFolderIfNotExists for imageFolder {}", createFolderIfNotExists(imageFolder));
    }

    private boolean createFolderIfNotExists(String pathToFolder) {
        boolean flag = false;
        File directory = new File(pathToFolder);
        if(!directory.exists()) {
            flag = directory.mkdirs();
        }
        return flag;
    }
}
