package org.scoula.travel.dataimport;


import org.scoula.database.JDBCUtil;
import org.scoula.travel.dao.TravelDao;
import org.scoula.travel.dao.TravelDaoImpl;
import org.scoula.travel.domain.TravelImageVO;

import java.io.File;

public class ImportImageData {
    public static void main(String[] args) {
        TravelDao dao = new TravelDaoImpl();
        File dir = new File("./travel-image");
        File[] files = dir.listFiles();
        for (File file : files) {
            String filename = file.getName();
            long travelNo = Long.parseLong(filename.split("-")[0]); // 관광지 no 얻기
            TravelImageVO image = TravelImageVO.builder()

                    .filename(filename)
                    .travelNo(travelNo)
                    .build();

            System.out.println(image);
            dao.insertImage(image);
        }
        JDBCUtil.close();
    }
}