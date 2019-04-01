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

package com.liferay.ebus.source;

import com.liferay.ebus.dataset.Dataset;

import com.liferay.ebus.datastore.Datastore;
import com.liferay.ebus.service.EBusService;
import org.talend.sdk.component.api.component.Icon;
import org.talend.sdk.component.api.component.Version;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Assessor;
import org.talend.sdk.component.api.input.Emitter;
import org.talend.sdk.component.api.input.PartitionMapper;
import org.talend.sdk.component.api.input.PartitionSize;
import org.talend.sdk.component.api.input.Split;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Igor Beslic
 */
@Documentation("Reads E-Bus entries")
@Icon(value = Icon.IconType.STAR)
@PartitionMapper(name = "eBusMapper")
@Version(1)
public class EBusMapper implements Serializable {

    public EBusMapper(
		@Option("configuration") final Dataset dataset, EBusService eBusService,
		final RecordBuilderFactory recordBuilderFactory) {

    	_dataset = dataset;
    	_eBusService = eBusService;
        _recordBuilderFactory = recordBuilderFactory;

		System.out.println("[" + this + "] " + "constructed");
    }

    @PostConstruct
    public void init() {
		Datastore dataStore =
			_dataset.getDatastore();

		URL endpointInstanceURL = dataStore.getEndpointInstanceUrl();

		System.out.println("[" + this + "] " + "initialized");
	}

    @Assessor
    public long estimateSize() {
    	return 15L;
    }

    @Split
    public List<EBusMapper> split(
    	@PartitionSize final long bundles) {

    	return singletonList(this);
    }

    @Emitter
    public EBusSource createWorker() {
		System.out.println("[" + this + "] " + "using service: " + _eBusService);

        return new EBusSource(_recordBuilderFactory);
    }

	private final Dataset _dataset;
	private final EBusService _eBusService;
	private final RecordBuilderFactory _recordBuilderFactory;

}