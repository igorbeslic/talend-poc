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

package com.liferay.ebus.service;

import com.liferay.ebus.dataset.Dataset;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.record.Schema;
import org.talend.sdk.component.api.service.Service;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;
import org.talend.sdk.component.api.service.schema.DiscoverSchema;

/**
 * @author Igor Beslic
 */
@Service
public class EBusService {

	@DiscoverSchema(value = "Dataset")
	public Schema discoverSchema(
		@Option("dataset") Dataset dataset,
		final RecordBuilderFactory recordBuilderFactory) {

		System.out.println(
			"[" + this + "] using record builder " + recordBuilderFactory);

		Schema.Builder schemaBuilder = recordBuilderFactory.newSchemaBuilder(
			Schema.Type.RECORD);

		Schema.Entry.Builder entryBuilder =
			recordBuilderFactory.newEntryBuilder();

		schemaBuilder.withEntry(
			_doBuildEntry("alfaColumn", Schema.Type.STRING, entryBuilder));
		schemaBuilder.withEntry(
			_doBuildEntry("betaColumn", Schema.Type.STRING, entryBuilder));
		schemaBuilder.withEntry(
			_doBuildEntry("gamaColumn", Schema.Type.STRING, entryBuilder));
		schemaBuilder.withEntry(
			_doBuildEntry("deltaColumn", Schema.Type.STRING, entryBuilder));

		return schemaBuilder.build();
	}

	private Schema.Entry _doBuildEntry(
		String name, Schema.Type type, Schema.Entry.Builder entryBuilder) {

		return entryBuilder.withName(
			name
		).withType(
			type
		).withNullable(
			false
		).withComment(
			"Comment to make difference from schema resolved from record"
		).build();
	}

}
