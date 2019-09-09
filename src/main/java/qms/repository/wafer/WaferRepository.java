package qms.repository.wafer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import qms.repository.base.BaseRepository;

public interface WaferRepository extends BaseRepository<Wafer, Long> {
    public List<Wafer> findAll();

    public List<Wafer> findByWaferid(String waferid);

    @Query(value = "select c_radius from t_wafer where c_wafer_id=:waferid", nativeQuery = true)
    public long getRadius(@Param("waferid") String waferid);
}
