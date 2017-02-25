/**
 * ﻿Copyright (C) 2012-${latestYearOfContribution} 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as publishedby the Free
 * Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of the
 * following licenses, the combination of the program with the linked library is
 * not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed under
 * the aforementioned licenses, is permitted by the copyright holders if the
 * distribution is compliant with both the GNU General Public License version 2
 * and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */
package org.n52.sps.sensor.cite;

import junit.framework.TestCase;
import net.opengis.ows.x11.MetadataType;
import net.opengis.sps.x20.DescribeTaskingResponseDocument;
import net.opengis.sps.x20.DescribeTaskingResponseType;
import net.opengis.sps.x20.DescribeTaskingResponseType.TaskingParameters;
import net.opengis.sps.x20.SPSMetadataDocument;
import net.opengis.swe.x20.AbstractDataComponentType;
import net.opengis.swe.x20.DataRecordDocument;
import net.opengis.swe.x20.DataRecordType;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.n52.sps.sensor.SensorTaskService;
import org.n52.sps.sensor.model.SensorConfiguration;
import org.n52.sps.sensor.model.SensorDescription;
import org.n52.sps.util.nodb.InMemorySensorTaskRepository;
import org.n52.testing.utilities.FileContentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CiteTestSensorTest extends TestCase {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CiteTestSensorTest.class);

    private final static String TASKING_PARAMETERS_TEMPLATE = "/files/taskingParameters.xml";
    
    private static final String PROCEDURE = "http://my.namespace.org/procedure/xy";
    private static final String PROCEDURE_DESCRIPTION_URL = "http://my.namespace.org/procedure/xy/description";
    private static final String PROCEDURE_DESCRIPTION_FORMAT = "http://www.opengis.net/sensorML/1.0.1";
    private static final String PLUGIN_TYPE = "org.n52.sps.sensor.cite.CiteTestSensor";
    
    private CiteTestSensor sensor;

    @Override
    protected void setUp() throws Exception {
        SensorConfiguration configuration = new SensorConfiguration();
        configuration.setProcedure(PROCEDURE);
        XmlObject template = FileContentLoader.loadXmlFileViaClassloader(TASKING_PARAMETERS_TEMPLATE, getClass());
        configuration.setTaskingParametersTemplate(((DataRecordDocument) template).getAbstractDataComponent());
        configuration.setSensorPluginType(PLUGIN_TYPE);
        configuration.addSensorDescription(new SensorDescription(PROCEDURE_DESCRIPTION_FORMAT, PROCEDURE_DESCRIPTION_URL));
        sensor = new CiteTestSensor(new SensorTaskService(new InMemorySensorTaskRepository()), configuration);
    }

    public void testCreateSpsMetadata() {
        MetadataType metadata = MetadataType.Factory.newInstance();
        sensor.createSpsMetadata(metadata, "http://data.access-link.org/");
        try {
            SPSMetadataDocument parsedmetadata = SPSMetadataDocument.Factory.parse(metadata.getDomNode());
            assertEquals("Types are not equal!", SPSMetadataDocument.type, parsedmetadata.schemaType());
        }
        catch (XmlException e) {
            LOGGER.error("Could not create SPSMetadata from configuration.", e);
            fail("SPSMetadata could not be created.");
        }
    }
    
    public void testQualifyDataComponent() {
        AbstractDataComponentType taskingParametersTemplate = sensor.getSensorConfiguration().getTaskingParametersTemplate();
        DescribeTaskingResponseDocument responseDocument = DescribeTaskingResponseDocument.Factory.newInstance();
        DescribeTaskingResponseType describeTaskingResponse = responseDocument.addNewDescribeTaskingResponse();
        TaskingParameters taskingParameter = describeTaskingResponse.addNewTaskingParameters();
        taskingParameter.addNewAbstractDataComponent().set(taskingParametersTemplate);
        
        TaskingParameters taskingParameters = describeTaskingResponse.getTaskingParameters();
        AbstractDataComponentType unQualifiedDataComponent = taskingParameters.getAbstractDataComponent();
        sensor.qualifyDataComponent(unQualifiedDataComponent);
        
        DataRecordType qualifiedDataComponent = (DataRecordType) unQualifiedDataComponent;
        assertEquals("Types are not equal!", DataRecordType.type, qualifiedDataComponent.schemaType());
    }

}
