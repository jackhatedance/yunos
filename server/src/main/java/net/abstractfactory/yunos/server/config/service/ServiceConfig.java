package net.abstractfactory.yunos.server.config.service;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.abstractfactory.plum.integration.spring.PlumFactoryBean;
import net.abstractfactory.plum.integration.spring.SpringReflectionHelper;
import net.abstractfactory.plum.integration.spring.hibernate.RepositoryFactoryBean;
import net.abstractfactory.plum.integration.spring.listener.PlumClosedListener;
import net.abstractfactory.plum.integration.spring.tx.SpringTranactionExecutor;
import net.abstractfactory.plum.ognl.OgnlRoot;
import net.abstractfactory.plum.ognl.OgnlSecurityService;
import net.abstractfactory.plum.repository.biz.TransactionExecutor;
import net.abstractfactory.plum.repository.biz.interafce.Repository;
import net.abstractfactory.plum.security.SecurityService;
import net.abstractfactory.util.ReflectionFactory;
import net.abstractfactory.yunos.server.security.service.MyOgnlSecurityService;
import net.abstractfactory.yunos.server.security.service.MySecurityCheckService;

@Configuration
@ComponentScan(basePackageClasses = { PlumClosedListener.class, SpringTranactionExecutor.class })
public class ServiceConfig {

	@Bean
	public RepositoryFactoryBean repository(SessionFactory sessionFactory) {
		RepositoryFactoryBean bean = new RepositoryFactoryBean();
		bean.setSessionFactory(sessionFactory);
		return bean;
	}

	@Bean
	public SecurityService securityService() {
		return new MySecurityCheckService();
	}

	@Bean
	public OgnlSecurityService ognlSecurityService() {
		return new MyOgnlSecurityService();
	}

	@Bean
	@Autowired
	public PlumFactoryBean plum(TransactionExecutor transactionExecutor, Repository repository,
			SecurityService securityService, OgnlSecurityService ognlSecurityService) throws Exception {
		OgnlRoot ognlRoot = new OgnlRoot();
		ognlRoot.init(repository, ognlSecurityService);

		ReflectionFactory.register(new SpringReflectionHelper());

		PlumFactoryBean bean = new PlumFactoryBean();

		bean.setTransactionExecutor(transactionExecutor);
		bean.setRepository(repository);
		bean.setSecurityService(securityService);
		bean.setOgnlRoot(ognlRoot);

		Set<String> packages = new java.util.HashSet();
		packages.add("net/abstractfactory/plum");
		packages.add("com/dingjianghao/homepage");

		bean.setViewBuilderBasePackages(packages);

		// bean.setDefaultModelClassName("net.abstractfactory.plum.demo.biz.DemoApp");
		return bean;
	}

}
