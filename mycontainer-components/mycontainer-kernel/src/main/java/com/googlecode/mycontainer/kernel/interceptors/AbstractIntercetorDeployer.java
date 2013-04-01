/*
 * Copyright 2008 Whohoo Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable
 * law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.googlecode.mycontainer.kernel.interceptors;

import com.googlecode.mycontainer.kernel.deploy.Deployer;
import com.googlecode.mycontainer.kernel.reflect.proxy.ContextInterceptor;

public abstract class AbstractIntercetorDeployer extends Deployer {

	public abstract <T extends ContextInterceptor> void addContextInterceptor(
			Class<T> interceptor);

	public abstract <T extends ContextInterceptor> void removeContextInterceptor(
			Class<T> interceptor);

}