package com.medicare.controller;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;

	import com.medicare.entity.Drug;
	import com.medicare.service.DrugService;
	import com.medicare.utils.SuccessResponse;

	@RestController
	@RequestMapping("/drug")
	public class DrugController {

		@Autowired
		DrugService drugService;

		@PostMapping("/add")
		public ResponseEntity<SuccessResponse> saveDrug(@RequestBody Drug drug, @RequestParam int adminId)
		{
			return drugService.saveDrug(drug, adminId);
		}
		
		@PutMapping("/update")
		public ResponseEntity<SuccessResponse> updateDrug(@RequestBody Drug drug, @RequestParam int adminId)
		{
			return drugService.updateDrug(drug, adminId);
		}

		@DeleteMapping("/delete")
		public ResponseEntity<SuccessResponse> deleteDrug(@RequestParam int id, @RequestParam int adminId)
		{
			return drugService.deleteDrug(id, adminId);
		}

		@GetMapping("/find")
		public ResponseEntity<SuccessResponse> findDrugById(@RequestParam int id)
		{
			return drugService.findDrugById(id);
		}

		@GetMapping("/fetchAll")
		public ResponseEntity<SuccessResponse> fetchAllDrugs()
		{
			return drugService.fetchAllDrugs();
		}

		@GetMapping("/search")
		public ResponseEntity<SuccessResponse> searchDrugs(@RequestParam String keyword)
		{
			return drugService.searchDrugs(keyword);
		}
		
	}


