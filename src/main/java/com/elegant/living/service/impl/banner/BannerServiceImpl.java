package com.elegant.living.service.impl.banner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elegant.living.service.banner.BannerServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("bannerService")
@Transactional
public class BannerServiceImpl extends CommonServiceImpl implements BannerServiceI {
	
}