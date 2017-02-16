package com.cachexic.apple.common.junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Junit测试公共类
 * @author tangmin
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@TransactionConfiguration(transactionManager="transactionManager") 
//如果多个配置文件@ContextConfiguration(locations =  { "classpath:applicationContext.xml","classpath:applicationContext-shiro.xml" })
public class SpringJunitTest extends AbstractTransactionalJUnit4SpringContextTests{

}
