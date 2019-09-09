package qms.repository.wafer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qms.repository.base.BaseRepository;

public interface WaferDataRepository extends BaseRepository<WaferData, Long> {
    public List<WaferData> findAll();

    public List<WaferData>findByWaferid(String waferid);

    @Query(value = "select max(c_cx) from t_wafer_data where c_wafer_id=:waferid",nativeQuery = true)
	public long radiusSize(@Param("waferid") String waferid);
}
