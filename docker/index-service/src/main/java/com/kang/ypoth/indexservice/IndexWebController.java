package com.kang.ypoth.indexservice;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//import org.springframework.hateoas.Link;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/index")

public class IndexWebController {
	
	
	
		
	//	private  IndexRepository indexRepository;
		private static final Logger LOGGER = LoggerFactory.getLogger(IndexWebController.class);
		
	//	@Autowired
		private IndexService indexService;
		
		@Autowired
		public IndexWebController(IndexService indexService) {
			this.indexService=indexService;
		}
		
		
		@PostMapping
		public ResponseEntity<Index> createRegister(@RequestBody Index index) {
	       
			//LOGGER.info("POST /index/{}", index.getId());

			return ResponseEntity.ok(indexService.createRegister(index));
		}


		/**@Autowired
		public IndexWebController(IndexRepository indexRepository) {
			super();
			this.indexRepository = indexRepository;
		}

		
		
		/**@GetMapping(produces = { "application/json" })// alakse repository
		public Page<Index> getAllRegisters(Pageable pageable){
			 LOGGER.info("GET /index/");
			 List<Index> index = indexRepository.findAllRegisters();
			// List<Index> indexList = index.getContent().stream().collect(Collectors.toList());
			 
		     return new PageImpl<Index>( index, pageable,  );

			 
		}**/
		
		
		@GetMapping(produces = { "application/json" })
		public ResponseEntity<List<Index>> getAllRegisters(){
			LOGGER.info("Start");
			
			List<Index> allIndex =  indexService.getAllRegisters();
			
			if(allIndex == null) {
	    		LOGGER.debug("No individual retrieved from repository");
				return new ResponseEntity<List<Index>>(HttpStatus.NOT_FOUND);

			}
			
			for(Index index : allIndex) {
				index.add(linkTo(IndexWebController.class).slash(index.getIdentityNum()).withSelfRel());
				//LOGGER.debug(allIndex.toString());
			}
			
			
    		
        	LOGGER.info("Ending");

        	return ResponseEntity.ok(allIndex);		
        	}
		
		
		
		@GetMapping(value = "/{socialSecNum}",produces = { "application/json" })
		public ResponseEntity<Index> findBySocialSecNum(@PathVariable("socialSecNum") String socialSecNum) {
			
			//LOGGER.info("Start");
	    //	LOGGER.debug("Fetching individual with socialSecNum: {}", socialSecNum);
			
			Index index = indexService.findBySocialSecNum(socialSecNum);
			if(index == null) {
	    	//	LOGGER.debug("Individual with socialSecNum: {} not found", socialSecNum);
				return new ResponseEntity<Index>(HttpStatus.NOT_FOUND);
				
			}
			
			index.add(linkTo(methodOn(IndexWebController.class).findBySocialSecNum(index.getSocialSecNum())).withSelfRel());
			
		
	    	//LOGGER.info("Ending");

        	return ResponseEntity.ok(index);		
        	}
		
}



	
		
	
		
		

	



