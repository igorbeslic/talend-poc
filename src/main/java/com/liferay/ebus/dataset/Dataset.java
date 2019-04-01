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

package com.liferay.ebus.dataset;

import com.liferay.ebus.datastore.Datastore;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.type.DataSet;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

import java.io.Serializable;

/**
 * @author Igor Beslic
 */
@DataSet("Dataset")
@GridLayout({
    @GridLayout.Row({ "_datastore" }),
	@GridLayout.Row({ "_maxItemsPerRequest" })
})
@Documentation("Additional Open Api 3 properties and directives needed for parser services")
public class Dataset implements Serializable {

	public Datastore getDatastore() {
        return _datastore;
    }

    public Dataset setDatastore(Datastore datastore) {
        _datastore = datastore;

        return this;
    }

	public int getMaxItemsPerRequest() {
		return _maxItemsPerRequest;
	}

	public Dataset setMaxItemsPerRequest(int maxItemsPerRequest) {
		_maxItemsPerRequest = maxItemsPerRequest;

		return this;
	}

	@Option("_datastore")
	@Documentation("Connection configuration for E-Bus")
	private Datastore _datastore;

	@Option
	@Documentation("Maximum items to return in one request")
	private int _maxItemsPerRequest;

}