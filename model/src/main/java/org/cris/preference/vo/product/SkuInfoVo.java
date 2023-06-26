package org.cris.preference.vo.product;

import org.cris.preference.model.product.*;
import org.cris.preference.model.product.SkuAttrValue;
import org.cris.preference.model.product.SkuImage;
import org.cris.preference.model.product.SkuInfo;
import org.cris.preference.model.product.SkuPoster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo {

	@ApiModelProperty(value = "海报列表")
	private List<SkuPoster> skuPosterList;

	@ApiModelProperty(value = "属性值")
	private List<SkuAttrValue> skuAttrValueList;

	@ApiModelProperty(value = "图片")
	private List<SkuImage> skuImagesList;

}

