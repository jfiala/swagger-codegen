package io.swagger.codegen.generators;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenConstants;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public abstract class AbstractGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenerator.class);
	
	protected String contractPath;
	
	protected String samplesFolder;
	
	protected CodegenConfig codegenConfig;
	
    public void generateToFolder(File output) {
    	String contractPath = this.getContractPath();
    	
    	final Swagger swagger = new SwaggerParser().read(contractPath);

    	codegenConfig.additionalProperties().put(CodegenConstants.HIDE_GENERATION_TIMESTAMP, true);

    	codegenConfig.setOutputDir(output.getAbsolutePath());

        ClientOptInput clientOptInput = new ClientOptInput().opts(new ClientOpts()).swagger(swagger).config(codegenConfig);

        DefaultGenerator gen = new DefaultGenerator();
        gen.opts(clientOptInput);

        gen.generate();
    }
  
    
	public String getContractPath() {
		return contractPath;
	}

	public void setContractPath(String contractPath) {
		this.contractPath = contractPath;
	}

	public String getSamplesFolder() {
		return samplesFolder;
	}


	public void setSamplesFolder(String samplesFolder) {
		this.samplesFolder = samplesFolder;
	}


	public CodegenConfig getCodegenConfig() {
		return codegenConfig;
	}


	public void setCodegenConfig(CodegenConfig codegenConfig) {
		this.codegenConfig = codegenConfig;
	}
    
}
