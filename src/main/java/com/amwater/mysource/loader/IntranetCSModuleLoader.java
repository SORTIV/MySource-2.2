package com.amwater.mysource.loader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.amwater.mysource.builder.MySourceCSModuleBuilder;
import com.apporchid.cloudseer.config.loader.BaseCloudseerModuleLoader;

@Profile("!solution-dev")
@Component
public class IntranetCSModuleLoader extends BaseCloudseerModuleLoader<MySourceCSModuleBuilder> {
	private static final String[] DEFAULT_ACCESS_ROLES = new String[] { "administrator", "demo_user" };

	@Override
	protected Class<MySourceCSModuleBuilder> getCSModuleBuilderType() {
		return MySourceCSModuleBuilder.class;
	}

	@Override
	protected boolean isReloadOnServerStartup() {
		return true;
	}

	@Override
	protected String[] getAccessRoles() {
		return DEFAULT_ACCESS_ROLES;
	}
}
