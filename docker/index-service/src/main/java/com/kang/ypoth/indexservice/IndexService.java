package com.kang.ypoth.indexservice;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class IndexService {
	
	////@Autowired
	private IndexRepository indexRepository;
	
	
	//@Autowired
	//MessageSource messages;
	;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexService.class);
	
	@Autowired
	public IndexService(IndexRepository indexRepository) {
		this.indexRepository=indexRepository;
	}
	

	public Index  createRegister(Index index){
		
		logger.debug("Create register with ID: {}", index.getId());
		
		indexRepository.save(index);
		return index;
		
	

	}
	
			//logging.file.path=logs
			//logging.file.name=$${HOME}/myapp.log
	
	public List<Index> getAllRegisters(){
		logger.info("Start");
		
		List<Index> allIndex =  this.indexRepository.findAll();
		
		if(allIndex == null) {
		logger.debug("No individual retrieved from repository");
		//	throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null)));	

			
		}
				
		logger.info("Ending");

		return allIndex;
	}
	
	
	public Index findBySocialSecNum(String socialSecNum) {
		
		logger.info("Start");
		logger.debug("Fetching individual with socialSecNum: {}", socialSecNum);
		
		Index index = this.indexRepository.findBySocialSecNum(socialSecNum);
	/**	if(index == null) {
			logger.debug("Individual with socialSecNum: {} not found", socialSecNum);
			throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null)));	

			
			
		}*/
		return index;
	
	}
}

