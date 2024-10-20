package com.bo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDataBO {

	private String encodedFrontImage;
	
	private String encodedBackImage;
	
	private List<String> extraImages;
	
}
