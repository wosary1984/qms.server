package qms.service.wafer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qms.exception.def.BadRequestException;
import qms.repository.wafer.Wafer;
import qms.repository.wafer.WaferRepository;
import qms.service.context.ContextCheckService;

@Service
public class WaferDataService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WaferRepository waferRepository;

	@Autowired
	ContextCheckService context;

	// @PreAuthorize("hasPermission(#id, 'Person', 'read')")
	public Iterable<Wafer> getWaferData(String waferid) {
		List<Wafer> data = null;
		try {

			data = waferRepository.findByWaferid(waferid);

			logger.info("user:{}, query wafer:{} data", context.getLoginUser().getUsername(), waferid);
		} catch (Exception exception) {
			throw new BadRequestException("Internal Server Error");
		}
		return data;
	}
}
