package qms.repository.wafer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleWaferData {

    @Autowired
    private WaferDataRepository waferDataRepository;

    @Autowired
    private WaferRepository waferRepository;

    // private long radiusSize = 20;
    // private long scale = 5;

    public long pythagorean(long r, long h, long scale) {
        double length = Math.sqrt(Math.pow(r, 2) - Math.pow(h, 2)) / scale;
        return Math.round(length);
    }

    public void generateSampleData(String waferid) {

        List<WaferData> sample = this.generateData(waferid, 20, 5);
        waferDataRepository.saveAll(sample);
    }

    private List<WaferData> generateData(String waferid, long radius, long scale) {

        List<WaferData> data = new ArrayList<>();

        for (long y = -radius; y < radius; y++) {

            long delta = radius - pythagorean(radius * scale, y * scale, scale);

            for (int x = 0; x < radius * 2 - delta * 2; x++) {

                WaferData p = new WaferData(waferid, (long) x - radius, y, delta, Math.random() > 0.9 ? true : false);
                data.add(p);
            }
        }
        return data;
    }

    private List<WaferData> generateData(Wafer wafer, long radius, long scale) {

        List<WaferData> data = new ArrayList<>();

        for (long y = -radius; y < radius; y++) {

            long delta = radius - pythagorean(radius * scale, y * scale, scale);

            for (int x = 0; x < radius * 2 - delta * 2; x++) {

                WaferData p = new WaferData(wafer, (long) x - radius, y, delta, Math.random() > 0.9 ? true : false);
                data.add(p);
            }
        }
        return data;
    }

    public void generateSampleData2(String waferid,long radius, long scale) {
        Wafer wafer = new Wafer(waferid,radius);
        List<WaferData> data = this.generateData(wafer, radius, scale);
        wafer.setData(new HashSet<>(data));
        waferRepository.save(wafer);
    }
}