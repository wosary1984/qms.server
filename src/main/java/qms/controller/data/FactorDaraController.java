package qms.controller.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qms.controller.BaseController;
import qms.repository.data.KeyFactor;
import qms.repository.wafer.WaferData;
import qms.service.data.FactorDataService;

@RestController
public class FactorDaraController extends BaseController {

	final String PATH_FACTOR = "/api/factor";
	final String PATH_FACTOR_EVENT = "/api/event";

	@Autowired
	FactorDataService factorDataService;

	@RequestMapping(path = PATH_FACTOR, method = RequestMethod.GET)
	public Iterable<FactorDataService.FactorSummary> getFactorData() {

		return factorDataService.getFactorList();
	}

	@RequestMapping(path = PATH_FACTOR, method = RequestMethod.POST)
	public KeyFactor createFactor(@RequestBody String data) {
		return factorDataService.createFactor(data);
	}

	@RequestMapping(path = PATH_FACTOR_EVENT, method = RequestMethod.POST)
	public KeyFactor createEvent(@RequestBody String data) {
		return factorDataService.createEvent(data);
	}

	@RequestMapping(path = PATH_FACTOR + "/{key}", method = RequestMethod.GET)
	public KeyFactor getFactor(@PathVariable String key) {

		return factorDataService.getFactor(key);
	}

	protected class WaferModel {

		public List<WaferData> data = null;
		public long radius = 0;

		public WaferModel(Iterable<WaferData> data, long radius) {
			this.data = (List<WaferData>) data;
			this.radius = radius;
		}
	}

}
