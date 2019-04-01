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

import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.record.Record;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.Serializable;

/**
 * @author Igor Beslic
 */
@Documentation("Source for swagger open api specifications")
public class EBusSource implements Serializable {

    public EBusSource(final RecordBuilderFactory recordBuilderFactory) {
        _recordBuilderFactory = recordBuilderFactory;

		System.out.println("[" + this + "] " + "constructed");
    }

    @PostConstruct
    public void init() {
    	_title = "Title";
		_version = "Version";
		_limit = 20;
		_current = 0;

		System.out.println("[" + this + "] " + "initialized");
	}

    @Producer
    public Record next() {
    	if (_current == _limit) {
			return null;
		}

		Record record = _createRecord(_current++);

		System.out.println("[" + this + "] " + "produced record " + record);

        return record;
    }

	@PreDestroy
	public void release() {
		System.out.println("[" + this + "] " + "released");
	}

    private Record _createRecord(long seed) {
		String suffix = String.format("%03d",seed);

    	String title = _title + suffix;
    	String version = _version + suffix;
    	String path = "/path/" + suffix;

		Record.Builder recordBuilder = _recordBuilderFactory.newRecordBuilder();

		recordBuilder.withString("alfaColumn", title);
		recordBuilder.withString("betaColumn", version);
		recordBuilder.withString("gamaColumn", path);
		recordBuilder.withString("deltaColumn", "delta");

		Record record = recordBuilder.build();

		return record;
	}

	private final RecordBuilderFactory _recordBuilderFactory;
	private String _version;
	private String _title;
	private long _limit;
	private long _current;

}