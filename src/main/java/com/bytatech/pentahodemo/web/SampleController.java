package com.bytatech.pentahodemo.web;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;
@RestController
public class SampleController {
	
	@GetMapping("/test")
	public void executeKtr() {
			   try {
	            KettleEnvironment.init();
	            URL ktrUrl = SampleController.class.getClassLoader().getResource("Transformation 1.ktr");
	            TransMeta metaData = new TransMeta(ktrUrl.getFile());
	            Trans trans = new Trans( metaData );
	        	//trans.setParameterValue("inputfile", "");
	            trans.execute( null );
	            trans.waitUntilFinished();
	            if ( trans.getErrors() > 0 ) {
	                System.out.print( "Error Executing transformation" );
	            }
	        } catch( KettleException e ) {
	            e.printStackTrace();
	        }
	}

}
