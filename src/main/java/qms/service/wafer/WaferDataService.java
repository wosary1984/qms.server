package qms.service.wafer;

import java.util.List;
import java.util.stream.Collectors;

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

	public Iterable<WaferCode> getWaferList() {
		List<WaferCode> result = null;
		try {
			List<Wafer> data = waferRepository.findAll();
			result = data.stream().map(p -> {
				return new WaferCode(p);
			}).collect(Collectors.toList());
			logger.info("user:{}, query wafer list", context.getLoginUser().getUsername());
		} catch (Exception exception) {
			throw new BadRequestException("Internal Server Error");
		}
		return result;
	}

	public class WaferCode {

		public String text;
		public long id;

		public WaferCode(Wafer wafer) {
			this.text = wafer.getWaferid();
			this.id = wafer.getId();
		}
	}
}
