package com.elegant.living.service.impl.im;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elegant.living.service.im.IMentityServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("iMentityService")
@Transactional
public class IMentityServiceImpl extends CommonServiceImpl implements IMentityServiceI {
	
}