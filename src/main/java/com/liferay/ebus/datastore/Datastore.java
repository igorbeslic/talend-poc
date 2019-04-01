/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.ebus.datastore;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.type.DataStore;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

import java.io.Serializable;
import java.net.URL;

/**
 * @author Igor Beslic
 */
@DataStore("Datastore")
@GridLayout({
    @GridLayout.Row({ "endpointInstanceUrl" }),
    @GridLayout.Row({ "apiKey" })
})
@Documentation("Data store with endpoint URL and authentication for open Api3 endpoint")
public class Datastore implements Serializable {

    public URL getEndpointInstanceUrl() {
        return _endpointInstanceUrl;
    }

    public Datastore setEndpointInstanceUrl(URL endpoint) {
        _endpointInstanceUrl = endpoint;
        return this;
    }

    public String getApiKey() {
        return _apiKey;
    }

    public Datastore setApiKey(String apiKey) {
        _apiKey = apiKey;

        return this;
    }

	@Option("apiKey")
	@Documentation("Api key used for authorization header")
	private String _apiKey;

    @Option("endpointInstanceUrl")
	@Documentation("Endpoint instance URL")
	private URL _endpointInstanceUrl;


}